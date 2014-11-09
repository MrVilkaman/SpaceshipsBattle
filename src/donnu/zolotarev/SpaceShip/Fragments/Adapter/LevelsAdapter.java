package donnu.zolotarev.SpaceShip.Fragments.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.LevelInfo;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import org.andengine.util.color.Color;

public class LevelsAdapter extends ArrayAdapter {


    private final LayoutInflater lInflater;
    private final Resources resources;
    private LevelController levelController;
    private ISimpleClick clickListener;

    public LevelsAdapter(Context context) {
        super(context, R.layout.item_level);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        levelController = LevelController.getInstance();
        resources = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflateNewView(parent);
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        final LevelInfo levelInfo = levelController.getLevelInfoForAdapter(position);


        //holder.bk.setBackgroundColor(resources.getColor(R.color.item_level_bk_color));
        holder.status.setTextColor(redraw(levelInfo).getARGBPackedInt());
        holder.status.setText(getStatusMessage(levelInfo));

        if (levelInfo.isEnabled()){
            holder.total.setVisibility(View.VISIBLE);
        }else{
            holder.total.setVisibility(View.INVISIBLE);
        }

        holder.totalCoast.setText(resources.getString(R.string.item_level_total_coast,levelInfo.getTotalCoast()));
        holder.totalGames.setText(resources.getString(R.string.item_level_total_games,levelInfo.getTotalGame()));
        holder.totalWins.setText(resources.getString(R.string.item_level_total_wins,levelInfo.getTotalWin()));
        holder.totalLose.setText(resources.getString(R.string.item_level_total_lose,levelInfo.getTotalLose()));


        holder.start.setEnabled(levelInfo.isEnabled());

        holder.title.setText(resources.getString(R.string.item_level_title_template,levelInfo.getLevelId()));
        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(levelInfo.getLevelId());
            }
        });
        return convertView;
    }

    private int getStatusMessage(LevelInfo item){
        int id;
        if (item.isEnabled()){
            if (item.isNew()){
                id = R.string.txt_status_new;
            }else {
                if (item.isWin()){
                    id = R.string.txt_status_gone;
                } else {
                    id = R.string.txt_status_lose;
                }
            }
        } else {
            id = R.string.txt_status_disable;
        }
        return id;
    }

    private Color redraw(LevelInfo item) {
        Color color;
        if (item.isEnabled()){
            if (item.isNew()){
                color = Color.WHITE;
            }else {
                if (item.isWin()){
                    color = Color.GREEN;
                } else {
                    color = Color.RED;
                }
            }
        } else {
            color = Color.WHITE;
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

    public void setClickListener(ISimpleClick click) {
        this.clickListener = click;
    }

    private class ViewHolder {
        public final TextView title;
        public final Button start;
        public final TextView status;
        public final RelativeLayout bk;
        public final LinearLayout total;
        public final TextView totalWins;
        public final TextView totalCoast;
        public final TextView totalLose;
        public final TextView totalGames;

        public ViewHolder(View view) {
            bk = (RelativeLayout) view.findViewById(R.id.item_level_bk);
            title = (TextView) view.findViewById(R.id.item_level_title);
            start = (Button) view.findViewById(R.id.item_level_start);
            status = (TextView) view.findViewById(R.id.item_level_status);

            total = (LinearLayout) view.findViewById(R.id.item_level_total);
            totalWins = (TextView) view.findViewById(R.id.item_level_total_wins);
            totalCoast = (TextView) view.findViewById(R.id.item_level_total_coast);
            totalLose = (TextView) view.findViewById(R.id.item_level_total_lose);
            totalGames = (TextView) view.findViewById(R.id.item_level_total_games);

        }
    }
}
