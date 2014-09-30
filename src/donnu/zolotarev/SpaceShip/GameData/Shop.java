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

        list.add(new ShopItem(R.string.shop_item_armor_title,R.string.shop_item_armor_description,
                ShopItem.ItemShopType.DEFENCE,10, ShopGrowthRates.RatesModels.PARABOLA,150,75
        , ShopGrowthRates.RatesModels.SPECIAL_1,150,0.5f));

        list.add(new ShopItem(R.string.shop_item_damage_title,R.string.shop_item_damage_description,
                ShopItem.ItemShopType.DEFENCE,10, ShopGrowthRates.RatesModels.PARABOLA,250,120,
                ShopGrowthRates.RatesModels.LINEARLY,0,15));

        list.add(new ShopItem(R.string.shop_item_double_gun_title,R.string.shop_item_double_ammo_title,
                R.string.shop_item_double_gun_description,ShopItem.ItemShopType.AMMO,20,1000,300,3,1));

        list.add(new ShopItem(R.string.shop_item_rocket_gun_title,R.string.shop_item_rocket_ammo_title,
                R.string.shop_item_rocket_gun_description,ShopItem.ItemShopType.AMMO,20,2000,500,10,5));

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
