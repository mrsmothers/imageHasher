//calculate the intesity gradiant of an image using the sobel method
import java.awt.Image.BufferedImage;
import java.awt.image.*;
import java.io.*;
import javax.imageio.;

public class imageGradiant{
    private Kernal xKernal, yKernal;
    private ConvlolutionOP xConvolve, yConvovle
    private final static int[] xSobel = {-1, 0 , 1, -2, 0, 2, -1, 0, 1};
    private final static int[] ySobel = {1, 2, 1, 0, 0, 0, -1, -2, -1};
    
    public imageGradiant(){
        xKernal = new Kernal(3, 3, xSobel);
        yKernal = new Kernal(3, 3, ySobel);
        
        xConvolve = new ConvolutionOP(xKernal, EDGE_NO_OP, null);
        yConvolve = new ConvolutionOP(yKernal, EDGE_NO_OP, null);
    }
    
    public BufferedImage prosses(BufferedImage img){
        BufferedImage xGradient, yGradiant;
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), Transparency.TRANSLUCENT);
        
        BufferedImage intesityMap = intensityMap.remap(img);
        
        xGradiant = xConvolve.filter(intesityMap, null);
        yGradiant = yConvolve.filter(intesityMap, null);
        
        for(int I = 0; I<intesityMap.getWidth(); I++){
            for(int K = 0; K<intesityMap.getHeight(); K++)
                int a , b , n;
                a = qantizePixle(xGradiant, I , J)[0];
                b = qantizePixle(yGradiant, I , J)[0];
                
                n = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), .5);
                
                Color color = new Color(n, n, n,);
                
                out.setRGB(I, J, color.getRGB());
        }
        
        return out;
    }
    
    public static void main(String[] args){
        BufferedImage img = openImageFile(args[0]);
        BufferedImage out;
        
        imageGradient ig = new imageGradent();
        
        out = ig.prossses(img);
        
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
