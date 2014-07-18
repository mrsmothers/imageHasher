import java.awt.image.*;
import java.awt.Image.BufferedImage;


public class HistogramImageHash{


	public static float[][] prosses(BufferedImage img){
		float out[] = new float[3][256];
		BufferedImage gradient = imageGradient(img);

		float[] kernal = ImageHasher.gassianKernal((img.getWidth()-1)/2, 1);
		
		int kernalIndex = 0;
		for(int I = 0; I < img.getWidth();I++){
			for(int K = 0; K < img.getWidth(); K++){
				out[0][((gradient.getRGB(I , K) & x00ff0000) >> 16)] += kernal[kernalIndex];
				out[1][((gradient.getRGB(I , K + (img.getHeight()-img.getWidth())/2) & x00ff0000) >> 16)] += kernal[kernalIndex];
				out[2][((gradient.getRGB(I , K + (img.getHeight()-img.getWidth())-1) & x00ff0000) >> 16)] += kernal[kernalIndex];
				kernalIndex++;
			}
		}

		return out;
}

