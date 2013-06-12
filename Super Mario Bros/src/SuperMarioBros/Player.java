package SuperMarioBros;

/* PlayerCharacter
 * PlayerCharacter is an extension of Character. It allows user input and movement from the keyboard. It is what the user controls
 * Effect 
 * Effect isnt used but it sets an effect on a character
 */
public class Player extends Character
{
  public int score;
  public int lives = 3;
  private static double MaxAccel = 2.0;
  private static double MinAccel = 1.0;
  private static double AccelGainPerMs = 0.01;
  /*
   * This is so we can set to 0 the accelaration if we re changing route
   * 
   * true = RIGHT
   * false = LEFT
   * 
   */
  private boolean isWatching = true;      
  public Effect playerEffect ;
  private double accelaration = 0.0;
  public Player(String name, AnimatedSprite sprite, int x, int y, int speed)
  {
    super(name,sprite,x,y,speed);
    playerEffect= new Effect(0);
  }  
  public void update(float gameTime, Camera camera, KeyboardInput input)
  {	 
    if (isDead ||position.y > 450)
    { 	
       if (lives > 0)
       {
    	   AudioPlayer.play("Content/Audio/flatten.wav","Content/Audio/bgMusic2.wav");
    	  	  try{
    				Thread.sleep(3500);
    				sprite.play("dead");
    	  		}catch (Exception e) {
    	  		}
        this.accelaration = 0.0;
        this.moveTo(128,200);
        camera.setPosition(0,0);
        this.isDead = false;
        sprite.play("stand");
      }
      else{ 
    	  AudioPlayer.play("Content/Audio/smb_gameover.wav");
      	  try{
    			Thread.sleep(3500);
    			sprite.play("dead");
      		}catch (Exception e) {
      		}
        SideScroller.isGameOver = true;
      }
      return;
    } 
    super.update(gameTime,camera);
    handleInput(input);
  } 
  public int getEffect(){
	  return playerEffect.Current_Status;
  }
  public void setEffect(int newEffect){
	  playerEffect.changeStatus(newEffect);
  }
  private void handleInput(KeyboardInput input)
  {
      if(input.isKeyReleased(Keys.Right) || input.isKeyReleased(Keys.Left))
          accelaration = 0.0;
    else if (input.isKeyDown(Keys.Right))
    {
      if (input.isKeyPressed(Keys.Right))
      {
        sprite.play("walk");
      }
      if(this.isWatching == false)
      {
          sprite.play("walk");
          this.isWatching = true;
          accelaration = 0;
      }
      this.applyForce(1+(int)(1*getAccel()),0);
      direction = Direction.Right;
    }
    else if (input.isKeyDown(Keys.Left))
    {
      if (input.isKeyPressed(Keys.Left))
        sprite.play("walk");
      if(this.isWatching == true)
      {
          this.isWatching = false;
          accelaration = 0;
          sprite.play("walk");
      }
      this.applyForce(-1-(int)(1*getAccel()),0);
      direction = Direction.Left;
    }
    else
    {
      if (!sprite.isPlaying("stand"))
        sprite.play("stand");
    }
    if (input.isKeyPressed(Keys.Up))
    {
      if(!this.isJumping )
      {
        this.isJumping = true;
        sprite.play("jump");
        this.applyForce(0,-28);
        AudioPlayer.play("Content/Audio/jump.wav");
        isontheGround = false;
      }
    }
  }
   //CARE
  public double getAccel()
  {
      //return the ac 
      if (this.accelaration <= MaxAccel && this.accelaration > MinAccel)
      {
          accelaration += accelaration*AccelGainPerMs;
      }else if( this.accelaration <= MinAccel) 
      {
          accelaration += 0.05f;
      }
      else {}//Do nothing for the time could use some sound
      return accelaration;
  }
    @Override
  public Direction getDirection()
  {
      return direction;
  }
    @Override
    public void AddtoScore(int add)
  {
      score +=add;
  }
    @Override
    public void die()
    {
        this.isDead =true;
        lives--;
        score = 0 ;
    }
public  class Effect  {
    //effects
    public  final int MARIO = 0;
    public  final int SUPER = 1;
    public  final int RED_FLOWER = 2;
    public  final int HYPER = 3;
    public int Current_Status;
    public Effect(int w){
        switch(w){
            default:
                System.out.println("No mario status selected!");
                break;
            case MARIO:
            	Current_Status = MARIO;
                break;
            case SUPER:
            	Current_Status = SUPER;
                break;
            case RED_FLOWER:
            	Current_Status = RED_FLOWER;
                break;
            case HYPER:
            	Current_Status = HYPER;               
                break;
        }
    }
    public void changeStatus(int w){
        switch(w){
            default:
                System.out.println("No effect selected!");
                break;
            case SUPER:
                if(Current_Status == MARIO)
                	Current_Status =SUPER;              
                break;
            case RED_FLOWER:
            	if(Current_Status == MARIO || Current_Status == SUPER )
            		Current_Status = RED_FLOWER;              
                break;
            case HYPER:
            	if(Current_Status == SUPER || Current_Status == RED_FLOWER)
            	{
            		Current_Status = HYPER;
            	}
                break;
        	}
    	}
	}
} 