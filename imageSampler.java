//crud class for identifying magic cards


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
    public float[][] signitureCovariance;
    public float[][][][] cardHypothesisData chd;// [xSamples][ySamples][N] = signitureCovariance[][]
    
    
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
        }
        
        //We must now take are data and build the most likely world view
        //ToDo: We will first do a local summation via median window filter.
        //Canidits with the hights score in each position will be bubbled up.
        //
        
        
        
        
        
        
        
        
    }
        
    

  
}
