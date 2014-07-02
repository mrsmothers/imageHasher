//returns a subsample of a BufferedImage


public class imageSampler{

//select a portion of the screen
//generate histograme of with prossed image(gassian blure, and grayscale conversion)
//charicterize hisogame signiture
//compare with data base

    public BufferedImage originalImage;
    public String signiture;
    public float[] kernal, histogram;
    public String[] dataBase;
    
    
    public String[] returnMatches(){
        BufferedImage grayImg = intesityMap.remap(originalImage);
        
        int boxWidth;
        
        //box portions of the screen to prosses
        for(int I = 0; I<kernal.length; I++)//for each field of view use a different boxWidth
        for(int J = 0; J<originalImage.length+boxWidth; J++)// for the length of the image
        for(int K = 0; K<originalImage.length+boxWidth;K++)
        
        
        
    }

  
}
