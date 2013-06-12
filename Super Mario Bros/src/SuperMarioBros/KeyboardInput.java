package SuperMarioBros;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * KeyboardInput
 * Handles keyboard input for a game senario. Java uses an event based input handeling system that works great for UI and typing, but not so well for a frame-by-frame game
 * This class allows the checking of specific Keys with values correct for the exact frame.
 */
public class KeyboardInput implements KeyListener
{
  private static final int KEY_COUNT = 256;
  //Stores the possible states a key can have (Up(not down), Down (Pressed or Held) and Pressed (just pressed this frame)
  private enum KeyState
  {
    Up, //Key is not down
    Pressed, //Key has been pressed this frame
    Down,// Key is down
    Released//Key was just released
  }
  //Stores an array of the keys and if they are held. It is accessed by the keycode
  private boolean[] currentKeys = null;
  //Stores the state of the keys
  private KeyState[] keys = null;
  public KeyboardInput()
  {
    currentKeys = new boolean[KEY_COUNT];
    keys = new KeyState[KEY_COUNT];
    for (int i = 0; i < KEY_COUNT; i++)
      keys[i] = KeyState.Up;
  }
  //Gets the state of all keys each frame.
  //This is called by the Game class and can safely be ignored
  public synchronized void poll()
  {
    for(int i = 0; i < KEY_COUNT; ++i)
    {      
      if (currentKeys[i])
      {
        //The key is down
        //Check if it was pressed this frame or if it has been down for more than that
        if( keys[i] == KeyState.Up)
            keys[i] = KeyState.Pressed;
        else
            keys[i] = KeyState.Down;
        //They his is down now but wasnt before (Therefor it was pressed this frame)
      }
      else
      {
        if(keys[i] == KeyState.Down)
            keys[i] = KeyState.Released;
        else
            keys[i] = KeyState.Up;
      } 
    }
  }
  //Gets the information from java's event based system
  public synchronized void keyPressed(KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    if (keyCode >= 0 && keyCode < KEY_COUNT)
    {
      currentKeys[keyCode] = true;
    }
  }
  public synchronized void keyReleased(KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    if (keyCode >= 0 && keyCode < KEY_COUNT)
    {
      currentKeys[keyCode] = false;
    }
  }
  //Checks to see if a specific key is down (Held or just pressed)
  public boolean isKeyDown (int keyCode)
  {
    return keys[keyCode] == KeyState.Down
      || keys[keyCode] == KeyState.Pressed;
  }
  //Checks to see if a specific key is Pressed this frame
  public boolean isKeyPressed(int keyCode)
  {
    return keys[keyCode] == KeyState.Pressed;
  }
  //Checks to see if a specific key is not down or pressed
  public boolean isKeyUp(int keyCode)
  {
    return keys[keyCode] == KeyState.Up; 
  }
  public boolean isKeyReleased(int keyCode)
  {
      return keys[keyCode] == KeyState.Released;
  }
  public void keyTyped(KeyEvent e)
  { 
  }
}