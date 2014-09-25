package donnu.zolotarev.SpaceShip.GameData;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import donnu.zolotarev.SpaceShip.R;

import java.util.ArrayList;

public class Shop {

    private static transient Shop instance;

    private ArrayList<ShopItem> list;

    public int getSize(){
        return list.size();
    }

    public ShopItem getItem(int pos){
        return list.get(pos);
    }

    private Shop(Context context){
        list = new ArrayList<ShopItem>();

        list.add(new ShopItem(context.getString(R.string.shop_item_armor_title),context.getString(R.string.shop_item_armor_description),
                ShopItem.ItemShopType.DEFENCE,100));

        list.add(new ShopItem(context.getString(R.string.shop_item_damage_title),context.getString(R.string.shop_item_damage_description),
                ShopItem.ItemShopType.DEFENCE,100));
    }

    public static void create(Context context,String s){
        if (!s.isEmpty()){
            try {
                Gson gson = new Gson();
                instance = gson.fromJson(s,new TypeToken<Shop>(){}.getType());
            } catch (JsonSyntaxException e) {
                instance = new Shop(context);
            }
        } else {
            instance = new Shop(context);
        }
    }

    public static Shop getInstance() {
        return instance;
    }
}
