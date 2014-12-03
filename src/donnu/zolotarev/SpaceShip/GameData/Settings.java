package donnu.zolotarev.SpaceShip.GameData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import donnu.zolotarev.SpaceShip.UI.FileMode;

public class Settings {

    private static Settings instance;
    private int lastPlayedLevel = 0;
    private FileMode fireMode;
    private boolean isAnalogflyControlMode = false;
    private boolean sound;
    private boolean music = true;

    public int getLastPlayedLevel() {
        return lastPlayedLevel;
    }

    public  void setLastPlayedLevel(int lastPlayedLevel) {
        this.lastPlayedLevel = lastPlayedLevel;
    }

    public FileMode getFileMode() {
        return fireMode;
    }

    public void setFireMode(FileMode fireMode) {
        this.fireMode = fireMode;
    }

    private Settings() {
        fireMode = FileMode.BY_HOLD;
    }

    public static Settings get(){
        return instance;
    }

    public static void create(String s){
        instance = null;
        if (!s.isEmpty()){
            try {
                Gson gson = new Gson();
                instance = gson.fromJson(s,new TypeToken<Settings>(){}.getType());
            } catch (JsonSyntaxException e) {
                instance = new Settings();
            }
        } else {
            instance = new Settings();
        }
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isAnalogflyControlMode() {
        return isAnalogflyControlMode;
    }

    public void setAnalogflyControlMode(boolean isAnalogflyControlMode) {
        this.isAnalogflyControlMode = isAnalogflyControlMode;
    }
}
