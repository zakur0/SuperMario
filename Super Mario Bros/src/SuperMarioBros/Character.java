package SuperMarioBros;

 import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
/*
 * Character
 * This is the base class for making 'Characters' or 'Actors' for the world
 * This class should not ever need to be used, use subclasses instead
 */
public class Character  
{
  protected String name;
  protected Point position;
  protected Point worldPosition;
  protected int speed;
  protected  boolean isBonus = false;
  protected AnimatedSprite sprite;
  protected Point velocity;
  protected boolean isDead = false;
  //These are so we dont have a jet pack like effect
  protected boolean isJumping;
  protected boolean isontheGround=false;
  protected Direction direction = Direction.Right;
  //Used to make a character.
  public Character(String name, AnimatedSprite sprite, int x, int y, int speed)
  {
    this.name = name;
    this.sprite = sprite;
    this.position = new Point(x,y);
    this.speed = speed;
    this.worldPosition = new Point(0,0);
    this.velocity = new Point(0,0);
    this.isJumping = false; 
  }
  public void update(float gameTime, Camera camera)
  {
    if (isDead)
    {
      return;
    } 
    this.move(this.velocity);
    this.worldPosition.x = this.position.x + camera.getPosition().x;
    this.worldPosition.y = this.position.y + camera.getPosition().y;
    this.velocity.x *= 0.3f;
    this.velocity.y *= 0.9f;
    sprite.update(gameTime);
  }
  public void draw(Graphics graphics, Camera camera)
  {
    if (isDead && !name.equals("isFlowerMonster"))
        if(!isBonus)
            sprite.play("dead");
    if (direction == Direction.Right)
      sprite.draw(graphics,position.x - camera.getPosition().x, position.y - camera.getPosition().y);
    else
      sprite.drawFlipped(graphics,position.x - camera.getPosition().x, position.y - camera.getPosition().y);  
  } 
  public AnimatedSprite getSprite()
  {
    return sprite;
  }
  public void applyForce(int x, int y)
  {
    this.velocity.x += x;
    this.velocity.y += y;
  }
  public Point getForce()
  {
    return this.velocity;
  } 
  public void setForce(int x, int y)
  {
    this.velocity.x = x;
    this.velocity.y = y;
  }
  //Moves the character by a sepecific amount
  public void move(int x, int y)
  {
    this.position.x += x;
    this.position.y += y;
  } 
  //Moves the character by a specific amount
  public void move(Point amount)
  {
    this.position.x += amount.x;
    this.position.y += amount.y;
  }
  public void moveTo(int x, int y)
  {
    this.position.x = x;
    this.position.y = y;
  }
  //gets the character's position in pixel coords
  public Point getPosition()
  {
    return position;
  }
  
  public Point getWorldPosition()
  {
    return worldPosition;
  }
  //Gets the characer's position in tile coords
  public Point getTilePosition()
  {
    return new Point((this.position.x+32) / SideScroller.TILE_SIZE, this.position.y / SideScroller.TILE_SIZE);
    //return new Point((this.worldPosition.x+32) / SideScroller.TILE_SIZE, this.worldPosition.y / SideScroller.TILE_SIZE);
  } 
  public Rectangle getBounds()
  {
    //return new Rectangle(worldPosition.x, worldPosition.y, 64,128);
    //return new Rectangle(position.x, position.y, 64,128);
    return new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());
  }
  public void die()
  {
    this.isDead = true;
  }
  public boolean isAlive()
  {
    return !isDead;
  }
  public Direction getDirection()
  {
      return direction;
  }
  //So the hero gets more points
  public void AddtoScore(int add)
  {
  }
  //So the bonuses change route
  public void changeRoute()
  {
  }
}