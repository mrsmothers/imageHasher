//crud class for identifying magic cards
import java.util.image.*;
import java.util.Image.BufferedImage;
import java.


public class imageSampler{

//select a portion of the screen
//generate histograme of with prossed image(gassian blure, and grayscale conversion)
//charicterize hisogame signiture
//compare with data base

//FunFact:The aspect ratio of a magic card is 5:7(2.5'' x 3.5'')

    public BufferedImage originalImage;
    public float[] kernalsWidths;
    public float[][] kernals;
    public float[] histogram;
    
    public float[][] dataBase;
    public String[] cardNames;
    
    public float[] signitureCovariance;
    public float[][][] cardHypothesisData;// [xSamples][ySamples][N] = signitureCovariance[][]
    public int[] projections;
    
    
    public String[] returnMatches(){
        BufferedImage grayScaleImage = intesityMap.remap(originalImage);
        int numSamples;
        
        int boxWidth;
        
        //box portions of the screen to build the chd(card hypothesis data)
        for(int I = 0; I<kernals.length; I++){//for each field of view use a different boxWidth
            numSamples = ((int)(grayScaleImage.getWidth() / xResolution)) * ((int)(grayScaleImage.getHeight()/yResolution));
           //ToDo:finish this boxWIdth = Math.pow()
            
            //ToDo:add nessary code to allow Window function to start at intermediate points. 
            //ToDo:define x & y resolution 
            
            for(int J = 0; J<originalImage.length+boxWidth; J+=xResolution){// for the length of the image
                for(int K = 0; K<originalImage.length+boxWidth; K+=yResolution){// for the height of the  image
            
                    // applay gassian filter to region of image to generate histogram
                    histogram = gassianHistogramWindower(grayScaleImage, x0, Y0, kernal[I])//ToDo:Define This
        
                    for(int L = 0; L< numSamples; L++){//compare sample with all other signitures
                        signitureCovariance[I][L] = signatureCompare(histogram, dataBase[L]);//differance in eqilized histograms
                    }
                }    
            }    
        
        
        //We must now take are data and build the most likely world view
        //ToDo: We will first do a local summation via median window filter.
        //Canidits with the hights score in each position will be bubbled up.
           
            for(int J = samplerWidth; J < cardHypothiesData.length - samplerWidth; J++){    // in the  X direction
                for(int K = samplerWidth; K < cardHyopthesisData.length - samplerWidth; K++)  // in the Y direction
                    int cardInferance = -1;
                    float score;   
                    
                    for(int L = 0; L < cardHypothisesData[][][].length; L++){//for each card
                        float localScore = 0;
                        
                        for(int M = -samplerWidth; M < samplerWidth; M++){ // for windowX
                            for(int N = -samplerWidth; N < samplerWidth; N++){//for windowY
                                localScore +=cardHypotisData[J+L][K+M][L]       //acumlate sum of hypotiss
                            } 
                        }
                        
                        if(localScore>threshold && localScore>score){
                            cardInferance = L;
                            score = localScore;
                        }
                    }
                    projections[J*(cardHypothisData.length-samplerWidth)+K - samplerWidth]
                }
            }
            //acumulate histogram of projections
            int[] histogram = new int[numCards]
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
            for(int J = 0; J < 10; J++)
                System.out.println(cardNames[outputOrder[numCards-1-J]+":"+projections[outputOrder[numCards-1-J]);
        }
        
    }
        
    

  
}
