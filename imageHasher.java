import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class imageHasher{ 
  public int halfLength = 14;
  public double variance = 4.0;
 
  public static void main(String[] args){

   BufferedImage img, possesedImg;
   imageHasher hasher = new ImageHasher(14, 4.0);

 img = openImageFile(args[0]);
 possesedImg = imageHash.hash(img);
 saveImageFile(possesedImg, args[0]+".hash");
  }
  
  public imageHasher(int halfLength, double variance){
  	this.halfLength = halfLength;
  	this.variance = variance;
  	
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

  
  public static BufferedImage hash(BufferedImage image){
    int halfLength = 14;
    double variance = 4.0;
    
     
    Kernel kernel =  new Kernel(2*halfLength + 1, 2*halfLength + 1, gassianKernel(halfLength, variance));
    //Java Native Convolution Object
    ConvolveOp cOP = new ConvolveOp(kernel);
  //apply gassian bluring function  
  
    return cOP.filter(image, null);
  }
    
  public static float[] gassianKernel(int halfLength, double variance){
    int length = (int)Math.pow(2*halfLength + 1, 2);
    float[] out = new float[length];
    
    for(int I = -halfLength; I<=halfLength; I++){
      for(int K = -halfLength; K<=halfLength; K++){
        out[(I + halfLength)*(2*halfLength + 1) + K + halfLength] = 
                      ((float)(1/(2*Math.PI*Math.pow(variance, 2)))) *((float) Math.exp(-(I*I + K*K)/(2*Math.pow(variance, 2))));
      }
    }

    return out;
  }
}
