//crud class for identifying magic cards
import java.awt.image.*;
import java.awt.Image;
import javax.imageio.*;
import java.io.IOException;
import java.io.File;
import java.util.*;


public class ImageSampler{

//select a portion of the screen
//generate histograme of with prossed image(gassian blure, and grayscale conversion)
//charicterize hisogame signiture
//compare with data base

//FunFact:The aspect ratio of a magic card is 5:7(2.5'' x 3.5'')

	public int[] kernalHalfWidths = {250};
	public float[] kernalVariance = {0.5f};
	public int deltaX[] = {10};
	public int deltaY[] = {10};

    public float[] histogram;
    
	public int numCards;
	public int numSamples = 0 ;
	public float[][] dataBase;
    public String[] cardNames;
	public String[] fileNames ;
    
    public float[] signitureCovariance;
    public float[][][] cardHypothesisData; // [xSamples][ySamples][N] = signitureCovariance[][]
    public int[][] projections;

	public int projectionSamplerWidth = 3;
    public int projectionSupressionThreshold = 0;
	public int numberOfOutputs = 1;
	
    
	public ImageSampler(){
		this.numCards = 1;
		this.numSamples = 3;
		dataBase = new float[numCards*numSamples][];

		//Name of cards and file location
		cardNames = new String[numCards];
		fileNames = new String[numCards];

		cardNames[0] = "Neal Patric Haris";
		fileNames[0] = "nph.jpg";
/*
		cardNames[1] = "Charizard";
		fileNames[1] = "charizard.jpg";
		
		cardNames[2] = "Echo-Mage";
		fileNames[2] = "magic-2.jpg";

		cardNames[3] = "Alloy Myr";
		fileNames[3] = "myr.jpg" ;
		*/
		//load Cards and build database
		for(int I = 0; I < numCards; I++){
			BufferedImage img = openImageFile("db/"+fileNames[I]);
			
			float[][] tmp = HistogramImageHash.prosses(img);
			//unpack hashes
			for(int K = 0; K < this.numSamples;K++){
				dataBase[numSamples*I+K] =  tmp[K];
				//-------System.out.println(Arrays.toString(dataBase[numSamples*I+K]));
			}
		}
	}
    //box portions of the screen to build the chd(card hypothesis data)
    public void returnMatches(BufferedImage img){
        BufferedImage grayScaleImage = ImageGradient.prosses(img);//convert to gray scale
        
        
        //for each field of view use a different mask 
        for(int I = 0; I<kernalHalfWidths.length; I++){

			//init values and data
			int boxWidth = 2*kernalHalfWidths[I]+1;
            cardHypothesisData = new float[(int)(grayScaleImage.getWidth()/ deltaX[I])][(int)(grayScaleImage.getHeight()/deltaY[I])][numCards*this.numSamples] ;

			//Generate Maskes
			float[] kernal =  ImageHasher.gassianKernel(kernalHalfWidths[I], kernalVariance[I]);
           
            
            //ToDo:add nessary code to allow Window function to start at intermediate points. 
            
            for(int J = 0; J<img.getWidth()-boxWidth; J+=deltaX[I]){// for the length of the image
                for(int K = 0; K<img.getHeight()-boxWidth; K+=deltaY[I]){// for the height of the  image
            
                    // apply gassian filter to region of image to generate histogram
                    histogram = gassianHistogramWindower(grayScaleImage, J, K, kernal, boxWidth);
        
                    for(int L = 0; L< this.numSamples*numCards; L++){//compare sample with all other signitures
                        signitureCovariance[L] = signatureCompare(histogram, dataBase[L]);//differance in eqilized histograms
						System.out.print(signitureCovariance[L]);
                    }
					System.out.println();
                }  
  
            }    
        
        
        //We must now take are data and build the most likely world view
        //ToDo: We will first do a local summation via median window filter.
        //Canidits with the hights score in each position will be bubbled up.

		
           	projections = new int[cardHypothesisData.length - projectionSamplerWidth][cardHypothesisData[0].length - projectionSamplerWidth];
            for(int J = 0; J < cardHypothesisData.length - projectionSamplerWidth; J++){    // in the  X direction
                for(int K = 0; K < cardHypothesisData[J].length - projectionSamplerWidth; K++) { // in the Y direction
                    int cardInferance = -1;
                    float score = 0;   
                    
                    for(int L = 0; L < numCards*numSamples; L++){//for each card
                        float localScore = 0;
                        
                        for(int M = 0; M < projectionSamplerWidth; M++){ // for windowX
                            for(int N = 0; N < projectionSamplerWidth; N++){//for windowY
                                localScore +=cardHypothesisData[J+N][K+M][L];       //acumlate sum of hypotiss
                            } 
                        }
                        
                        if(localScore>projectionSupressionThreshold && localScore>score){
                            cardInferance = L;
                            score = localScore;
                        }
                    }
                    projections[J][K] = cardInferance;
                }
            }
            //acumulate histogram of projections
            int[] histogram = new int[numCards];
            for(int J = 0; J < projections.length; J++){
				for(int K = 0; K < projections[J].length; K++){
                if(projections[J][K]!=-1)
                	histogram[projections[J][K]/this.numSamples]++;//divide by number of samples
				}
            }
            //determin print order
            int[] outputOrder = new int[numCards];
            int index = 0;
            
            for(int J = 0 ;index < numCards; J++){
                for(int K = 0; K < numCards; K++){
                    if(J == histogram[K]){
                        outputOrder[index] = K;
                        index++;
                    }
                }
            }
            
            //print results
            for(int J = 0; J < numberOfOutputs; J++)
                System.out.println(cardNames[outputOrder[numCards-1-J]]+":"+histogram[outputOrder[numCards-1-J]]);
        }
    }
    
    public static void main(String[] args){
    	ImageSampler sampler = new ImageSampler();
    	sampler.returnMatches(openImageFile(args[0]));
    }
    
	//method aplays a mask to sample a rectaguler region of the screen 
	public static float[] gassianHistogramWindower(BufferedImage img, int x0, int y0, float[] mask, int maskWidth){
	    float[] out = new float[256];
		int maskIndex = 0;
		
		for(int I = x0; I < x0 + maskWidth; x0++){
			for(int J = y0; J < y0 + maskWidth; J++){
				out[(img.getRGB(I, J) & 0x00ff0000) >> 16] += mask[maskIndex];
				maskIndex++;
			}
		}

		return out;
	}

	//uses mean of differances squared to compare two histogram
	public static float signatureCompare(float[] arg1, float[] arg2){
		float runningSum = 0f;	

		for(int I = 0; I < arg1.length; I++)
			runningSum +=Math.pow(arg1[I]-arg2[I], 2);

		return 1-runningSum/arg1.length;
	}
					
    
  public static BufferedImage openImageFile(String fileName){
       BufferedImage img = null;
       
       try {
           img = ImageIO.read(new File(fileName));
       } catch (IOException e) { }

       return img;
       
  }
  
  public static void saveImageFile(BufferedImage img, String fileName){
      try {
       	File outputfile = new File(fileName);
	       ImageIO.write(img, "png", outputfile);
       } catch (IOException e) { }
  }
  
}
