import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Arrays;

public class imageHasher{ 

  public static BufferedImage img, possesedImg;
  public static void main(String[] args){
  
  openImageFile(args[0]);
  possesedImg = imageHash.hash(img);
  saveImageFile(args[0]);
  }

  public static void openImageFile(String fileName){
       try {
           img = ImageIO.read(new File(fileName));
       } catch (IOException e) { }
       
  }
  
  public static void saveImageFile(String fileName){
      try {
        File outputfile = new File(fileName+".hash");
        ImageIO.write(possesedImg, "png", outputfile);
       } catch (IOException e) { }
    
  }
}

class imageHash{
  public double[][] map;
  
  //applay an 2-D gassian blur to an image
  public static BufferedImage hash(BufferedImage image){
    
    
    int halfLeng = 9;
    double variance = 3.0;
    
    float[] map = gassianKernel(halfLeng, variance)
    
    //2-D convlution map 
    Kernel kernel =  new Kernel(2*halfLeng+1, 2*halfLeng+1, map);
    //Java Native Convolution Object
    ConvolveOp cop = new ConvolveOp(kernel);
  //apply gassian bluring function  
  
    return cop.filter(image,null);
  }
    
  public static float[] gassianKernel(int halfLength, double variance){
    int length = (int)Math.pow(2*halfLength+1, 2);
    float[] out = new float[length];
    
    for(int I=-halfLength; I<=halfLength; I++){
      for(int K=-halfLength; K<=halfLength; K++){
        out[(I+halfLength)*(2*halfLength+1)+K+halfLength] = 
                      ((float)(1/(2*Math.PI*Math.pow(variance, 2)))) *((float) Math.exp(-(I*I+K*K)/(2*Math.pow(variance, 2))));
      }
    }

    return out;
  }
}
