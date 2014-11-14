package donnu.zolotarev.SpaceShip.Utils;

import android.content.Context;
import android.media.MediaPlayer;
import donnu.zolotarev.SpaceShip.R;

public class SoundHelper {

    private static MediaPlayer mediaPlayer;

   public static void menuSoundOn(Context context){
       if (mediaPlayer != null){
           menuSoundOff();
       }
       mediaPlayer =  MediaPlayer.create(context, R.raw.menu_sound);
       mediaPlayer.setLooping(true);
       mediaPlayer.start();
   }

    public static void menuSoundOff(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void pause(){
        if (mediaPlayer != null){
          //  if (mediaPlayer.isPlaying())
                mediaPlayer.pause();
        }
    }

    public static void play(){
        if (mediaPlayer != null){
            if (!mediaPlayer.isPlaying())
                mediaPlayer.start();
        }
    }
}
