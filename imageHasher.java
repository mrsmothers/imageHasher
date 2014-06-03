import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

class imageHasher{ 

  public static Image img, possesedImg;
  public static void main(String[] args){
  
  openImageFile(args[0]);
  prossesImage(img);
  
  //preprosess image:generate intensity map
  //apply gassian hash
  //output hash
  }

  public static void openImageFile(String fileName){
       try {
           img = ImageIO.read(new File(fileName));
       } catch (IOException e) { }
       
  }



}

public class imageHash{
  public double[] map;
  
  
  public static imageHash hash(Image image){
  //apply gassian bluring function  
  
    return null;
  }
    
  }
