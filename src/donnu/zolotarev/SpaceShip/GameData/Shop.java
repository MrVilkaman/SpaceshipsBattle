package donnu.zolotarev.SpaceShip.GameData;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import donnu.zolotarev.SpaceShip.SpaceExeptions.NoShopItemById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Shop {


    public final static int SHOP_ARMOR_TITLE =0;
    public final static int SHOP_DAMAGE_TITLE = 1;
    public final static int SHOP_DAMAGE_DESCRIPTION = 2;
    public final static int SHOP_DOUBLE_GUN_TITLE = 3;
    public final static int SHOP_DOUBLE_AMMO_TITLE = 4;
    public final static int SHOP_ROCKET_GUN_TITLE = 5;
    public final static int SHOP_ROCKET_AMMO_TITLE = 6;
    public final static int SHOP_SHIELD_TITLE = 7;
    public final static int SHOP_SHIELD_AMMO_TITLE = 8;
    public final static int SHOP_SHIELD_HP_TITLE = 9;

    private static transient Shop instance;
    private ArrayList<ShopItem> list;

    public static void clear() {
        instance.clearList();
        instance = null;
    }

    public void clearList() {
         list.clear();
        list = null;
    }

    public int getSize(){
        return list.size();
    }

    public ShopItem getItem(int pos){
        return list.get(pos);
    }

    private Shop(Context context,ArrayList<ShopItem> l){
        list = l;
        if (list.get(0).getId() != SHOP_ARMOR_TITLE){
            list.get(0).setTitleResId(SHOP_ARMOR_TITLE);
        }
        if (list.get(1).getId() != SHOP_DAMAGE_TITLE){
            list.get(1).setTitleResId(SHOP_DAMAGE_TITLE);
        }
        if (list.get(2).getId() != SHOP_DOUBLE_GUN_TITLE){
            list.get(2).setTitleResId(SHOP_DOUBLE_GUN_TITLE);
        }
        if (list.get(2).getResID() != SHOP_DOUBLE_AMMO_TITLE){
            list.get(2).setTitleAmmoResI(SHOP_DOUBLE_AMMO_TITLE);
        }
        if (list.get(3).getId() != SHOP_ROCKET_GUN_TITLE){
            list.get(3).setTitleResId(SHOP_ROCKET_GUN_TITLE);
        }
        if (list.get(3).getResID() != SHOP_ROCKET_AMMO_TITLE){
            list.get(3).setTitleAmmoResI(SHOP_ROCKET_AMMO_TITLE);
        }
        if (list.get(4).getId() != SHOP_SHIELD_TITLE){
            list.get(4).setTitleResId(SHOP_SHIELD_TITLE);
        }
        if (list.get(4).getResID() != SHOP_SHIELD_AMMO_TITLE){
            list.get(4).setTitleAmmoResI(SHOP_SHIELD_AMMO_TITLE);
        }
        if (list.get(5).getId() != SHOP_SHIELD_HP_TITLE){
            list.get(5).setTitleResId(SHOP_SHIELD_HP_TITLE);
        }


    }

    private Shop(Context context){
        list = new ArrayList<ShopItem>();

        list.add(new ShopItem(SHOP_ARMOR_TITLE, ShopItem.ItemShopType.DEFENCE,10, ShopGrowthRates.RatesModels.PARABOLA,200,300
        , ShopGrowthRates.RatesModels.SPECIAL_1,180,0.5f));

        list.add(new ShopItem(SHOP_DAMAGE_TITLE, ShopItem.ItemShopType.DEFENCE,10, ShopGrowthRates.RatesModels.PARABOLA,250,200,
                ShopGrowthRates.RatesModels.LINEARLY,0,10));

        list.add(new ShopItem(SHOP_DOUBLE_GUN_TITLE,SHOP_DOUBLE_AMMO_TITLE, ShopItem.ItemShopType.AMMO,20,800,250,3,1));

        list.add(new ShopItem(SHOP_ROCKET_GUN_TITLE,SHOP_ROCKET_AMMO_TITLE, ShopItem.ItemShopType.AMMO,50,2000,375,10,5));

        // todo сделать так, что бы не удалялись сохранения)

        list.add(new ShopItem(SHOP_SHIELD_TITLE,SHOP_SHIELD_AMMO_TITLE, ShopItem.ItemShopType.AMMO,50,800,250,3,1));

        list.add(new ShopItem(SHOP_SHIELD_HP_TITLE, ShopItem.ItemShopType.AMMO,100, ShopGrowthRates.RatesModels.LINEARLY,200,40
                , ShopGrowthRates.RatesModels.LINEARLY,500,100));

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
