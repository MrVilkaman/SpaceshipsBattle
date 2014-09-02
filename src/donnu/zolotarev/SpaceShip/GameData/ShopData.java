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
    // Health
    private int priceMaxHealth = 150;
    private int priceDiffHealth = 75;
    private int effectMaxHealth = 150;
    private int levelMaxHealth = 0;

    // Damage
    private int priceBulletDamege = 250;
    private int priceDiffDamege = 120;
    private int effectBulletDamege = 20;
    private int levelBulletDamege = 0;

    // DoubleGun
    private boolean haveDoubleGun = false;
    private int priceDoubleGun = 1000;

    //Rocket
    private int priceRocketGun = 2000;
    private boolean haveRocket = false;

    private int priceRocketAmmo = 500;
    private int rocketCount = 0;


    public int getPriceMaxHealth() {
        return (int) (priceMaxHealth + priceDiffHealth* (levelMaxHealth* levelMaxHealth));
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
        return (int) (priceBulletDamege + priceDiffDamege* (levelBulletDamege* levelBulletDamege));
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

    public void buyRocketGun(){
        haveRocket = true;
        buyRocketAmmo();
    }

    public void buyRocketAmmo(){
        rocketCount += 5;
    }

    public boolean isHaveRocketGun() {
        return haveRocket;
    }

    public int getRocketCount() {
        return rocketCount;
    }

    public int useRocket() {
        return --rocketCount;
    }

    public boolean isHaveRocket() {
        return rocketCount > 0;
    }

    public int getPriceRocketGun() {
        return priceRocketGun;
    }

    public int getPriceRocketAmmo() {
        return priceRocketAmmo;
    }
}
