package donnu.zolotarev.SpaceShip.GameData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import donnu.zolotarev.SpaceShip.R;

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
        return Shop.get().getById(R.string.shop_item_armor_title).getEffectRec(maxHealth);
    }

    public int getExtraBulletDamege() {
        return Shop.get().getById(R.string.shop_item_damage_title).getEffect();
    }

    public boolean isHaveRocketGun() {
        return Shop.get().getById(R.string.shop_item_rocket_gun_title).alreadyBought();
    }

    public int getRocketCount() {
        return Shop.get().getById(R.string.shop_item_rocket_gun_title).getCount();
    }

    public boolean isHaveRocket() {
        return Shop.get().getById(R.string.shop_item_rocket_gun_title).getCount() > 0;
    }

    public int useRocket() {
        return Shop.get().getById(R.string.shop_item_rocket_gun_title).use();
    }

    public boolean isHaveDoubleGun() {
        return Shop.get().getById(R.string.shop_item_double_gun_title).alreadyBought();
    }

    public int getDoubleGunCount() {
        return Shop.get().getById(R.string.shop_item_double_gun_title).getCount();
    }

    public boolean isHaveDoubleAmmo() {
        return Shop.get().getById(R.string.shop_item_double_gun_title).getCount() > 0;
    }

    public int useDoubleGun() {
        return Shop.get().getById(R.string.shop_item_double_gun_title).use();
    }

    public boolean isHaveShield() {
        //todo заменить
        return true;//Shop.get().getById(R.string.shop_item_double_gun_title).getCount() > 0;
    }

    public int useShield() {
        //todo заменить
        return 1;//Shop.get().getById(R.string.shop_item_double_gun_title).use();
    }
}
