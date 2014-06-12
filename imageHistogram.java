// generates histograme of image
import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.vecmath.Vector3d;
import javax.media.jai.Histogram;

public class imageHistogram{
  
  public static void main(String[] args){
    BufferedImage img;
    Histogram histogram;
    
    histogram = new Histograme(3, 0, 256, 256);
    img = openImageFile(args[0]);
    
    histogram.countPixles(img, null, 0, 0);
    
    int[] tmp = histogram.getTotals();
    
    for(int I=0; I<tmp.length; I++){
      System.out.println(I+":"+tmp[I]);
    }
  }
  

  public static BufferedImage openImageFile(String fileName){
     BufferedImage img;
    
     try {
       img = ImageIO.read(new File(fileName));
     } catch (IOException e) { }
       
     return img;
       
  }
}
