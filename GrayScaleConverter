
/**
 *  The following code converts images into its grayscale (Black & White ) and saves them for you.
     Best of luck!
 * 
 * @author YASAR
 * @version 10/08/2020
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    
    public ImageResource makeGray(ImageResource inImage)
    { 
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for (Pixel pixel : outImage.pixels() )
        {
          Pixel inPixel =  inImage.getPixel(pixel.getX(),pixel.getY());         //GETTING CORRESPONDING PIXELS FROM inImage.
          
          int average = (inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
          
          pixel.setRed(average);                                  // SETTING THE PIXEL VALUES TO AVERAGE (RED = GREEN = BLUE = AVERAGE)
          
          pixel.setGreen(average);
          
          pixel.setBlue(average);
        
        }
    return outImage;
    }

    // METHOD TO CALL COVERT IMAGE INTO ITS GRAYSCALE AND SAVE IT.
    public void convertandsave() 
    {
        DirectoryResource dr= new DirectoryResource();
        
        for( File f : dr.selectedFiles())
        {
        ImageResource image = new ImageResource(f);
        
        String name = image.getFileName();                          //METHOD TO GET NAME OF THE IMAGE.
        ImageResource gray = makeGray(image);
        String newname = "grayscale-"+name;
        gray.setFileName(newname);
        gray.draw();                                           // DRAWS THE IMAGE ON THE SCREEN.
        gray.save();                                             //SAVES THE IMAGE.
       }
        
        
    }
}
