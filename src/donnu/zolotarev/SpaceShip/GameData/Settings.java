package donnu.zolotarev.SpaceShip.GameData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import donnu.zolotarev.SpaceShip.UI.ControlMode;

public class Settings {

    private static Settings instance;
    private int lastPlayedLevel = 0;
    private ControlMode controlMode;
    private boolean sound;
    private boolean music = true;

    public int getLastPlayedLevel() {
        return lastPlayedLevel;
    }

    public  void setLastPlayedLevel(int lastPlayedLevel) {
        this.lastPlayedLevel = lastPlayedLevel;
    }

    public ControlMode getControlMode() {
        return controlMode;
    }

    public void setControlMode(ControlMode controlMode) {
        this.controlMode = controlMode;
    }

    private Settings() {
        controlMode = ControlMode.BY_HOLD;
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
}
