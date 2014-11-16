package donnu.zolotarev.SpaceShip.GameData;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.SpaceExeptions.NoShopItemById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Shop {


    public final static int SHOP_ARMOR_TITLE = 0;
    public final static int SHOP_DAMAGE_TITLE = 2;
    public final static int SHOP_DAMAGE_DESCRIPTION = 3;
    public final static int SHOP_DOUBLE_GUN_TITLE = 4;
    public final static int SHOP_DOUBLE_AMMO_TITLE = 5;
    public final static int SHOP_ROCKET_GUN_TITLE = 6;
    public final static int SHOP_ROCKET_AMMO_TITLE = 7;
    public final static int SHOP_SHIELD_TITLE = 8;
    public final static int SHOP_SHIELD_AMMO_TITLE = 9;
    public final static int SHOP_SHIELD_HP_TITLE = 10;

    private static transient Shop instance;
    private ArrayList<ShopItem> list;

    public int getSize(){
        return list.size();
    }

    public ShopItem getItem(int pos){
        return list.get(pos);
    }

    private Shop(Context context,ArrayList<ShopItem> l){
        list = l;
    }

    private Shop(Context context){
        list = new ArrayList<ShopItem>();

        list.add(new ShopItem(SHOP_ARMOR_TITLE,R.string.shop_item_armor_description,
                ShopItem.ItemShopType.DEFENCE,10, ShopGrowthRates.RatesModels.PARABOLA,200,500
        , ShopGrowthRates.RatesModels.SPECIAL_1,180,0.5f));

        list.add(new ShopItem(SHOP_DAMAGE_TITLE,R.string.shop_item_damage_description,
                ShopItem.ItemShopType.DEFENCE,10, ShopGrowthRates.RatesModels.PARABOLA,250,300,
                ShopGrowthRates.RatesModels.LINEARLY,0,10));

        list.add(new ShopItem(SHOP_DOUBLE_GUN_TITLE,SHOP_DOUBLE_AMMO_TITLE,
                R.string.shop_item_double_gun_description,ShopItem.ItemShopType.AMMO,20,800,250,3,1));

        list.add(new ShopItem(SHOP_ROCKET_GUN_TITLE,SHOP_ROCKET_AMMO_TITLE,
                R.string.shop_item_rocket_gun_description,ShopItem.ItemShopType.AMMO,50,2000,375,10,5));

        // todo сделать так, что бы не удалялись сохранения)

        list.add(new ShopItem(SHOP_SHIELD_TITLE,SHOP_SHIELD_AMMO_TITLE,
                R.string.shop_item_shield_description,ShopItem.ItemShopType.AMMO,50,800,250,3,1));

        list.add(new ShopItem(SHOP_SHIELD_HP_TITLE,R.string.shop_item_shield_hp_description,
                ShopItem.ItemShopType.AMMO,100, ShopGrowthRates.RatesModels.LINEARLY,200,40
                , ShopGrowthRates.RatesModels.LINEARLY,100,100));

    }

    public static void create(Context context,String s){
        if (!s.isEmpty()){

            try {
                Gson gson = new Gson();
                ArrayList<ShopItem> l = gson.fromJson(s,new TypeToken<Collection<ShopItem>>(){}.getType());
                instance = new Shop(context,l);
            } catch (JsonSyntaxException e) {
                instance = new Shop(context);
            }
        } else {
            instance = new Shop(context);
        }
    }

    public static Shop get() {
        return instance;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    public ShopItem getById(int titleResId) throws NoShopItemById{
        Iterator<ShopItem> it = list.iterator();
        while (it.hasNext()){
            ShopItem item = it.next();
            if(item.getId() == titleResId){
                return item;
            }
        }
        new NoShopItemById();
        return null;
    }

}
