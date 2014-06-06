//remap color image to grayscale via 3-d distace formula
//grayscale filter

import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.vecmath.Vector3d;

public class intesityMap{ 

  public static BufferedImage img, possesedImg;
  
  public static void main(String[] args){
    openImageFile(args[0]);
    possesedImg = converter(img);
    saveImageFile(args[1]);
    
    System.exit(0);
  }
  
  public static BufferedImage converter(BufferedImage img){
    BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight());
    
    for(int I = 0; img.getWidth(); I++){
      for(int J = 0; img.getHeight(); J++){
        Vector3d v = quantizePixle(img, I, J);
        double tmp = v.length() / (Math.pow(3, 0.5)*256);
        
        Color nuColor = new Color(tmp, tmp, tmp);
        out.setRGB(I, J, nuColor.getRGB());
      }
    }
    
    return out;
  }

  public static void openImageFile(String fileName){
       try {
         img = ImageIO.read(new File(fileName));
       } catch (IOException e) { }
       
  }
  
  public static void saveImageFile(String fileName){
      try {
        File outputfile = new File(fileName);
        ImageIO.write(possesedImg, "png", outputfile);
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
