package donnu.zolotarev.SpaceShip.GameData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class HeroFeatures {
    private static transient HeroFeatures instance;

    private int maxHealth = 500;


    public static HeroFeatures get(){
        return instance;
    }

    public static void create(String s){
        if (!s.isEmpty()){
            try {
                Gson gson = new Gson();
                instance = gson.fromJson(s,new TypeToken<HeroFeatures>(){}.getType());
            } catch (JsonSyntaxException e) {
                instance = new HeroFeatures();
            }
        } else {
            instance = new HeroFeatures();
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
