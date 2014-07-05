//returns a subsample of a BufferedImage


public class imageSampler{

//select a portion of the screen
//generate histograme of with prossed image(gassian blure, and grayscale conversion)
//charicterize hisogame signiture
//compare with data base

    public BufferedImage originalImage;
    public String signiture;
    public float[] kernalsWidths;
    public float[] histogram;
    public String[][] dataBase;
    public float signitureCovariance[][];
    public float[][][] cardHyptissisData chd;
    
    
    public String[] returnMatches(){
        BufferedImage grayScaleImage = intesityMap.remap(originalImage);
        int numSamples;
        
        int boxWidth;
        
        //box portions of the screen to prosses
        for(int I = 0; I<kernals.length; I++){//for each field of view use a different boxWidth
            numSamples = ((int)(grayScaleImage.getWidth() / xResolution)) * ((int)(grayScaleImage.getHeight()/yResolution));
            boxWIdth = Math.pow()
            
            
            
            for(int J = 0; J<originalImage.length+boxWidth; J+=xResolution){// for the length of the image
                for(int K = 0; K<originalImage.length+boxWidth; K+=yResolution){// for the height
            
                    // applay gassian filter to region of image to generate histogram
                    histogram = filteredGasainThing(grayScaleImage, x0, Y0, kernal[I])
        
                    for(int L = 0; L< numSamples; L++){//compare sample with all other signitures
                        signitureCovariance[I][L] = signatureCompare(histogram, dataBase[L]);//differance in eqilized histograms

                    }
                     //sift throught the data to build a world model for all of the samples
                    
                }    
            }      
           
            
            
        }
        
        
        
        
    }
        
    

  
}
