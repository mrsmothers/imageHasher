//calculate the intesity gradiant of an image using the Sobel method
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.Transparency;
import java.awt.Color;
import java.io.*;
import javax.imageio.* ;
import java.awt.image.ConvolveOp;



public class ImageGradient{
    private final static float[] xSobel = {-1, 0 , 1, -2, 0, 2, -1, 0, 1};//3x3 mask arrays
    private final static float[] ySobel = {1, 2, 1, 0, 0, 0, -1, -2, -1};
    
    private final static float[] cross1 = {1, 0, 0, -1};//2x2 mask arrays
    private final static float[] cross2 = {0, 1, -1, 0};

    public static BufferedImage prosses(BufferedImage img){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), Transparency.TRANSLUCENT);
        
        BufferedImage im = IntensityMap.remap(img);//create input image gray scale
        
        
        for(int I = 0; I<im.getWidth()-2; I++){    //itterate through all the pixles of the image
            for(int J = 0; J<im.getHeight()-2; J++){
            	
                int a , b , n;
                a =(int) mask(im, I, J, xSobel, 3);//dy
                b =(int) mask(im, I, J, ySobel, 3);//dx
                
                n = (int)(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), .5));//sqrt(a^2+b^2)
                
                if(n>255) n = 255;
                
                Color color = new Color(n, n, n);
                out.setRGB(I, J, color.getRGB());
			}
        }
        
        return out;
    }

    public static BufferedImage cross(BufferedImage img){
        int[][] xGradient, yGradient;
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), Transparency.TRANSLUCENT);
        
        BufferedImage im = IntensityMap.remap(img);
        
        
        for(int I = 0; I<im.getWidth()-1; I++){
            for(int J = 0; J<im.getHeight()-1; J++){
                int a , b , n;
                a =(int) mask(im, I, J, cross1, 2);//dx
                b =(int) mask(im, I, J, cross2, 2);//dy
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
        BufferedImage out1, out2, out3;
                
        out1 = ImageGradient.prosses(img);
		out2 = ImageGradient.cross(img);
		out3 = ImageHasher.hash(img,3, 1.41f);
		out3 = ImageGradient.cross(out3);
        
        saveImageFile(out1, args[0]+".gradient");
		
		saveImageFile(out2, args[0]+".cross");
    }
    
    //applys a wndow funtion to a portion of an image
    //a weighted summation of the red band is perfomed
    
  ubli  pc static float mask(BufferedImage img, int x0, int y0, float[] bla, int width){
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

    private static BufferedImage openImageFile(String fileName){
       BufferedImage img = null;
       
       try {
           img = ImageIO.read(new File(fileName));
       } catch (IOException e) { }

       return img;
       
  }
  
    private static void saveImageFile(BufferedImage img, String fileName){
      try {
       	File outputfile = new File(fileName);
	       ImageIO.write(img, "png", outputfile);
       } catch (IOException e) { }
  }
}
