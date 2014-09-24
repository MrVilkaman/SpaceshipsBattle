package donnu.zolotarev.SpaceShip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ShopAdapter extends ArrayAdapter{
    private final LayoutInflater lInflater;

    public ShopAdapter(Context context) {
        super(context, R.layout.item_shop_item);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflateNewView(parent);
        ViewHolder viewHolder = (ViewHolder)view.getTag();

        viewHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return 10;
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
