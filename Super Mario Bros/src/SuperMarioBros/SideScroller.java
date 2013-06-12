package SuperMarioBros;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
/*
 * SideScroller
 * This will be the main class that will make loading, drawing,updating and collision results
 * The code set to comments is code that can be used(most of it is functional) for adding effects
 * logic:
 * we first prepare the hashtable through initialize()
 * then loadContent() loads the character animations,the map through Map.load  in gameMap object and the enemies with loadlevel method
 * the level#_level.txt is read and enemies are stored in an enemies array which is used with the collider class methods  and the draw method
 * everything is drawn in draw method(which also check for timing of the bonuses although it shouldnt) 
 * 
 * In update() we check for collision with characters/objects and some scripting is done
 * finaly checkoflevel() ensures we re at the end of the level and loads the new level which is written at the end of level#_level.txt
 * also they are stored as Goal objects so that allows for different level entrances like shortcuts and secret levels
 * 
 * 
 */
@SuppressWarnings("unchecked")
public class SideScroller extends Game 
{
  public static final int TILE_SIZE = 32 ;
  private static final String Solid = null;
  public static boolean isGameOver = false;
  public static boolean isAtTitleScreen = true;
  private String currentLevelPath;
  private String nextLVL;
  //Timer
  private int seconds = 400;
  //Map  
  private Map gameMap;
  private Tile tileType;
  private Camera camera;
  //audio
  private javax.sound.sampled.Clip bgMusic;
    private Player character;
    private Collider collider;
  private AICharacter bonus;
  private AnimatedSprite anime;
  private AnimatedSprite SuperMario;
  private AnimatedSprite FireMario;
  private AnimatedSprite BlockDestruction;
  private ArrayList enemies;
  private ArrayList bonuses;
  private ArrayList TimeBonus;
  private ArrayList goals;
  public SideScroller()
  {
    super();
    Tiles.initialize(spriteLoader);
  }
  protected void loadContent()
  {
    AudioPlayer audioPlayer = new AudioPlayer();
    gameMap = new Map(spriteLoader,30,14);
    camera = new Camera(0,0,800,600);
    //Mario Character
    anime = new AnimatedSprite(200f,true, true);
    anime.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/mario/walk1.png"));
    anime.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/mario/walk2.png"));
    anime.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/mario/walk3.png"));
    anime.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/mario/Standup.png"));
    anime.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/mario/Standdown.png"));
    anime.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/mario/dead.png"));
    anime.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/mario/jump.png"));   
    //mario
    anime.addAnimation("walk",new int[] {0,1,2,1});
    anime.addAnimation("stand",new int[] {3,4,3,4});
    anime.addAnimation("dead",new int[] {5});  
    anime.addAnimation("jump",new int[] {6});  
    character = new Player("Mario", anime,196,300,1);
    // Super Mario
         //sprites
    SuperMario = new AnimatedSprite(200f,true, true);
    SuperMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/SuperMario/walk1.png"));
    SuperMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/SuperMario/walk2.png"));
    SuperMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/SuperMario/walk3.png"));
    SuperMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/SuperMario/Standup.png"));
    SuperMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/SuperMario/Standdown.png"));
    SuperMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/SuperMario/dead.png"));
    SuperMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/SuperMario/jump.png"));
        //animations
    SuperMario.addAnimation("walk",new int[] {0,1,2,1});
    SuperMario.addAnimation("stand",new int[] {3,4,3,4});
    SuperMario.addAnimation("dead",new int[] {5});  
    SuperMario.addAnimation("jump",new int[] {6});  
    character = new Player("SuperMario", anime,196,300,1); 
    // Fire Flower Mario
        //sprites
    FireMario = new AnimatedSprite(200f,true, true);
    FireMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/FireFlower/walk1.png"));
    FireMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/FireFlower/walk2.png"));
    FireMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/FireFlower/walk3.png"));
    FireMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/FireFlower/Standup.png"));
    FireMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/FireFlower/Standdown.png"));
    FireMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/FireFlower/dead.png"));
    FireMario.addFrame(spriteLoader.load("Content/Textures/Characters/Mario/FireFlower/jump.png"));
        //animation
    FireMario.addAnimation("walk",new int[] {0,1,2,1});
    FireMario.addAnimation("stand",new int[] {3,4,3,4});
    FireMario.addAnimation("dead",new int[] {5});  
    FireMario.addAnimation("jump",new int[] {6});  
    character = new Player("FireMario", anime,196,300,1);
        //LOADING THE LEVEL
    gameMap = Map.load(spriteLoader, "data/Level1.txt");
    loadLevel("data/Level1_level.txt");
    this.currentLevelPath = "Level1";
    collider = new Collider();
    TimeBonus = new ArrayList();
  }
  int count = 0;
  //Add Update (Game Logic) to this method
  protected void update()
  {
      if (count == 0)
             AudioPlayer.play("Content/Audio/bgMusic2.wav",true);
	count++;     
        //THE TITLE SCREEN
    if (SideScroller.isAtTitleScreen)
    {
      if (input.isKeyPressed(Keys.Space))
      {
        SideScroller.isAtTitleScreen = false;
        new CountDown();
      }
      else
        return;
    }
    else if (SideScroller.isGameOver)
    {
      if (input.isKeyDown(Keys.Space))
      {
    	  SideScroller.isGameOver = false;
          SideScroller.isAtTitleScreen = true;
          loadLevel("data/"+this.currentLevelPath+"_level.txt");
          character.moveTo(32,400);
          camera.setPosition(0,0);
          character.lives = 3;
          character.score = 0;
          new CountDown();
      }
    }
    character.move(0,3);
    character.update(gameTime, camera, input);
    if (character.isAlive())
    {
    	camera.follow(character);
        collider.collide(character,gameMap);
        CheckBoxes(); 
    }
   //Checki enemy Collision
    for(int i = 0; i < enemies.size(); i++)
    {
      AICharacter enemy = ((AICharacter)enemies.get(i));
      enemy.move(0,1);
     collider.collide(enemy,gameMap);
     enemy.update(gameTime,camera);
     if (character.isAlive() && enemy.isAlive())
     {   
         if (collider.collide(character,enemy) == 1)
	 {
            if(enemy.getName().equals("flower_monster"))// && character.getEffect()!=character.playerEffect.HYPER)
            {
               /* if((character.getEffect() != character.playerEffect.MARIO))
                {
                    character.setEffect(character.playerEffect.MARIO);
		}else*/   
                character.die(); 
            }else
              {     
		enemy.die();
		character.score+= 100;
		AudioPlayer.play("Content/Audio/step.wav");
		if (character.score>=1000)
               {
	    	  character.lives++;
           	  character.score=0;				
               }
            }			
	   }else if (collider.collide(character,enemy) == -1)
	   {
               // if((character.getEffect()==  character.playerEffect.MARIO))
                //{
                     character.die();
              //  }else if(character.getEffect() == character.playerEffect.HYPER)
               // {
                 //   enemy.die();
               // }else
                //{
                 //   character.setEffect(character.playerEffect.MARIO);
	//	}
	    }
	}
    }
    //Check bonus colision
    for(int i = 0; i < bonuses.size(); i++)
    {
      AICharacter Bonus = ((AICharacter)bonuses.get(i));
      collider.collide(Bonus,gameMap);
      Bonus.update(gameTime,camera);
     if (character.isAlive() && Bonus.isAlive())
     {
         if (collider.collideswithbonus( character,Bonus))
	 {
                if(Bonus.getName().equals("HpUp") || Bonus.getName().equals("Shroom"))
                    character.lives++;     
                Bonus.die();
                bonuses.remove(i);	
                System.gc();
	   }
	}
    }
    checkEndOfLevel();
  }
  //Checking the boxes that return true from Collider.collidewithBonus() method
  public void CheckBoxes()
  {
    Tile tile;
    tile = collider.collideWithSpBox(character, gameMap);
    if(tile!= null)
    {       
        //identifying the item
      if(tile.hasShroom())
      {
           bonus = AICharacter.createShroom(spriteLoader, character.getPosition().x, (character.getPosition().y)-70,"Shroom");
           TimeBonus.add(System.currentTimeMillis());
           tile.gotTheItem();
      }
      else if(tile.hasHpUp())
      {
          bonus = AICharacter.createHpUp(spriteLoader, character.getPosition().x, (character.getPosition().y)-70,"HpUp");
          TimeBonus.add(System.currentTimeMillis());
          tile.gotTheItem();
      }     
      //this has no gotTheItem() method cause it is called from collider.collidwithbonusbox() method
      else if(tile.hasMoney())
      {
          bonus = AICharacter.createMoney(spriteLoader, character.getPosition().x, (character.getPosition().y)-70,"Money");
          TimeBonus.add(System.currentTimeMillis());
      }
      else if(tile.hasStar())
      {
          bonus = AICharacter.createStar(spriteLoader, character.getPosition().x, (character.getPosition().y)-70,"star");
          TimeBonus.add(System.currentTimeMillis());
          tile.gotTheItem();
      }
      //adding the item to an array for drawing purposes
      bonuses.add(bonus);      
     }
  }
    @Override
  protected void draw(Graphics2D graphics)
  {
    if (SideScroller.isAtTitleScreen)
    {
      spriteLoader.load("Content/Textures/titleScreen.png").draw(graphics,0,0);
      graphics.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,28));
      graphics.drawString("Press [Space] to Continue...",220,500);
      return;
    }
    gameMap.draw(graphics,camera.getPosition());
    character.draw(graphics,camera);
    for(int i = 0; i < enemies.size(); i++)
    {
      ((AICharacter)enemies.get(i)).draw(graphics,camera);
    } 
    /* this is so that the animations dont last for ever */
    for(int i =0 ; i < bonuses.size();i++)
    {
         long time = (Long)TimeBonus.get(i);
         AICharacter topicBonus = (AICharacter)bonuses.get(i);
        if(topicBonus.getName().equals("Money") && topicBonus.isAlive()) 
        {       
            //checking the time passed
            if((System.currentTimeMillis()-time)<250)
            {
                ((AICharacter)bonuses.get(i)).draw(graphics,camera);
            }else{
                //removing and trying to clear the memory
                bonuses.remove(i);
                topicBonus.die();
                TimeBonus.remove(i);
                System.gc();
            }
            //same here but for other objects
        }else if(topicBonus.isAlive())
        {
            if((System.currentTimeMillis()-time)<10000)
            {
                ((AICharacter)bonuses.get(i)).draw(graphics,camera); 
            }else{
                bonuses.remove(i);
                topicBonus.die();
                TimeBonus.remove(i);
                System.gc();
            }
        }
     }
    drawUI(graphics);
  }
  private void checkEndOfLevel()
  {
    for (int i = 0; i < goals.size(); i++)
    {
      if (collider.collide(character,(Goal)goals.get(i)))
      {
        AudioPlayer.play("Content/Audio/victory.wav");
        //Load new map/level here
        this.currentLevelPath = ((Goal)goals.get(i)).getLevel();
       //
        gameMap = Map.load(spriteLoader,"data/"+((Goal)goals.get(i)).getLevel()+".txt");
        loadLevel("data/"+((Goal)goals.get(i)).getLevel()+"_level.txt");     
        //this could be a reset
        character.moveTo(32,400);
        character.lives = 3;
        character.score = 0;
        camera.setPosition(0,0);
      }
    }
  }
  private void drawUI(Graphics2D graphics)
  {
   character.getSprite().draw(graphics,0,-32); 
    graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,32));
    graphics.drawString("x"+character.lives,68,64);
    graphics.drawString("Score:"+character.score,300,64);
    graphics.drawString("Time: "+ seconds,500,64);
    if(SideScroller.isGameOver)
    {
      graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,40));
      graphics.drawString("GAME OVER ",300,300);
      graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,28));
      graphics.drawString("Press [Space] to go back to the main menu",220,360);
    }
  }
  private void loadAnimations()
  {   
  }
  private void loadLevel(String fileName)
  {	
    enemies = new ArrayList<AICharacter>();
    bonuses = new ArrayList<AICharacter>();
    goals = new ArrayList(); 
    java.io.BufferedReader reader;
    String line = new String();
    String[] elements;
    try
    {
      reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
      //Keep looping through the level file until we reach the end
              //Read the new line
      while((line = reader.readLine())!=null)
      {
        //Split that line into its elements
       elements = line.split(":");
       //Check to see if we're dealing with an enemy
        if (elements[0].equals("enemy"))
        {
            AICharacter enemy = null;
          //Create the new enemy with the specified properties
         if (elements[1].equals("turtle"))
         {
                enemy = AICharacter.createTurtle(spriteLoader,elements[1],Integer.parseInt(elements[2]),Integer.parseInt(elements[3]),Integer.parseInt(elements[4]));    
         }else if (elements[1].equals("flower_monster"))
         {
                enemy = AICharacter.createFlowerMonster(spriteLoader,elements[1],Integer.parseInt(elements[2]),Integer.parseInt(elements[3]),Integer.parseInt(elements[4]));
         }else if(elements[1].equals("goomba"))
         {  
             enemy = AICharacter.createGoomba(spriteLoader,elements[1],Integer.parseInt(elements[2]),Integer.parseInt(elements[3]),Integer.parseInt(elements[4]));
         }
         else {
                System.err.println("Unknown type of Character");
            }
            //Add the enemy to the list of enemies
         if(enemy!=null) {
                enemies.add(enemy);
            }
        }
        //Check to see if we're dealing with a goal
        else if (elements[0].equals("goal"))
        {
          goals.add(new Goal(Integer.parseInt(elements[1]), elements[2]));
        }
      }
      //Done reading the file, so close it
      reader.close();
   }catch(Exception e)
    {
       System.err.println("There was an error with the _level file");
    }
  }
  public class CountDown extends TimerTask{
		 Timer timer;		   
		public CountDown() {
			timer = new Timer();
			timer.schedule(this, 0, 1000 );
		}
      @Override
		public void run() {
			if(!character.isDead){	 
				  if (seconds > 0) {
						seconds--;
						}
					 if(seconds==50){
					    	AudioPlayer.play("Content/Audio/smb_warning.wav");	
							//graphics.drawString("HURRY UP",300,300);
					 }else if (seconds==0){
					    	character.die();
					    	seconds = 400;
					    }
				 }else if (character.isDead){
					 seconds = 400;
				 }
			}	
		}
}