package SuperMarioBros;

import java.awt.Point;
/*
 * Camera
 * Helps with positioning of objects however the offset in the end is a bit off
 */
public class Camera
{
  private Point position;
  private int width;
  private int height;
  private Point center;  
  public Camera(int x, int y, int width, int height)
  {
    this.position = new Point(x,y);
    this.width = width;
    this.height = height;   
    this.center = new Point(x+(int)(width/2f),y+(int)(width/2f));
  }
  public Point getPosition()
  {
    return this.position;
  } 
  public Point getCenter()
  {
    return this.center;
  }
  public int getWidth()
  {
    return this.width;
  }
  public int getHeight()
  {
    return this.height; 
  } 
  public void setPosition(int x, int y)
  {
    this.position.x = x;
    this.position.y = y;
  }
  public void setPosition(Point position)
  {
    this.position = position;
  } 
  public void move(int x, int y)
  {
    this.position.x += x;
    this.position.y += y;
  }
  public void move(Point amount)
  {
    this.position.x += amount.x;
    this.position.y += amount.y;
  }
  public void follow(Character character)
  {
    Point target = character.getWorldPosition();
    Point min = new Point(this.position.x+(int)(this.width * 0.3f),this.position.y-(int)(this.height*0.2f));
    Point max = new Point(this.position.x+(int)(this.width * 0.3f),this.position.y-(int)(this.height*0.5f)+this.position.y);   
    if (target.x < min.x)
      this.position.x += target.x - min.x;
    else if (character.getWorldPosition().x > max.x) 
     this.position.x = target.x - max.x;    
    if (this.position.x < 0)
      this.position.x = 0;
  }
}