package donnu.zolotarev.SpaceShip.Fragments.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.GameData.Shop;
import donnu.zolotarev.SpaceShip.GameData.ShopItem;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.R;

public class ShopAdapter extends ArrayAdapter{
    private final LayoutInflater lInflater;
    private final Shop shop;
    private final UserDataProcessor dataProcessor;
    private final Callback callback;

    public int getShopItemValue(int title) {
        switch (title){
            case R.string.shop_item_armor_title:
                return HeroFeatures.get().getMaxHealth();
            case R.string.shop_item_damage_title:
                return HeroFeatures.get().getExtraBulletDamege();
            case R.string.shop_item_shield_hp_title:
            return HeroFeatures.get().getShieldPoint();
        }
        return 0;
    }

    public interface Callback{
        public void updateMoney();
    }

    public ShopAdapter(Context context, Shop shop,Callback callback) {
        super(context, R.layout.item_shop_item);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.shop = shop;
        this.callback = callback;
        dataProcessor = UserDataProcessor.get();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflateNewView(parent);
        }
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();

        Resources resources = parent.getContext().getResources();
       final ShopItem shopItem = shop.getItem(position);

        boolean hideTitle = !shopItem.isUseAmmo() && shopItem.alreadyBought();
        if (hideTitle){
            viewHolder.title.setText(resources.getString(shopItem.getTitle(), getShopItemValue(shopItem.getTitle())));
        }else{
            viewHolder.title.setText(resources.getString(shopItem.getTitle()));
        }
        viewHolder.discription.setText(shopItem.getDescriptionResId());
        if (shopItem.getCount()>=0){
            viewHolder.levels.setText(resources.getString(R.string.shop_item_level_template,shopItem.getCount(),shopItem.getLevelMax()));
            viewHolder.levels.setVisibility(View.VISIBLE);
        }else{
            viewHolder.levels.setVisibility(View.GONE);
        }

        boolean needBuyButton = true;//!shopItem.alreadyBought() || shopItem.isUseAmmo();



        if (!shopItem.haveNext()){
            viewHolder.price.setText("");
            viewHolder.buy.setText(R.string.btn_shop_max_level);
            needBuyButton = false;
        }

        if (needBuyButton){
            viewHolder.price.setText(resources.getString(R.string.item_shop_price, shopItem.getPriceBuy()));
        }else{
            viewHolder.price.setText("");
        }


        viewHolder.buy.setEnabled(needBuyButton);
        if (needBuyButton){
            viewHolder.buy.setText(R.string.btn_shop_buy);
            viewHolder.buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dataProcessor.buy(shop.getItem(position))){
                        notifyDataSetChanged();
                        callback.updateMoney();
                    }
                }
            });
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return shop.getSize();
    }

    private View inflateNewView(ViewGroup parent) {
        View view  = lInflater.inflate(R.layout.item_shop_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    private class ViewHolder {
        public final Button buy;
        public final TextView price;
        public final TextView value;
        public final TextView title;
        public final TextView levels;
        public final TextView discription;

        public ViewHolder(View view) {
           buy = (Button)view.findViewById(R.id.btn_shop_buy);
           price = (TextView)view.findViewById(R.id.item_shop_price);
           title = (TextView)view.findViewById(R.id.btn_shop_title);
           value = (TextView)view.findViewById(R.id.btn_shop_value);
           discription = (TextView) view.findViewById(R.id.btn_shop_discription);
           levels = (TextView)view.findViewById(R.id.btn_shop_levels_count);
        }
    }                       
}
