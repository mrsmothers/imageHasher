import java.lang.Math;
import java.awt.image.ConvolveOp;
import java.awt.image.*;
import java.awt.Image;


public class Kerneler {
    private static long factorials[] = {1, 1, 2, 6 ,24, 120,
                                        720, 5040, 40320,
                                        362880, 3628800, 39916800,
                                        479001600, 6227020800l,
                                        87178291200l, 1307674368000l,
                                        20922789888000l, 355687428096000l,
                                        6402373705728000l,
                                        121645100408832000l,
                                        2432902008176640000l};
    
    public static long factorial(int I){
        if(I>=0 || I<=20) return factorials[I];
        else return 0;
    }
    
    public static long combinations(int n, int k){
        if(k<=n) return factorials[n]/(factorials[k]*factorials[n-k]);
		
        return 0;
    }
    
    public static float[] binomialCoefficients(int length, float a, float b){
        float[] out = new float[length];
        
        for(int P = 0, K = length-1; P < length; P++, K--)
            out[P] = (float)(combinations(length-1, P)*Math.pow(a, K)*Math.pow(b, P));
            
        return out;
    }
    

    
    public static float[] outerProduct(float[] arg1, float[] arg2){
        float[] out = new float[arg1.length*arg2.length];
        
        for(int I = 0, index = 0; I < arg1.length; I++){
            for(int J = 0; J < arg2.length; J++, index++)
                out[index] = arg1[I]*arg2[J];
        }

		return out;
    }

	public static void main(String[] args){
		BufferedImage out0;
		int width = 15;
		float lv = .9f;
		float rv = .1f;

		float[] bla0 = Kerneler.binomialCoefficients(width, lv, rv);

		float[] bla2 = Kerneler.outerProduct(bla0, bla0);

		Kernel kernel = new Kernel(width, width, bla2);
		ConvolveOp op0 = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		BufferedImage image = ImageHasher.openImageFile(args[0]);

		
		 out0 = op0.filter(image, null);
		ImageHasher.saveImageFile(out0, args[0]+".test1");


	}

}
