package SuperMarioBros;

import javax.sound.sampled.*;
import java.io.*;
/*
 * AudioPlayer
 * This class is a wrapper to the Java Sound api which is very low level and hard to work with.
 * The AudioPlayer allows simple playback of a sound files (although not mp3).
 * To use it simply make an instance of it and call play(String fileName).
 */
public class AudioPlayer
{	
  //The clip is what is actually going to play our sound, again java is dumb with exceptions so I can't initialize it here
  private static Clip clip = null;  
  public AudioPlayer()
  {  
  }
  public static void play(String fileName)
  {
    AudioPlayer.play(fileName,false);
  }  
  public static void play(String fileName, boolean loop)
  {
    //Stores the audio file for accessing
    File file = new File(fileName); 
    //Create an Input Stream (can't initialize it here cause java is dumb with exception handleing)
    AudioInputStream stream = null;
   //We'll need to know the format of the file later.
    AudioFormat format;  
    //Get a suitible audio stream
    try
    {
      stream = AudioSystem.getAudioInputStream(file);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }
    //find out the format of our stream
    format = stream.getFormat();
    //The DataLine.Info helps get us a clip from the AudioEngine with the right info (Hence the name aparently)
    DataLine.Info info = new DataLine.Info(Clip.class,format);
    try
    {
      //Get a clip
      clip = (Clip)AudioSystem.getLine(info);
      //Open a stream on the sound card.
      clip.open(stream);
    }
    catch(Exception e)
    {
      System.err.println("There was an error obtaining a Clip line!");
    }
    if (loop)
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    else
      clip.start();
  }
  //Switch between clips
  public static void play(String filename,String filename2) 
  {
	  if(clip != null)
	  {
              if(clip.isRunning())
              {
                     clip.stop();
                     clip.flush();
                     play(filename);
                     clip.drain();                 
                     play(filename2,true); 
              }
         }else {
          System.err.println("There was no clip to stop,this shouldnt happen!");
      }  
  }  
}