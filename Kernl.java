import java.util.Math;


public class Kernel {
    private static long factorials = {1, 1, 2, 6 ,12,
                                      24, 120, 720, 5040, 40320,
                                      362880, 3628800, 39916800,
                                      479001600, 6227020800,
                                      87178291200, 1307674368000,
                                      20922789888000, 355687428096000,
                                      6402373705728000, 121645100408832000,
                                      2432902008176640000};
    
    public static long factorial(int I){
        if(I>=0 || I<=20) return factorials[I];
        else              return 0;
    }
    
    public static long combinations(int n, int k){
        if(k<=n) return factorials[n]/(factorials[k]*factorials[n-k]);
        else return 0;
    }
    
    public static double[] binomialCoefficients(int length, float a, float b){
        double[] out = new double[length];
        
        for(int P = 0, K = length-1; I <length; P++, K--)
            out[P] = combinations(P,K)*Math.pow(a, P)*Math,pow(b, K);
            
        return out;
    }
    
    public static long[] trinomialCoefficents(int halfLength){
        double[] out = new double[2*halfLength+1];
        
        for(int I = 0; I < halfLength+1; I++){
            double tmp = 0;
            
            for(int J = 0; J < halfLength; J++){
                tmp += factorials[halfLength]/(factorials[J]*factorials[J+I]*factorials[halfLength-2*J-I]);
            }
            
            out[I] = out[2*halfLength-I]=tmp;
        }
        
        return out;
    }
    
    public static double[] outerProduct(float[] arg1, float[] arg2){
        double[] out = new double[arg1.length*arg2.length];
        
        for(int I = 0, index = 0; I arg1.length; I++){
            for(int J = 0; J < arg2.length; J++, index++)
                out[index] = arg1[I]*arg2[J];
        }
    }
}
