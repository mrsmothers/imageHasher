import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

class imageComparer {
  public static void main(String[] args){
  
  System.out.println(comparer(args[0],args[1]);
  
  }
  
  public static double comparer(BufferedImage img1, BufferedImage img2){
    double number=0;
    
    for(int I = 0;I<img1.getHeight(); I++){
      for(int J = 0; J<img1.getWidth(); J++){
        number+= Math.pow(
    return 0.0;
  }


  public static void openImageFile(String fileName){
       try {
           img = ImageIO.read(new File(fileName));
       } catch (IOException e) { }
       
  }


}



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


class Main {
    public static void main(String[] args) throws IOException {

        final int SCALE = 2;

        Image img = new ImageIcon("duke.png").getImage();

        BufferedImage bi = new BufferedImage(SCALE * img.getWidth(null),
                                             SCALE * img.getHeight(null),
                                             BufferedImage.TYPE_INT_ARGB);

        Graphics2D grph = (Graphics2D) bi.getGraphics();
        grph.scale(SCALE, SCALE);

        // everything drawn with grph from now on will get scaled.

        grph.drawImage(img, 0, 0, null);
        grph.dispose();

        ImageIO.write(bi, "png", new File("duke_double_size.png"));
    }
}
