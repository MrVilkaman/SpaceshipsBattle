package donnu.zolotarev.SpaceShip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

public class LevelsAdapter extends ArrayAdapter {

    private final LayoutInflater lInflater;

    public LevelsAdapter(Context context) {
        super(context, R.layout.item_level);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflateNewView(parent);
        ViewHolder holder = (ViewHolder)view.getTag();
        if(position%2 == 0){
            holder.bk.setBackgroundColor( parent.getResources().getColor(R.color.item_level_bk_color_2));
        }
        return view;
    }

    @Override
    public int getCount() {
        return 10;
    }

    private View inflateNewView(ViewGroup parent) {
        View view  = lInflater.inflate(R.layout.item_level, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }

    private class ViewHolder {
        public RelativeLayout bk;

        public ViewHolder(View view) {
            bk = (RelativeLayout) view.findViewById(R.id.item_level_bk);
        }
    }
}
