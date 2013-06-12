package SuperMarioBros;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * Game
 * This is the base class for any game. It preforms the tasks of making the window, preparing a drawing surface,
 * the game loop, and methods that can be overrided in subclasses to organize code.
 */
public class Game extends Canvas
{
  protected BufferStrategy strategy;
  protected SpriteLoader spriteLoader;
  protected boolean isGameRunning = true;
  protected KeyboardInput input;
  long gameTime = 0;
  long lastLoopTime = 0;
  //Constructor for the Game Class.
  public Game()
  {
    //Setup the frame and the panel to use to render our game.
    JFrame frame = new JFrame("[SUPER MARIO BROS]");
    JPanel panel = (JPanel)frame.getContentPane();
    panel.setPreferredSize(new Dimension(768,420));
    panel.setLayout(null);
    //Add this Canvas to the frame with the correct resolution
    setBounds(0,0,800,600);
    panel.add(this);
    //Don't use the slow awt rendering mode. Instead use the accelerated graphics.
    setIgnoreRepaint(true);
    //pack up the frame and make it visible (so people can see the game=P)
    //Also removes the ability to resize the frame to maintain a specific resolution.
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true); 
    //allow the user to exit using the Frame 'X' at the top right corner
    frame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    });
      //Allow user input
    input = new KeyboardInput();
    this.addKeyListener(input);
      //Get the focus on our game.
    requestFocus();
    //setup our accelerated graphics
    createBufferStrategy(2);
    strategy = getBufferStrategy();
    spriteLoader = new SpriteLoader();
    this.loadContent();
  } 
  public void run()
  {
    lastLoopTime = System.currentTimeMillis();
    while(isGameRunning)
    {
      this.updateTime();  
      //Get the Graphics surface to draw to, and clear it.
      Graphics2D graphics = (Graphics2D)strategy.getDrawGraphics();
      this.clear(graphics);     
      input.poll();
      //Update the game logic
      this.update();
      //Draw the graphics to the backbuffer
      this.draw(graphics);   
      //We've finished drawing, so show the backbuffer and dispose of the graphics surface.
      this.flush(graphics);    
      //Slow down the loop. This should make the game run at approximatly 100fps.
      //Look into a better method of doing this, as this is a variable time step that can easily fluctuate.
      try{ Thread.sleep(5); } catch(Exception e) {}
   }
  }
  //Helper Methods
  protected void loadContent()
  {
  }
  private void updateTime()
  {
    gameTime = System.currentTimeMillis() - lastLoopTime;
    lastLoopTime = System.currentTimeMillis();
  }
  private void clear(Graphics2D graphics)
  {
    graphics.setColor(Color.black);
    graphics.fillRect(0,0,800,600);
  } 
  private void flush(Graphics2D graphics)
  {
    graphics.dispose();
    strategy.show();
  }
  //Add Update (Game Logic) to this method
  protected void update()
  { 
  }
  //Add Drawing logic here. (This should generally just conist of drawing things, if you want to update and change
  //values, use the update() method instead
  protected void draw(Graphics2D graphics)
  {
  }
}