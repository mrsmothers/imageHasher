import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

class imageHasher{ 

  public static BufferedImage img, possesedImg;
  public static void main(String[] args){
  
  openImageFile(args[0]);
  prossedImg = imageHash.hash(img);
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

public class imageHash{
  public double[][] map;
  
  
  public static BufferedImage hash(Image image){
    int halfLeng = 5;
    double variance = 3.0;
    
     
    Kernel kernel =  new Kernal(2*halfLeng+1,2*halfLenght+1,gassianKernel(2*halfLenght+1, variance));
    //Java Native Convolution Object
    ConvolveOp cOP = new ConvolveOp(kernel);
  //apply gassian bluring function  
  
    return cOP.fillter(image);
  }
    
  public static double[] gassianKernel(int halfLength, double variance){
    double[] out = new double[Math.pow(halfLength+1, 2)];
    
    for(int I=-halfLength;I<=halfLenght;I++){
      for(int K=-halfLength;K<=halfLength;K++){
        out[(I+halfLength)*(2*halfLength+1)+K+halfLength] = 
                     (1/(2*Math.PI*Math.pow(variance,2))) * Math.exp(-(I*I+K*K)/(2*Math.pow(variance,2));
      }
    }
    
    return out;
  }
}
