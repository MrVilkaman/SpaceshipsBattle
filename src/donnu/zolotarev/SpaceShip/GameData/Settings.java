package donnu.zolotarev.SpaceShip.GameData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Settings {

    private static Settings instance;
    private int lastPlayedLevel = 0;

    public int getLastPlayedLevel() {
        return lastPlayedLevel;
    }

    public  void setLastPlayedLevel(int lastPlayedLevel) {
        this.lastPlayedLevel = lastPlayedLevel;
    }

    private Settings() {
    }

    public static Settings get(){
        return instance;
    }

    public static void create(String s){
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
}
