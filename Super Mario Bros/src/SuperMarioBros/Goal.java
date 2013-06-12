package SuperMarioBros;

public class Goal
{
  public int x;
  private String level;  
  public Goal(int x, String level)
  {
    this.x = x;
    this.level = level;
  }
   public String getLevel()
  {
    return level;
  }
}