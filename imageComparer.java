import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

class imageComparer {
  
  public static void main(String[] args){
    System.out.println(comparer(openImageFile(args[0]), openImageFile(args[1])));
  }
  //campare each
  public static double comparer(BufferedImage img1, BufferedImage img2){
    double sum = 0;
    
    for(int I = 0; I < ((img1.getHeight() < img2.getHeight()) ? img1.getHeight() : img2.getHeight()); I++){
      for(int J = 0; J < ((img1.getWidth() < img2.getWidth()) ? img1.getWidth() : img2.getWidth()); J++){
        float[] v1, v2 ,delta;
        
        v1 = quantizePixle(img1, J, I);
        v2 = quantizePixle(img2, J, I);
        
        delta = vSub(v1, v2);
        
        sum += vLength(delta);
      }
    }

    return 1 - sum / ( ((img1.getWidth() < img2.getWidth()) ? img1.getWidth() : img2.getWidth()) * ((img1.getHeight() < img2.getHeight()) ? img1.getHeight() : img2.getHeight()) * 256);
  }


  public static BufferedImage openImageFile(String fileName){
	BufferedImage img = null;

       try {
           img = ImageIO.read(new File(fileName));
       } catch (IOException e) {System.out.println("file not found "+fileName); }

	return img;
  }
  
  public static float[] quantizePixle(BufferedImage img, int x, int y){
    int clr = img.getRGB(x,y);
    int red = (clr & 0x00ff0000) >> 16;
    int green = (clr & 0x0000ff00) >> 8;
    int blue = clr & 0x000000ff;
    
    float[] out = {red, green, blue};
    
    return out;
  }
  
  public static float vLength(float[] vector){
    double sum = 0;
    
    for(int I=0; I<vector.length; I++)
      sum += Math.pow(vector[I], 2);
      
    return (float)Math.pow(sum, 0.5);
  }
  
  public static float[] vAdd(float[] arg1, float[] arg2){
    float[] out = new float[arg1.length];
    
    for(int I=0; I<out.length; I++)
      out[I] = arg1[I] + arg2[I];
    
    return out;
    
  }
  
  public static float[] vSub(float[] arg1, float[] arg2){
    float[] out= new float[arg1.length];
    
    for(int I=0; I<out.length; I++)
      out[I] = arg1[I] - arg2[I];
      
    return out;
      
  }
}
