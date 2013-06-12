package SuperMarioBros;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
/*
 * AnimatedSprite
 * The AnimatedSprite class is a helper class to allow animations to be made without too much effort
 * They update to the next frame automatically, all that is need is to insert animations, and tell it what animation to play.
 */
 @SuppressWarnings("unchecked")
public class AnimatedSprite
{
  //stores the images that represent each frame of the animation
  private ArrayList images;
  private int currentFrame;
  private int currentAnimationFrame;
  private boolean isPlaying, isLooping;
  private int width, height;
  private float elapsedTime = 300;
  private float timePerFrame = 100f;
  private Hashtable animations;
  private int[] currentFrames;
  //Constructs a new animated sprite with no frames.
  //NOTE: Do not play the animation until at least 1 frame has been added.
  public AnimatedSprite(float timePerFrame, boolean startPlaying,boolean isLooping)
  {
    animations = new Hashtable();
    images = new ArrayList();
    this.timePerFrame = timePerFrame;
    this.isPlaying = startPlaying;
    this.isLooping = isLooping;
  }
  //Constructs a new sprite from an array of images
  public AnimatedSprite(Sprite[] images)
  {
     
    for (int i = 0; i < images.length; i++) 
      this.images.add(images[i]);
 }
  //Adds an image to the end of the frame
  public void addFrame(Sprite image)
  {
    this.images.add(image);
    recalcDimensions();
  }
  public void addAnimation(String name, int[] frames)
  {
    animations.put(name, frames);
  }
  //Gets the Width of this sprite
  public int getWidth()
  {
    return width;
  } 
  //Gets the Height of this sprite
  public int getHeight()
  {
    return height;
  }
  //Allows easy drawing of this sprite at any position
  public void draw(Graphics graphics, int x, int y)
  {
    ((Sprite)images.get(currentFrame)).draw(graphics,x,y);
    //graphics.drawImage((Image)images.get(currentFrame),x,y,null);
  }
  //Sets the Sprite to play, advancing frames a the specified speed (timePerFrame)
  public void play()
  {
    this.isPlaying = true;
    this.currentFrames = null;
  }
  //Sets the Sprite to play, advancing frames a the specified speed (timePerFrame)
  public void play(String animationName)
  {
    this.isPlaying = true;
    this.currentFrames = (int[])animations.get(animationName);
    this.currentAnimationFrame = 0;
    this.currentFrame = currentFrames[0];
  }
  public boolean isPlaying(String animationName)
  {
    return currentFrames == (int[])animations.get(animationName);
  }
  //Sets the Sprite to stop playing, but remember the frame the animation is on
  public void pause()
  {
    this.isPlaying = false;
  }
  //Sets if the sprite should loop the animation (IE Go back to the beginging and play again once it is finished)
  public void setLooping(boolean loop)
  {
    this.isLooping = loop;
  }
  //Start playing the sprite, but from a specific frame
  public void playFromFrame(int frame)
  {
    this.goToFrame(frame);
    this.play();
  }
  //Sets the frame currently being 'played' or displayed by the sprite. This method ensures that a valid frame is selected (assuming you enter a number > 0)
  public void goToFrame(int frame)
  {
    this.currentFrame = frame;  
    if (currentFrame > images.size()-1)
    {
      if (isLooping == true)
        currentFrame = 0;
      else
        currentFrame = images.size() -1;
    }
  } 
  //Updates the Animation
  public void update(float gameTime)
  {
    if (!isPlaying)
      return;
    //Increase the elapsed time by the time 
    elapsedTime += gameTime;
    if (elapsedTime > timePerFrame)
    {
     elapsedTime -= timePerFrame;
     if (currentFrames == null)
       goToFrame(currentFrame+1);
     else
     {
       currentAnimationFrame += 1;
       if (currentAnimationFrame >= currentFrames.length-1 && isLooping)
         currentAnimationFrame = 0;
       goToFrame(currentFrames[currentAnimationFrame]);
     }
    }
  } 
  //Allows easy drawing of this sprite at any position
  public void draw(Graphics graphics, Point position)
  {
    ((Sprite)images.get(currentFrame)).draw(graphics,position);
    //graphics.drawImage((Image)images.get(currentFrame),position.x,position.y,null);
  }
  //Allows easy drawing of this sprite at any position
  public void drawFlipped(Graphics graphics, Point position)
  {
    ((Sprite)images.get(currentFrame)).drawFlipped(graphics,position);
    //graphics.drawImage((Image)images.get(currentFrame),position.x,position.y,null);
  }
  //Allows easy drawing of this sprite at any position
  public void drawFlipped(Graphics graphics, int x, int y)
  {
    ((Sprite)images.get(currentFrame)).drawFlipped(graphics,x,y);
    //graphics.drawImage((Image)images.get(currentFrame),position.x,position.y,null);
  }
  //recalculates the width and height of the AnimatedSprite to match the new largest width and height
  private void recalcDimensions()
  {
    //Store the first image sizes for sure
    width = getImageWidth(0);
    height = getImageHeight(0);  
    //Loop through the rest of the images
    for(int i = 1; i < images.size(); i++)
    {
      //If we've found a wider image reset the width
      if (getImageWidth(i) > width)
        width = getImageWidth(i);
      //If we've found a taller image, reset the height
      if (getImageHeight(i) > height)
        height = getImageHeight(i);
    }
  } 
  private int getImageWidth(int index)
  {
    return ((Sprite)images.get(index)).getWidth();
  }
  private int getImageHeight(int index)
  {
    return ((Sprite)images.get(index)).getHeight();
  }
}