package donnu.zolotarev.SpaceShip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import donnu.zolotarev.SpaceShip.GameData.Shop;
import donnu.zolotarev.SpaceShip.GameData.ShopItem;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;

public class ShopAdapter extends ArrayAdapter{
    private final LayoutInflater lInflater;
    private final Shop shop;
    private final UserDataProcessor dataProcessor;
    private final Callback callback;
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
        View view = inflateNewView(parent);
        ViewHolder viewHolder = (ViewHolder)view.getTag();

       final ShopItem shopItem = shop.getItem(position);

        viewHolder.title.setText(shopItem.getTitle());
        viewHolder.value.setText(shopItem.getDescription());

        boolean needBuyButton = true;//!shopItem.alreadyBought() || shopItem.isUseAmmo();



        if (needBuyButton){
            viewHolder.price.setText(parent.getContext().getString(R.string.item_shop_price,shopItem.getPriceBuy()));
        }else{
            viewHolder.price.setText("");
        }

        if (!shopItem.haveNext()){
            viewHolder.price.setText("");
            viewHolder.buy.setText(R.string.btn_shop_max_level);
            needBuyButton = false;
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
        return view;
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

        public ViewHolder(View view) {
           buy = (Button)view.findViewById(R.id.btn_shop_buy);
           price = (TextView)view.findViewById(R.id.item_shop_price);
           title = (TextView)view.findViewById(R.id.btn_shop_title);
           value = (TextView)view.findViewById(R.id.btn_shop_value);
        }
    }                       
}
