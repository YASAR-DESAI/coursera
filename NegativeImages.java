
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
public class NegativeImages {
    
    
    
    public ImageResource makeNegative(ImageResource inImage)
    { 
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for (Pixel pixel : outImage.pixels() )
        {
          Pixel inPixel =  inImage.getPixel(pixel.getX(),pixel.getY());
          
          pixel.setRed(255-inPixel.getRed());
          
          pixel.setGreen(255-inPixel.getGreen());
          
          pixel.setBlue(255-inPixel.getBlue());
        
        }
    return outImage;
    }

    
    public void convertandsave()
    {
        DirectoryResource dr= new DirectoryResource();
        
        for( File f : dr.selectedFiles())
        {
        ImageResource image = new ImageResource(f);
        
        String name = image.getFileName();
        ImageResource negative = makeNegative(image);
        String newname = "Negative-"+name;
        negative.setFileName(newname);
        negative.draw();
        negative.save();
       }
        
        
    }
}



