// generates histograme of image
import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.lang.Math;


public class imageHistogram{
  float[] histogram;
  int mode;
  
  
  public static void main(String[] args){
    Bufferedimage img = openImageFile(args[0]);
    int work[] = imageHistogram.prosses(img);
    
    for(int I=0; I<tmp.length; I++){
      System.out.println(I+":"+work[I]);
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
        
        if(out[tmp] >out[mode])
        mode = tmp;
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
  
     public String print(int width){
         StringBuilder out = new StringBuilder();
         
         for(int I=0;I<width;I++)
            out.append(""+(I+1)%10));
            
        out.append('\n');
        

    
    for(int I = 0; I< histogram.length; I++)
        out.append(I+1);
        switch((int)Math.log10(I)){
            case 0:
                out.append("  :");
                break;
            case 1:
                out.append(" :");
                break;
            default:
               out.append(':');
        }
        for(int K = 0; K < (int)(histograme[I]/histogram[mode]); K++)
        out.append('.')
    }
      out.append('\n');
}
