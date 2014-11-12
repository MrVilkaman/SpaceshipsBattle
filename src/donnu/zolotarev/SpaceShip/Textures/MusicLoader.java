package donnu.zolotarev.SpaceShip.Textures;

import android.content.Context;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;

public class MusicLoader {

    private static Sound fire;
    private static Music sound;

    public static void loadTexture(Context context, Engine engine) {
        try {
            fire = SoundFactory.createSoundFromAsset(engine.getSoundManager(), context, "fire_sound.ogg");
            sound = MusicFactory.createMusicFromAsset(engine.getMusicManager(),context, "Ouroboros.ogg");
            sound.setLooping(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Sound getFire() {
        return fire;
    }

    public static Music getSound() {
        return sound;
    }

    public static void clear() {
//        sound.release();
    }
}
