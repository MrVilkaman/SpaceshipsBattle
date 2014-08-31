package donnu.zolotarev.SpaceShip.GameData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ShopData {
    private static transient ShopData instance;

    public static ShopData get(){
        return instance;
    }

    public static void create(String s){
        if (!s.isEmpty()){
            try {
                Gson gson = new Gson();
                instance = gson.fromJson(s,new TypeToken<ShopData>(){}.getType());
            } catch (JsonSyntaxException e) {
                instance = new ShopData();
            }
        } else {
            instance = new ShopData();
        }
    }

    private int priceMaxHealth = 100;
    private int effectMaxHealth = 150;
    private int levelMaxHealth = 0;

    private int priceBulletDamege = 200;
    private int effectBulletDamege = 20;
    private int levelBulletDamege = 0;

    private boolean haveDoubleGun = false;
    private int priceDoubleGun = 1000;

    public int getPriceMaxHealth() {
        return (int) (priceMaxHealth * (1+ levelMaxHealth));
    }

    public int getEffectMaxHealth() {
        return (int) (effectMaxHealth * (1+ levelMaxHealth*0.5f));
    }

    public int getLevelMaxHealth() {
        return levelMaxHealth;
    }

    public void nextLevelMaxHealth() {
        ++levelMaxHealth;
    }

    public int getPriceBulletDamege() {
        return (int) (priceBulletDamege * (1+ levelBulletDamege*.7f));
    }

    public int getEffectBulletDamege() {
        return (int) (effectBulletDamege *levelBulletDamege);
    }

    public int getLevelBulletDamege() {
        return levelBulletDamege;
    }

    public void nextLevelBulletDamege() {
        ++levelBulletDamege;
    }

    public boolean isHaveDoubleGun() {
        return haveDoubleGun;
    }

    public void buyDoubleGun() {
        haveDoubleGun = true;
    }

    public int getPriceDoubleGun() {
        return priceDoubleGun;
    }
}
