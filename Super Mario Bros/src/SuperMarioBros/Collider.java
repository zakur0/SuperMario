package SuperMarioBros;

import java.awt.Point;
import java.awt.Rectangle;
/* 
 * Using Point Collision
 * The Point is set to the top Left of the rectangle so we have to be more carefull
 * 
*/
public class Collider
{
    private Point topTile;
    private Point topLeftTile;
    private Point bottomLeftTile;
    private Point leftTile;
    private Point rightTile;
    private Point bottomTile;
  public Collider()
  {  
  }
  public void collide(Character character, Map map)
  {
    if (!character.isAlive())
      return;
    topLeftTile = new Point(character.getTilePosition().x-1,character.getTilePosition().y);
    bottomLeftTile = new Point(character.getTilePosition().x-1,character.getTilePosition().y+1);
    bottomTile = new Point(character.getTilePosition().x,character.getTilePosition().y+1);
    topTile = new Point(character.getTilePosition().x,character.getTilePosition().y);
    rightTile = new Point(character.getTilePosition().x,character.getTilePosition().y);
    leftTile = new Point(character.getTilePosition().x-1,character.getTilePosition().y);
    if(map.getTile(bottomTile).IsMaterialized() || map.getTile(bottomLeftTile).IsMaterialized())
     {
          character.moveTo(character.getPosition().x, bottomTile.y*SideScroller.TILE_SIZE-character.getSprite().getHeight());          
          if(character.isJumping && character.isontheGround)         
             character.isJumping = false;
            character.isontheGround = true;         
    }
    if(map.getTile(topTile).IsMaterialized()||map.getTile(topLeftTile).IsMaterialized() )
    {
        character.applyForce(0, -character.getForce().y);
    } 
    
    //So we dont get out of bounds
   if(character.getPosition().x-SideScroller.TILE_SIZE > 0 && character.direction == Direction.Left|| 
      character.getPosition().x+SideScroller.TILE_SIZE  <= 210*SideScroller.TILE_SIZE && character.direction == Direction.Right)
   {
    if(map.getTile(rightTile).IsMaterialized() && character.direction == Direction.Right )
    {
         if(character.isBonus)
            character.changeRoute();
        character.applyForce(-character.getForce().x, 0);
    }
    if(map.getTile(leftTile).IsMaterialized() && character.direction == Direction.Left)
    {
        if(character.isBonus)
            character.changeRoute();
       character.applyForce(-character.getForce().x,0);     
    }
   }else 
       character.applyForce(-character.getForce().x, 0);
    if(map.getTile(character.getTilePosition()).killsCharacter())
    {
        character.die();      
    }
}
  public int collide(Character characterA, Character characterB)
  {
    int retVal = 0;
    if ((characterA.getPosition().x <= characterB.getPosition().x+characterB.getBounds().width)
       && (characterA.getPosition().x + characterA.getBounds().width >= characterB.getPosition().x))
    {
      //They are intersecting X coordinants, lets check and see if we have a collision by checking vertical too
      if ((characterA.getPosition().y <= characterB.getPosition().y+characterB.getBounds().height)
       && (characterA.getPosition().y + characterA.getBounds().height >= characterB.getPosition().y))
      {
        if (characterA.getPosition().y < characterB.getPosition().y)
          retVal = 1;
        else
          retVal = -1;
      }
    }
    return retVal;
  }
  //check bonus box collision if it has an item it takes it from and if it has money it reduces the amount
    public Tile collideWithSpBox(Character character,Map map)
    {
      //if its not a plain tile
          if( map.getTile(topTile).hasItem  )
          {  
                if(map.getTile(topTile).tileType == TileType.MoneyBox  )
                {  
                    if(map.getTile(topTile).TileMoney>0 )
                    {
                        character.AddtoScore(1);
                        map.getTile(topTile).gottheMoney(); 
                        return map.getTile(topTile);
                    }else return null;  
                }      
               return map.getTile(topTile);
          } else if( map.getTile(topLeftTile).hasItem  )
          {
               if(map.getTile(topLeftTile).tileType == TileType.MoneyBox  )
               {            
                    if(map.getTile(topLeftTile).TileMoney>0 )
                    {
                        character.AddtoScore(1);
                        map.getTile(topLeftTile).gottheMoney();                     
                        return map.getTile(topLeftTile);
                    }else return null;           
                }
              return  map.getTile(topLeftTile);     
          }else 
              return null;
    }
      public boolean collide(Character character, Goal goal)
    {
    if ((character.getPosition().x+SideScroller.TILE_SIZE == goal.x))
    {
    return true;
    }
    return false;
  } 
      //checking bonus collision on x and y axis
    public boolean collideswithbonus(Character character1,Character character2)
    {
    	if((character1.getTilePosition().x == character2.getTilePosition().x) &&
          (character1.getTilePosition().y == character2.getTilePosition().y))
 		return true;
    	else return false;
    }
}