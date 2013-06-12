package SuperMarioBros;

/*
 * AIcharacter
 * Class for creation of animations(through the animatedsprite which loads the images from spriteloader) and paring them with cpu characters
 * the objects of this class are solely created in sidescroller
 * patrol logic in changeRoute and update methods
 */
public class AICharacter extends Character
{
  private static int distance;
  private static int startPosition;
  private int distanceTraveled=0;
  private String name;
  public AICharacter(String name, AnimatedSprite sprite, int x, int y, int speed,Direction direction)
  {
    super(name,sprite,x,y,speed);
    this.name = name;
    sprite.play("walk");
    this.direction = direction;
    if(name.equals("star")||
       name.equals("Money")||
       name.equals("HpUp")||
       name.equals("Shroom"))
        isBonus = true;
  }
  public void update(float gameTime, Camera camera)
  { 
    if (isDead && !isBonus && !name.equals("flower_monster"))
    {
      sprite.play("dead");
      return;
    }
    super.update(gameTime, camera); 
    distanceTraveled += speed;
     //Update the distance the character is traveled since it turned around
    //Once the character has traveled it's patrol distance turn it around, and reset the distance traveled
    if(!isBonus)
    {
        if(!name.equals("flower_monster"))
        {
        if (distanceTraveled >= distance)
        {
                if (this.direction == Direction.Right)
                  this.direction = Direction.Left;
              else if (this.direction == Direction.Left)
      		  this.direction = Direction.Right;
                  distanceTraveled = 0;
        }
    }
    //Move the character in the direction traveled
    if (direction == Direction.Right)
      this.applyForce(speed,0);
    else if(direction == Direction.Left)
      this.applyForce(-speed,0);
    }else
    {
        if (direction == Direction.Right)
      this.applyForce(speed,1);
    else if(direction == Direction.Left)
      this.applyForce(-speed,1);
    }
  }
  //A method to help make the goomba type of enemy
  //It is static so it is called by AICharacter.creategoomba()
  public static AICharacter createGoomba(SpriteLoader spriteLoader,String name, int x, int y,int Adistance)
  {
    //Load the frames into the animation
    AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/goomba/walk1.png"));
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/goomba/walk2.png"));
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/goomba/dead.png"));
    //Register animations
    enemyAnim.addAnimation("walk",new int[] {0,1,0});
    enemyAnim.addAnimation("dead",new int[] {2});
    //Create and return the new goomba with the animations we just made
    AICharacter goomba = new AICharacter(name, enemyAnim, x*64,y*64,1,Direction.Left);
    startPosition = x*SideScroller.TILE_SIZE;
    distance = Adistance;
    return goomba;
  }
  //A method to help make the turtle type of enemy
  //It is static so it is called by AICharacter.createTurtle()
public static AICharacter createTurtle(SpriteLoader spriteLoader,String name, int x, int y,int Adistance)
  {
    //Load the frames into the animation
    AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/turtle/walk1.png"));
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/turtle/walk2.png"));
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/turtle/dead.png"));
    //Register animations
    enemyAnim.addAnimation("walk",new int[] {0,1,0});
    enemyAnim.addAnimation("dead",new int[] {2});
    //Create and return the new turtle with the animations we just made
    AICharacter turtle = new AICharacter(name, enemyAnim, x*64,y*64,1,Direction.Left);
    startPosition = x*SideScroller.TILE_SIZE;
    distance = Adistance;
    return turtle;
  }
//A method to help make the Flower_monster  type of enemy
  //It is static so it is called by AICharacter.createFlowerMonster()
  public static AICharacter createFlowerMonster(SpriteLoader spriteLoader, String name ,int x, int y,int Adistance)
  {
    //Load the frames into the animation
    AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/flower_monster/flower_monster1.png"));
    enemyAnim.addFrame(spriteLoader.load("Content/Textures/Characters/enemies/flower_monster/flower_monster2.png"));
    //Register animations
    enemyAnim.addAnimation("walk",new int[] {0,1,0});    
    //Create and return the new flower_monster with the animations we just made
    AICharacter flower_monster = new AICharacter(name, enemyAnim, x*64,y*64,1,Direction.Up);
    startPosition = x*SideScroller.TILE_SIZE;
    distance = Adistance; 
    return flower_monster;
  }
  //A method to help make the star bonus
  //It is static so it is called by AICharacter.createstar() and is not used here
  public static AICharacter createStar(SpriteLoader spriteLoader, int x, int y,String name)
  {
    //Load the frames into the animation
    AnimatedSprite bonusAnim = new AnimatedSprite(200f,true, true);
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/star1.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/star2.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/star3.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/star4.png"));
    //Register animation
    bonusAnim.addAnimation(name,new int[] {0,1,2,3,0});
    //Create and return the new flower_monster with the animations we just made
    AICharacter star = new AICharacter("star", bonusAnim, x , y,1,Direction.Right);   
    return star;
  }
  //A method to help make the star bonus
  //It is static so it is called by AICharacter.createHpUp() and is not used here
  public static AICharacter createHpUp(SpriteLoader spriteLoader, int x, int y,String name)
  {
    //Load the frames into the animation
    AnimatedSprite bonusAnim = new AnimatedSprite(1000,true, true);
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/1Up.png"));
    //Register animations
    bonusAnim.addAnimation("walk",new int[] {0});    
    //Create and return the new HpUp with the animations we just made
    AICharacter HpUp = new AICharacter(name, bonusAnim,x , y,1,Direction.Right);
    return HpUp;
  }
   //A method to help make the Shroom bonus
  //It is static so it is called by AICharacter.createShroom() and is not used here
  public static AICharacter createShroom(SpriteLoader spriteLoader, int x, int y,String name)
  {
    //Load the frames into the animation
    AnimatedSprite bonusAnim = new AnimatedSprite(1,true, true);
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/mushroom.png"));  
    //Register animations
    bonusAnim.addAnimation("walk",new int[] {0});    
    //Create and return the new mushroom with the animations we just made
    AICharacter Shroom = new AICharacter(name, bonusAnim,x , y,1,Direction.Right);    
    return Shroom;
  }
   //A method to help make the Money
  //It is static so it is called by AICharacter.createMoney() 
  public static AICharacter createMoney(SpriteLoader spriteLoader, int x, int y,String name)
  {    
    //Load the frames into the animation
    AnimatedSprite bonusAnim = new AnimatedSprite(150,true, true);
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/coin1.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/coin2.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/coin3.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/coin4.png"));
    //Register animations
    bonusAnim.addAnimation("walk",new int[] {0,1,2,3});    
    //Create and return the new Money with the animations we just made
    AICharacter Money = new AICharacter( name, bonusAnim,x ,y,1,Direction.Up);    
    return Money;
  }
    //A method to help make the FireFlower bonus
  //It is static so it is called by AICharacter.createFireFlower() and is not used here
  public static AICharacter createFireFlower(SpriteLoader spriteLoader, int x, int y)
  {
    //Load the frames into the animation
    AnimatedSprite bonusAnim = new AnimatedSprite(1000f,true, true);
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/fireFlower.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/fireFlower1.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/fireFlower2.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/fireFlower3.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/fireFlower4.png"));    
    //Register animations
    bonusAnim.addAnimation("walk",new int[] {0,1,2,3,0});    
    //Create and return the new FireFlower with the animations we just made
    AICharacter FireFlower = new AICharacter("FireFlower", bonusAnim,x , y,1,Direction.Up);    
    return FireFlower;
  }
//Block Destruction Frame
//Well its not used at all ...and its kinda tough
  public static AICharacter DestroyBlock(SpriteLoader spriteLoader, int x, int y)
  {
    //Load the frames into the animation
    AnimatedSprite bonusAnim = new AnimatedSprite(100f,true, true);
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/block1.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/block2.png"));
    bonusAnim.addFrame(spriteLoader.load("Content/Textures/Tiles/items/block3.png"));   
    //Register animations
    bonusAnim.addAnimation("walk",new int[] {0,1,2,0});
    //Create and return the new broken blocks with the animations we just made
    AICharacter BlockDestruction = new AICharacter("Block Destruction", bonusAnim,x , y,1,null);   
    return BlockDestruction;
  }
  public String getName(){
      return name;
  }
  //Check the direction and revert it
  //this way aicharacters are locked inside of obstacles and dont keep moving against the wall
    @Override
  public void changeRoute()
  {
      if (direction == Direction.Right)
          direction = Direction.Left;
      else if (direction == Direction.Left)
          direction = Direction.Right;
  }  
}