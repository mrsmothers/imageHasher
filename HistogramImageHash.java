import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.*;


public class HistogramImageHash{


	public static float[][] prosses(BufferedImage img){
		float out[][] = new float[3][256];
		BufferedImage gradient = ImageGradient.prosses(img);
		
		int kernalHalfWidth = (img.getWidth()-1)/2;
		int kernalWidth = kernalHalfWidth*2+1;
		
		float[] kernal = ImageHasher.gassianKernel(kernalHalfWidth , 1f);
		
		int kernalIndex = 0;
		for(int I = 0; I < kernalWidth;I++){
			for(int K = 0; K < kernalWidth; K++){
				out[0][((gradient.getRGB(I , K) & 0x00ff0000) >> 16)] += kernal[kernalIndex];
				out[1][((gradient.getRGB(I , K + (img.getHeight()-img.getWidth())/2) & 0x00ff0000) >> 16)] += kernal[kernalIndex];
				out[2][((gradient.getRGB(I , K + (img.getHeight()-img.getWidth())-1) & 0x00ff0000) >> 16)] += kernal[kernalIndex];
				kernalIndex++;
			}
		}
		System.out.println(Arrays.toString(kernal));
		return out;
	}
	
}
