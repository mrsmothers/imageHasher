//crud class for identifying magic cards
import java.awt.image.*;
import java.awt.Image;
import javax.imageio.*;


public class ImageSampler{

//select a portion of the screen
//generate histograme of with prossed image(gassian blure, and grayscale conversion)
//charicterize hisogame signiture
//compare with data base

//FunFact:The aspect ratio of a magic card is 5:7(2.5'' x 3.5'')

	public float[] kernalHalfWidths = {250};
    public float[] kernalsWidths = {501};
    public float[][] kernals;
	public int deltaX[] = {10};
	public int deltaY[] = {10};


    public float[] histogram;
    
    
	public int numCards;
	public int numSamples ;
	public float[] dataBase;
    public String[] cardNames;
	public String[] fileNames ;
    
    public float[] signitureCovariance;
    public float[][][] cardHypothesisData;// [xSamples][ySamples][N] = signitureCovariance[][]
    public int[][] projections;



    
	public ImageSampler(){
		numCards = 1;
		numSamples = 3;
		dataBase = new float[numCards*numSamples];

		//Name of cards and file location
		cardNames = new String[numCards];
		cardNames[0] = "Neal Patric Haris";
		fileNames = new String[numCards];
		fileNames[0] = "nph.jpg";

		//load Cards and build database
		for(int I = 0; I < numCards; I++){
			BufferedImage img = openImageFile(fileNames[I]);
			
			float[][] tmp = HistogramImageHash.prosses(img);
			//unpack hashes
			for(int K = 0; K < numSamples;K++)
				dataBase[numSamples*I+K] = tmp[K];
		}
	}
    //box portions of the screen to build the chd(card hypothesis data)
    public String[] returnMatches(BufferedImage img){
		//convert to gray scale
        BufferedImage grayScaleImage = ImageGradient.remap(img);
        int numSamples;
        
        
        
        //for each field of view use a different mask 
        for(int I = 0; I<kernals.length; I++){

			//init values and data
			int boxWidth = 2*kernalWidths[I]+1;
            cardHypothesisData = new float[(int)(grayScaleImage.getWidth()/ deltaX[I])][(int)(grayScaleImage.getHeight()/deltaY[I])][numCards*numSamples] ;

			//Generate Maskes
			kernals = new float[kernalWidths.length][];
			for(int J = 0; J < kernalsWidths.length; J++)
				kernals[J] = ImageHasher.gassianKernal(kernalWidths[J]);
			
           
            
            //ToDo:add nessary code to allow Window function to start at intermediate points. 
            
            for(int J = 0; J<originalImage.length+boxWidth; J+=deltaX){// for the length of the image
                for(int K = 0; K<originalImage.length+boxWidth; K+=deltaY){// for the height of the  image
            
                    // apply gassian filter to region of image to generate histogram
                    histogram = gassianHistogramWindower(grayScaleImage, J, K, kernals[I], 2*kernalWidths[I]+1);
        
                    for(int L = 0; L< numSamples; L++){//compare sample with all other signitures
                        signitureCovariance[L] = signatureCompare(histogram, dataBase[L]);//differance in eqilized histograms
                    }
                }    
            }    
        
        
        //We must now take are data and build the most likely world view
        //ToDo: We will first do a local summation via median window filter.
        //Canidits with the hights score in each position will be bubbled up.
           
            for(int J = samplerWidth; J < cardHypothiesData.length - samplerWidth; J++){    // in the  X direction
                for(int K = samplerWidth; K < cardHyopthesisData.length - samplerWidth; K++) { // in the Y direction
                    int cardInferance = -1;
                    float score;   
                    
                    for(int L = 0; L < numCards; L++){//for each card
                        float localScore = 0;
                        
                        for(int M = -samplerWidth; M < samplerWidth; M++){ // for windowX
                            for(int N = -samplerWidth; N < samplerWidth; N++){//for windowY
                                localScore +=cardHypotisData[J+L][K+M][L];       //acumlate sum of hypotiss
                            } 
                        }
                        
                        if(localScore>threshold && localScore>score){
                            cardInferance = L;
                            score = localScore;
                        }
                    }
                    projections[J*(cardHypothisData.length-samplerWidth)+K - samplerWidth] = L;
                }
            }
            //acumulate histogram of projections
            int[] histogram = new int[numCards];
            for(int J = 0; J < projections.length; J++){
                if(projections[I]==-1) continue;
                histogram[projections[I]]++;
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
            for(int J = 0; J < 2; J++)
                System.out.println(cardNames[outputOrder[numCards-1-J]]+":"+projections[outputOrder[numCards-1-J]]);
        }
		
        
    }
    
	//method aplays a mask to sample a rectaguler region of the screen 
	public static float[] gassianHistogramWindower(BufferedImage img, int x0, int y0, float[] mask, int maskWidth){
	    float[] out = new float[256];
		int maskIndex = 0;
		
		for(int I = x0; I < x0 + maskWidth; x0++){
			for(int J = y0; J < y0 + maskWidth; J++){
				out[(img.getRGB(I, J) & x00ff0000) >> 16] += mask[maskIndex];
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
