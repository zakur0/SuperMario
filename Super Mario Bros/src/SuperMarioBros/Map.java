package SuperMarioBros;

import java.awt.Graphics;
import java.awt.Point;
import java.io.*;
public class Map
{
  //2D Array of tiles that represents the map.
  Tile[][] tiles;
  int width;
  int height;
   //Create a new map with specific dimensions
  public Map(SpriteLoader spriteLoader, int width, int height)
  {
    tiles = new Tile[width][height];  
    this.width = width;
    this.height = height;
  } 
  public void setTile(int x, int y, Tile tile)
  {
    tiles[x][y] = tile;
  }
  //Gets the tile at the specified (x,y) position
  public Tile getTile(int x, int y)
  {
    return tiles[x][y];
  }
  //Gets the tile at the specified tile coord (x,y)
  public Tile getTile(Point tileCoord)
  {
    return tiles[tileCoord.x][tileCoord.y];
  }
  //Gets the tile at the specified pixel position
  public Tile getTileAtPosition(Point point)
  {
    //Look into making sure this will return the correct tile.
    return tiles[point.y/SideScroller.TILE_SIZE][point.y/SideScroller.TILE_SIZE];
  }
  //Gets the tile at the specified pixel position
  public Tile getTileAtPosition(int x, int y)
  {
    //Look into making sure this will return the correct tile.
    return tiles[x/SideScroller.TILE_SIZE][y/SideScroller.TILE_SIZE];
  }
  //Draws all of the tiles at their specific positions
  public void draw(Graphics graphics, Point position)
  {
    this.draw(graphics,-position.x, -position.y);
  }
  //Draws all of the tiles at their specific positions
  public void draw(Graphics graphics, int offsetX, int offsetY)
  {
    //Loop through each column
    for(int x = 0; x < width; x++)
    {
      //Loop through each tile in the current columb
      for(int y = 0; y < height; y++)
      {
        //Draw the tile we are currently on at the correct position
        tiles[x][y].draw(graphics,offsetX + x*SideScroller.TILE_SIZE,offsetY + y*SideScroller.TILE_SIZE);
      }
    }
  }
  //Saving/Loading Methods
  public void save(String fileName)
  {
    //todo write the array to a file
    java.io.PrintWriter writer; 
    try
    {
      writer = new java.io.PrintWriter(new java.io.FileWriter(fileName));
      writer.println(width+","+height);
      for (int y = 0; y < height; y++)
      {
        for (int x = 0; x < width; x++)
        {
          writer.println(/*x+","+y+":"+*/getTile(x,y).getImagePath()+":"+getTile(x,y).getTileType());
        }
      }
      writer.close();
    }
    catch (Exception e)
    {
    }
  }
  public static Map load(SpriteLoader spriteLoader, String fileName)
  {
    java.io.BufferedReader reader;
    String line;
    String[] elements;
    Map map = null;
    try
    {
      reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
      line = reader.readLine();
      elements = line.split(",");
      map = new Map(spriteLoader,Integer.parseInt(elements[0]),Integer.parseInt(elements[1]));
      for (int h = 0; h < map.height; h++)
      {
        for (int w = 0; w < map.width; w++)
        {
          line = reader.readLine();
          elements = line.split(":");
          map.setTile(w,h,new Tile(spriteLoader.load(elements[0]),TileType.parse(elements[1])));
        }
      }
      reader.close();
    }
    catch(Exception e)
    {
      System.err.println(map.width);
    }
    return map;
  }
}