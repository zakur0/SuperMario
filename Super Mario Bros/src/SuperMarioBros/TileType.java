package SuperMarioBros;


/* 
 * TileType
 * This is a way to organize how a tile behaves
 */
public enum TileType
{
  Blank,
  Cloud,
  Solid,
  Deadly,
  MoneyBox,
  ShroomBox,
  StarBox,
  Breakable,
  FireFlowerBox,
  HpupBox;
  
  public static TileType parse(String str)
  {
    if (str.equals("Blank"))
      return TileType.Blank;
    else if (str.equals("Solid"))
      return TileType.Solid;
    else if(str.equals("MoneyBox"))
    	return TileType.MoneyBox;
    else if(str.equals("ShroomBox"))
    	return TileType.ShroomBox;
    else if(str.equals("Deadly"))
    	return TileType.Deadly;
    else if(str.equals("Cloud"))
    	return TileType.Cloud;
    else if(str.equals("starBox"))
    	return TileType.StarBox;
    else if(str.equals("hpupBox"))
    	return TileType.HpupBox;
    else if(str.equals("Breakable"))
    	return TileType.Breakable;
    else if(str.equals("FireFlowerBox"))
    	return TileType.FireFlowerBox;
      return TileType.Blank;
  }
}