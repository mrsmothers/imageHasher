//returns a subsample of a BufferedImage


public class imageSampler{

//select a portion of the screen
//generate histograme of with prossed image(gassian blure, and grayscale conversion)
//charicterize hisogame signiture
//compare with data base

    public BufferedImage originalImage;
    public String signiture;
    public float[] kernalsWidths {512} 
    public float[] histogram;
    public String[][] dataBase;
    public float signitureCovariance[][];
    public cardHyptissisData chd = new(projecteddistance, scaleFactor,);
    
    
    public String[] returnMatches(){
        BufferedImage grayScaleImage = intesityMap.remap(originalImage);
        int numSamples;
        
        int boxWidth;
        
        //box portions of the screen to prosses
        for(int I = 0; I<kernals.length; I++){//for each field of view use a different boxWidth
            numSamples = ((int)(grayScaleImage.getWidth() / xResolution)) * ((int)(grayScaleImage.getHeight()/yResolution));
            boxWIdth = Math.pow()
            
            //magic cards have an aspect ratio of 5:7(2.5in x 3.5in) and the 
            //diferance 2. The cards are chunked into 3 intersecting parts so 
            //with this equation w*h*(w-h)/chunks 5*7*2/3 = 70/3 This
            //is thought to be a plank width for the minimum dectectable size 
            //of a magic card 20px x 28px
            
            
            
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
