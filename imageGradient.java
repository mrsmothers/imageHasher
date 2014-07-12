//calculate the intesity gradiant of an image using the Sobel method
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.Transparency;
import java.awt.Color;
import java.io.*;
import javax.imageio.* ;
import java.awt.image.ConvolveOp;

public class imageGradient{
    private Kernel xKernel, yKernel;
    private ConvolveOp xConvolve, yConvolve;
    private final static float[] xSobel = {-1, 0 , 1, -2, 0, 2, -1, 0, 1};
    private final static float[] ySobel = {1, 2, 1, 0, 0, 0, -1, -2, -1};
    
    public imageGradient(){
        xKernel = new Kernel(3, 3, xSobel);
        yKernel = new Kernel(3, 3, ySobel);
        
        xConvolve = new ConvolveOp(xKernel, ConvolveOp.EDGE_NO_OP, null);
        yConvolve = new ConvolveOp(yKernel, ConvolveOp.EDGE_NO_OP, null);
    }
    
    public BufferedImage prosses(BufferedImage img){
        BufferedImage xGradient, yGradient;
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), Transparency.TRANSLUCENT);
        
        BufferedImage im = intensityMap.remap(img);
        
        
        xGradient = xConvolve.filter(im, null);
        yGradient = yConvolve.filter(im, null);
        saveImageFile(xGradient,"duh");
        
        for(int I = 0; I<im.getWidth(); I++){
            for(int J = 0; J<im.getHeight(); J++){
                int a , b , n;
                a = quantizePixle(xGradient, I , J)[0];
                b = quantizePixle(yGradient, I , J)[0];
                
                n = (int)(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), .5));
                if(n>255) n = 255;
				if(n<0) n = 0;
                Color color = new Color(n, n, n);
                
                out.setRGB(I, J, color.getRGB());
			}
        }
        
        return out;
    }
    
    public static void main(String[] args){
        BufferedImage img = openImageFile(args[0]);
        BufferedImage out;
        
        imageGradient ig = new imageGradient();
        
        out = ig.prosses(img);
        
        saveImageFile(out, args[0]+".gradient");
    }

  public static int[] quantizePixle(BufferedImage img, int x, int y){
    int clr =  img.getRGB(x, y); 
                 //    red                  //     green             //       blue
    int[]  out = {(clr & 0x00ff0000) >> 16, (clr & 0x0000ff00) >> 8, clr & 0x000000ff};
    return out;
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
