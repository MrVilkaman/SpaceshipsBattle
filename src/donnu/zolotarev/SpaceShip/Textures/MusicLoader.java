package donnu.zolotarev.SpaceShip.Textures;

import android.content.Context;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.Engine;

public class MusicLoader {

    private static Music fire;
    private static Music sound;

    public static void loadTexture(Context context, Engine engine) {
        try {
            fire = MusicFactory.createMusicFromAsset(engine.getMusicManager(), context, "fire_sound.ogg");
            sound = MusicFactory.createMusicFromAsset(engine.getMusicManager(),context, "Ouroboros.ogg");
            sound.setLooping(true);

//            engine.getSoundManager().setMasterVolume(0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void playFire() {
        //todo настроить звук
       /* if (fire.isPlaying()){
            fire.stop();
        }
            fire.play();*/
    }

    public static Music getSound() {
        return sound;
    }

    public static void clear() {
//        sound.release();
            fire  = null;
            sound = null;
    }
}
