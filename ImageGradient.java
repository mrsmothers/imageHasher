//calculate the intesity gradiant of an image using the Sobel method
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.Transparency;
import java.awt.Color;
import java.io.*;
import javax.imageio.* ;
import java.awt.image.ConvolveOp;

public class ImageGradient{
    private final static float[] xSobel = {-1, 0 , 1, -2, 0, 2, -1, 0, 1};
    private final static float[] ySobel = {1, 2, 1, 0, 0, 0, -1, -2, -1};

    public static BufferedImage prosses(BufferedImage img){
        int[][] xGradient, yGradient;
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), Transparency.TRANSLUCENT);
        
        BufferedImage im = IntensityMap.remap(img);
        
        
        for(int I = 0; I<im.getWidth()-2; I++){
            for(int J = 0; J<im.getHeight()-2; J++){
                int a , b , n;
                a =(int) mask(im, I, J, xSobel, 3);
                b =(int) mask(im, I, J, ySobel, 3);
                n = (int)(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), .5));//sqrt(a^2+b^2)
                
                if(n>255) n = 255;
                
                Color color = new Color(n, n, n);
                out.setRGB(I, J, color.getRGB());
			}
        }
        
        return out;
    }
    
    public static void main(String[] args){
        BufferedImage img = openImageFile(args[0]);
        BufferedImage out;
                
        out = ImageGradient.prosses(img);
        
        saveImageFile(out, args[0]+".gradient");
    }
    
    //applys a wndow funtion to a portion of an image
    //a weighted summation of the red band is perfomed
    
    public static float mask(BufferedImage img, int x0, int y0, float[] bla, int width){
    	int index = 0;
    	float out =0;
    	
    	for(int I = x0; I < x0 + width;I++){
    		for(int J = y0; J < y0 + width;J++){
    			int pixle = img.getRGB(I, J);
    			out += bla[index++] * ((pixle & 0x00ff0000) >> 16);
    		}
    	}
    	return out;
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
