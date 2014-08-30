import java.awt.image.*;
import java.awt.Image;
import javax.imageio.*;
import java.io.IOException;
import java.io.File;

public SerailizeCardData {

    public static void main(String[] args){
        int numCards = 6;
        
        String[] cardNames = new String[numCards];
        String[] fileNames = new String[numCards];
        
        
        
		cardNames = new String[numCards];
		fileNames = new String[numCards];

		cardNames[0] = "Neal Patric Haris";
		fileNames[0] = "nph.jpg";

		cardNames[1] = "Charizard";
		fileNames[1] = "charizard.jpg";
	
		cardNames[2] = "Alloy Myr";
		fileNames[2] = "myr.jpg" ;
	
		cardNames[3] = "ogurki";
		fileNames[3] = "bomer.jpg";

		cardNames[4] = "Chuck Noris";
		fileNames[4] = "chuck.jpg";

		cardNames[5] = "Echo Mage";
		fileNames[5] = "magic-2.jpg";        
        
        
        
        for(int I = 0; I < numCards; I++){
            CardData cd = new CardData();
            
            //Set CardName
            cd.name = cardNames[I];
            
            //Get and Set Card Image
            BufferedImage img = null;
           
            try {
                img = ImageIO.read(new File(fileName));
            } catch(IOException e) { }   
           
           
            cd.hash = HistogramImageHash.prosses(img);
            
            
            //Serilize the Data
            try {
                FileOutputStream fileOut = new FileOutputStream(I+".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(e);
                out.close();
                fileOut.close();
            } catch(IOException i) { } 
        }
}

        
