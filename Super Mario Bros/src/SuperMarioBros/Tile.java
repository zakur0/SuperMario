package SuperMarioBros;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
/*
 * The Tile class contains ways to handle tiles, which are 64x64pixel and are what the map is made out of. They have a Sprite to represent them
 */
public class Tile
{
  //Fields
  TileType tileType;
  Sprite sprite;
  AnimatedSprite anime;
  int TileMoney;
  boolean hasItem=false;
  //Constructor
  public Tile(Sprite sprite, TileType tileType )
  {
    this.sprite = sprite;
    this.tileType = tileType;
    if(tileType == TileType.MoneyBox)
    	TileMoney=12;
    if(     tileType == TileType.ShroomBox||
            tileType == TileType.HpupBox||
            tileType == TileType.FireFlowerBox||
            tileType == TileType.StarBox||
            tileType == TileType.MoneyBox)
    	hasItem = true; 
  }
  //Draws the tile at tehe specified position
  public void draw(Graphics graphics, int x, int y)
  {
    this.sprite.draw(graphics,x,y);
  }  
  public String getImagePath()
  {
    return this.sprite.getImagePath();
  }
  //Gets the TileType of the tile.
  public TileType getTileType()
  {
    return tileType;
  }
  //Returns True if the tile kills any character that touches it (IE Lava, Water, Spikes....)
  public boolean killsCharacter()
  {
    return tileType == TileType.Deadly;
  } 
  //Returns True if the tile is a 'cloud' tile. This does not mean it is litterally a cloud, it just means that you can walk through it, jump over it,
  //but if you land on it from the top it behaves like a solid tile
  public boolean isCloud()
  {
    return tileType == TileType.Cloud;
  }
  public boolean hasShroom(){
	  return tileType == TileType.ShroomBox;
  }
  public void gotTheItem(){
	  hasItem=false;
	  tileType = TileType.Solid;
  }
  public boolean hasMoney(){
	  return tileType == TileType.MoneyBox;
  }
  public boolean hasHpUp(){
	  return tileType == tileType.HpupBox;
  }
  public boolean hasStar(){
	  return tileType == TileType.StarBox;
  }
  public boolean hasFireFlower(){
	  return tileType == TileType.FireFlowerBox;
  }
  public boolean isBreakable(){
	  return tileType == TileType.Breakable;
  }
  public void gottheMoney(){
	  if(tileType == TileType.MoneyBox && TileMoney>0)
		  TileMoney--;
	  else return;
  }
  public void destroyBlock(){
	  if(tileType == TileType.Breakable)
		  tileType = TileType.Blank;
	  
  }
  public boolean isBlank(){
	  return tileType == TileType.Blank;
  }
  //NO NEED but if we would the other levels it would be usefull
 /* public boolean canBePushed(){
    if(tileType!= TileType.Blank || tileType!= TileType.Cloud || tileType!= TileType.Deadly || tileType!= TileType.Solid){
	    	return  true;
    }else{ 
    	return false;
    }
  }
 */
  public boolean IsMaterialized(){
	  if(tileType == TileType.Solid)	 
		  return true;
	  else if(tileType == TileType.MoneyBox)	 
		  return true;
	  else if(tileType == TileType.Breakable)
		  return true;
	  else if(tileType == TileType.FireFlowerBox)	 
		  return true;
	  else if(tileType == TileType.HpupBox)	 
		  return true;
	  else if(tileType == TileType.ShroomBox)	 
		  return true;
	  else if(tileType == TileType.StarBox)	 
		  return true;
	  else return false;
  }
}

