import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ImageHasher{ 
  public int halfLength;
  public double variance;

  public ImageHasher(int halfLength, double variance){
  	this.halfLength = halfLength;
  	this.variance = variance;
  }
  
  public BufferedImage hash(BufferedImage image){
    Kernel kernel =  new Kernel(2*halfLength + 1, 2*halfLength + 1, ImageHasher.gassianKernel(this.halfLength, this.variance));
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

  public static float[] gassianKernel(int halfLength, float variance){
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
  
  
 
  public static void main(String[] args){
    BufferedImage img, possesedImg;
    ImageHasher hasher = new ImageHasher(14, 4.0);

    img = openImageFile(args[0]);
    possesedImg = hasher.hash(img);
    saveImageFile(possesedImg, args[0]+".hash");
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
