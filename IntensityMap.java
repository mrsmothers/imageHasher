//remap color image to grayscale via 3-d distace formula
//grayscale filter

import java.awt.Image;
import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Arrays;

public class IntensityMap{ 
  
  public static BufferedImage remap(BufferedImage img){
    BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), Transparency.TRANSLUCENT);
    
    for(int I = 0; I < img.getHeight(); I++){
      for(int J = 0; J < img.getWidth(); J++){
        int[] v = quantizePixle(img, J, I);
        int tmp = (int)(vLength(v)/(Math.pow(3, 0.5)));

        Color nuColor = new Color(tmp, tmp, tmp);
        out.setRGB(J, I, nuColor.getRGB());
      }
    }
    
    return out;
  }
  
  public static void main(String[] args){
    BufferedImage img, prossesdImg;
    
    img = openImageFile(args[0]);
    prossesdImg = remap(img);
    saveImageFile(prossesdImg, args[0]+".intensity");
    
    System.exit(0);
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
  
  public static int[] quantizePixle(BufferedImage img, int x, int y){
    int clr   =  img.getRGB(x, y); 
    int red   = (clr & 0x00ff0000) >> 16;
    int green = (clr & 0x0000ff00) >> 8;
    int blue  =  clr & 0x000000ff;
    
    int[]  out = {red, green, blue};
    
    return out;
  }
  
  public static float vLength(float[] vector){
    double sum = 0;
    
    for(int I=0; I<vector.length; I++)
      sum += Math.pow(vector[I], 2);
      
    return (float)Math.pow(sum, 0.5);
  }

  public static float vLength(int[] vector){
    double sum = 0;
    
    for(int I=0; I<vector.length; I++)
      sum += Math.pow(vector[I], 2);
      
    return (float)Math.pow(sum, 0.5);
  }
  
  public static float[] vMultiply(float[] vector, float val){
    float[] out = new float[vector.length];
    
    for(int I=0; I<vector.length; I++)
      out[I] = vector[I] * val;
      
    return out;
  }
  
  public static float vDot(float[] arg1, float[] arg2){
    double out=0;
    
    for(int I=0;I<arg1.length;I++)
      out += arg1[I]*arg2[I];
      
    return (float) (out * ( 1 / ( vLength(arg1) * vLength(arg2) ) ));
  }
}
