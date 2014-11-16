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
        return Shop.get().getById(Shop.SHOP_ARMOR_TITLE).getEffectRec(maxHealth);
    }

    public int getExtraBulletDamege() {
        return Shop.get().getById(Shop.SHOP_DAMAGE_TITLE).getEffect();
    }

    public boolean isHaveRocketGun() {
        return Shop.get().getById(Shop.SHOP_ROCKET_GUN_TITLE).alreadyBought();
    }

    public int getRocketCount() {
        return Shop.get().getById(Shop.SHOP_ROCKET_GUN_TITLE).getCount();
    }

    public boolean isHaveRocket() {
        return Shop.get().getById(Shop.SHOP_ROCKET_GUN_TITLE).getCount() > 0;
    }

    public int useRocket() {
        return Shop.get().getById(Shop.SHOP_ROCKET_GUN_TITLE).use();
    }

    public boolean isHaveDoubleGun() {
        return Shop.get().getById(Shop.SHOP_DOUBLE_GUN_TITLE).alreadyBought();
    }

    public int getDoubleGunCount() {
        return Shop.get().getById(Shop.SHOP_DOUBLE_GUN_TITLE).getCount();
    }

    public boolean isHaveDoubleAmmo() {
        return Shop.get().getById(Shop.SHOP_DOUBLE_GUN_TITLE).getCount() > 0;
    }

    public int useDoubleGun() {
        return Shop.get().getById(Shop.SHOP_DOUBLE_GUN_TITLE).use();
    }

    public boolean isHaveShield() {
        return Shop.get().getById(Shop.SHOP_SHIELD_TITLE).getCount() > 0;
    }

    public int useShield() {
        return Shop.get().getById(Shop.SHOP_SHIELD_TITLE).use();
    }

    public int getShieldPoint() {
      //  if (isHaveShield()){
            return Shop.get().getById(Shop.SHOP_SHIELD_HP_TITLE).getEffect();
       /* }else{
            return 0;
        }*/
    }
}
