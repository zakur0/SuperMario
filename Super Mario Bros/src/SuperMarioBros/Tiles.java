package SuperMarioBros;

/*
 * Tiles
 * Create a hashtable for faster loading time of images and recycling of objects
 * Netbeans marks all methods that use imagepaths unchecked propably cause of the hashtable
 * SuppressWarning should have worked
 * 
 */
import java.io.File;
import java.util.Hashtable;
import java.util.ArrayList;
@SuppressWarnings("unchecked")
public class Tiles
{
  private static ArrayList tiles = new ArrayList();
  //private static Hashtable lookUp = new Hashtable();
  private static int count = 0;
  public static void addTile(Tile tile)
  {
    tiles.add(tile);
  }  
  public static Tile getTile(int index)
  {
    return (Tile)tiles.get(index);
  }
  public static int getCount()
  {
    return tiles.size();
  }
  public static void initialize(SpriteLoader spriteLoader)
  {	  
	  //blocks	
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/blocks/block.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/blocks/block_underground.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/blocks/immovable_block.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/blocks/immovable_block_underground.png"),TileType.Solid));
    //castle
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle/castle_11.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle/castle_21.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle/castle_22.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle/castle_23.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle/castle_32.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle/castle_43.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle/castle_53.png"),TileType.Solid));

    //castle_underground
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle_underground/castle_under_11.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle_underground/castle_under_21.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle_underground/castle_under_22.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle_underground/castle_under_23.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle_underground/castle_under_32.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle_underground/castle_under_43.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/castle_underground/castle_under_53.png"),TileType.Solid));
    // cloud
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/cloud/cloud_11.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/cloud/cloud_12.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/cloud/cloud_13.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/cloud/cloud_21.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/cloud/cloud_22.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/cloud/cloud_23.png"),TileType.Blank));
    //grass
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/grass/grass_11.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/grass/grass_12.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/grass/grass_13.png"),TileType.Blank));
    //mountain
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_11.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_12.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_13.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_14.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_15.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_22.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_23.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_24.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/mountain/mountain_33.png"),TileType.Blank));
    //vertical_tubes
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/vertical_tubes/vertical_tube_11.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/vertical_tubes/vertical_tube_12.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/vertical_tubes/vertical_tube_21.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/vertical_tubes/vertical_tube_22.png"),TileType.Solid));  
    //air
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/air.png"),TileType.Blank));
    //flags
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/flag1.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/flag2.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/base_flag1.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/base_flag2.png"),TileType.Solid));
    //special boxes
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/blocks/money_box.png"),TileType.MoneyBox));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/world/blocks/immovable_box.png"),TileType.MoneyBox));
  }
}