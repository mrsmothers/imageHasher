// generates histograme of image
import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.lang.Math;


public class imageHistogram{
	int mode;
	int entries;
	int[] histogram;
  
  
  
	public static void main(String[] args){
		BufferedImage img = openImageFile(args[0]);
		imageHistogram ih = new imageHistogram(img);
	
		System.out.println(ih.print(50));

  	}

	public imageHistogram(BufferedImage img){
		mode = 0;
		entries++;
		histogram = prosses(img);

	}	
  
  public int[] prosses(BufferedImage img){
    int[] out = new int[256];
    
    Arrays.fill(out, 0);
    
    for(int I = 0; I < img.getHeight(); I++){
      for(int J = 0; J < img.getWidth(); J++){
        int[] v = quantizePixle(img, J, I);
	 float[] vnu = { v[0], v[1], v[2]};
        int tmp = (int)(vLength((vnu))/(Math.pow(3, 0.5)));

        out[tmp]++;
		enteies++;

		if(out[tmp] > out[mode])
			mode = tmp;
      }
    }
    
    return out;
  }
  
  public static BufferedImage openImageFile(String fileName){
     BufferedImage img = null;
    
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

  public static float vLength(float[] vector){
    double sum = 0;
    
    for(int I=0; I<vector.length; I++)
      sum += Math.pow(vector[I], 2);
      
    return (float)Math.pow(sum, 0.5);
  } 
  
     public String print(int width){
         StringBuilder out = new StringBuilder();
         
         //print first line of index
         out.append("   |");
         for(int I=0;I<width;I++)
            out.append(""+((I+1)%10));
            
        out.append('\n');
        
        //print out the data
	for(int I = 0; I < histogram.length; I++){
	out.append(I+1);
	switch((int)Math.log10(I+1)){
    		case 0:
        		out.append("  |");
        		break;
            	case 1:
                	out.append(" |");
               		break;
            	default:
               		out.append('|');
        	}
        	for(int K = 0; K < (int)(width*histogram[I]/histogram[mode]); K++){
        		out.append('.');
			}
			out.append('\n');
		}
		return out.toString();
	}
}
