// generates histograme of image
import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Arrays;

public class imageHistogram{
  
  
  public static void main(String[] args){
    Bufferedimage img = openImageFile(args[0]);
    int work = imageHistogram.prosses(img);
    
    for(int I=0; I<tmp.length; I++){
      System.out.println(I+":"+tmp[I]);
    }
  }
  
  public static int[] prosses(BufferedImage img){
    int out = new int[256];
    
    Arrays.fill(out, 0);
    
    for(int I = 0; I < img.getHeight(); I++){
      for(int J = 0; J < img.getWidth(); J++){
        int[] v = quantizePixle(img, J, I);
        int tmp = (int)(vLength(v)/(Math.pow(3, 0.5)));

        out[tmp]++;
      }
    }
    
    return out;
  }
  
  public static BufferedImage openImageFile(String fileName){
     BufferedImage img;
    
     try {
       img = ImageIO.read(new File(fileName));
     } catch (IOException e) { }
       
     return img;
       
  }

  public static int[] quantizePixle(BufferedImage img, int x, int y){
    int clr = img.getRGB(x, y);
    int red = (clr & 0x00ff0000) >> 16;
    int green = (clr & 0x0000ff00) >> 8;
    int blue = clr & 0x000000ff;
    
    int[] out = {red, green, blue};
    
    return out;
  }  
}
