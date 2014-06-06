import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.vecmath.Vector3d;

class imageComparer {
  
  public static void main(String[] args){
    System.out.println(comparer(args[0], args[1]);
  }
  //campare each
  public static double comparer(BufferedImage img1, BufferedImage img2){
    double sum = 0;
    
    for(int I = 0; I < img1.getHeight(); I++){
      for(int J = 0; J < img1.getWidth(); J++){
        Vector3d v1, v2;
        
        v1 = quantizePixle(img1, I, J);
        v2 = quantizePixle(img2, I, J);
        
        v1.sub(v2);
        
        sum += Math.abs(v1.length());
      }
    }

    return 1 - sum / ( img1.getWidth() * img2.getHeight() * 256);
  }


  public static void openImageFile(String fileName){
       try {
           img = ImageIO.read(new File(fileName));
       } catch (IOException e) { }
  }
  
  public static Vector3d quantizePixle(BufferedImage img, int x, int y){
    int clr   =  img.getRGB(x, y); 
    int red   = (clr & 0x00ff0000) >> 16;
    int green = (clr & 0x0000ff00) >> 8;
    int blue  =  clr & 0x000000ff;
    
    Vector3d out = new Vector3d(red, green, blue);
    
    return out;
  }
}
