package SuperMarioBros;
/*
 * 
 * SpriteLoader
 * Utility class used for loading images 
 * 
 */
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Hashtable;
@SuppressWarnings("unchecked")
public class SpriteLoader
{  
  //Used to stores Sprites once they are loaded
  //They are accesible the spriteName that was used to load it originally.
  //This will speed up spriteLoading, and reduce memory.
  Hashtable cache; 
 public SpriteLoader()
  {
    cache = new Hashtable();
  }
  public Sprite load(String spriteName)
  {
    if (cache != null)
    {
      if (cache.containsKey(spriteName))
            return (Sprite)cache.get(spriteName);
    } 
    BufferedImage sourceImage = null;
    try{
      sourceImage = ImageIO.read(new File(spriteName));
      System.out.println("this managed to load"+ spriteName);
    }
    catch(IOException e)
    {
      displayErrorMessage("There was an error loading the file '" + spriteName + "'. Please ensure the file exsists in the specified directory");
    }
    //Convert the loaded image into an internal java accelerated image format.
    GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    Image image = graphicsConfig.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(), Transparency.BITMASK);
    image.getGraphics().drawImage(sourceImage,0,0,null);
    //Load the accelerated image into a Sprite object for convinience.
    Sprite sprite = new Sprite(image,spriteName);
    cache.put(spriteName, sprite);
    return sprite;
  }
  private void displayErrorMessage(String message)
  {
    System.err.println(message);
    System.exit(0);
  }
}