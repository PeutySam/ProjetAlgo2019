import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

public class SoundEffect {
    Clip clip;
    public void setFile(String g){
        try{
            File file=new File(g);
            AudioInputStream sound= AudioSystem.getAudioInputStream(file);
            clip=AudioSystem.getClip();
            clip.open(sound);
        }catch(Exception e){
            System.out.println(e);
        }

    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();

    }
}
