import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

class imageHasher{ 

  public static Image img;
  public static void main(String[] args){
  
  openFile();
  //preprosess image:generate intensity map
  //apply gassian hash
  //output hash
  }

  public static void openFile(){
       try {
           img = ImageIO.read(new File(args[0]));
       } catch (IOException e) { }
       
  }



}

public class imageHash{
  private static int LAYERS = 1;
  public double[LAYERS][] members;
  
  
  public static imageHash hash(Image image){
  //apply gassian bluring function  
  
    return null;
  }
    
  }
