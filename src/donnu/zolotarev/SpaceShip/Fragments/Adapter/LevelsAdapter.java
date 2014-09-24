package donnu.zolotarev.SpaceShip.Fragments.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.LevelInfo;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import org.andengine.util.color.Color;

public class LevelsAdapter extends ArrayAdapter {


    private final LayoutInflater lInflater;
    private LevelController levelController;
    private ISimpleClick clickListener;

    public LevelsAdapter(Context context) {
        super(context, R.layout.item_level);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = inflateNewView(parent);
        ViewHolder holder = (ViewHolder)view.getTag();
        final LevelInfo levelInfo =  levelController.getLevelInfo(position);
        Resources resources = parent.getResources();

            holder.bk.setBackgroundColor(redraw(levelInfo).getARGBPackedInt());


            holder.start.setEnabled(levelInfo.isEnabled());


        holder.title.setText(resources.getString(R.string.item_level_title_template,levelInfo.getLevelId()));

        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(levelInfo.getLevelId());
            }
        });
        return view;
    }

    private Color redraw(LevelInfo item) {
        String name;
        Color color;
        if(item.getLevelId() == WaveContainer.LEVEL_INFINITY){
            color = Color.BLACK;
            item.setEnabled(true);
            item.setWin(true);
        }else if(item.getLevelId() == WaveContainer.LEVEL_TEST){
            color = Color.BLACK;
            item.setEnabled(true);
            item.setWin(true);
        }else if(item.getLevelId() == WaveContainer.LEVEL_MUSEUM){
            color = Color.BLACK;
            item.setEnabled(true);
            item.setWin(true);
        } else {
            name = String.valueOf(item.getLevelId());
            if (item.isEnabled()){
                if (item.isNew()){
                    color = Color.BLACK;
                }else {
                    if (item.isWin()){
                        color = Color.GREEN;
                    } else {
                        color = Color.RED;
                    }
                }
            } else {
                color = new Color(184/255f,  183/255f,  183/255f, 1);
            }
        }
        return color;
    }

    @Override
    public int getCount() {
        return levelController.getSize();
    }

    private View inflateNewView(ViewGroup parent) {
        View view  = lInflater.inflate(R.layout.item_level, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }

    public void setLevels(LevelController levelController) {
        this.levelController = levelController;
    }

    public void setClickListener(ISimpleClick click) {
        this.clickListener = click;
    }

    private class ViewHolder {
        public final TextView title;
        public final Button start;
        public RelativeLayout bk;

        public ViewHolder(View view) {
            bk = (RelativeLayout) view.findViewById(R.id.item_level_bk);
            title = (TextView) view.findViewById(R.id.item_level_title);
            start = (Button) view.findViewById(R.id.item_level_start);

        }
    }
}
