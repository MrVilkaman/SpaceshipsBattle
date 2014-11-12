package donnu.zolotarev.SpaceShip.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick2;
import donnu.zolotarev.SpaceShip.Utils.Constants;

public class FlySettingsFragment extends DialogFragment {

    @InjectView(R.id.setting_sound)
    Button soundButton;

    @InjectView(R.id.setting_music)
    Button musicButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_fly_settings, null);
        ButterKnife.inject(this,v);
        Point screeSize = new Point();
        getDefaultDisplay(getActivity()).getSize(screeSize);
        v.setMinimumWidth(2*screeSize.x/3);
      //  v.setMinimumHeight(screeSize.y);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setView(v);
        changeSoundIcon(flag);
    changeMusicIcon(flag2);
        return alertDialog.create();
    }

    //
    boolean flag = false;
    boolean flag2 = false;
    @OnClick(R.id.setting_sound)
    void onChangeSoundState(){
        flag = ! flag;
        changeSoundIcon(flag);
    }

    @OnClick(R.id.setting_music)
    void changeMusicIcon(){
        flag2 = ! flag2;
        changeMusicIcon(flag2);
    }

    @OnClick(R.id.setting_credits_btn)
    public void onAbout(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.about,null);
        donnu.zolotarev.SpaceShip.Fragments.DialogFragment fragment = new donnu.zolotarev.SpaceShip.Fragments.DialogFragment();
        fragment.show(getFragmentManager(),"1");
        fragment.setTitle(getActivity().getString(R.string.msg_about));
        fragment.setView(view);
        fragment.setOkListener(new ISimpleClick2() {
            @Override
            public void onClick() {

            }
        });
        view.findViewById(R.id.author_link_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse(Constants.VK_AUTHOR_PAGE_LINK);
                Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                getActivity().startActivity(openlink);
            }
        });
        view.findViewById(R.id.offical_group_link_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse(Constants.VK_GROUP_LINK);
                Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                getActivity().startActivity(openlink);
            }
        });

    }

    private void changeSoundIcon(boolean isEnable){
        Drawable icon = null;
        if (isEnable){
            icon = getResources().getDrawable(R.drawable.sound_on);
        } else  {
            icon = getResources().getDrawable(R.drawable.sound_off);
        }

        soundButton.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    private void changeMusicIcon(boolean isEnable){
        Drawable icon = null;
        if (isEnable){
            icon = getResources().getDrawable(R.drawable.music_on);
        } else  {
            icon = getResources().getDrawable(R.drawable.music_off);
        }

        musicButton.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    private static Display getDefaultDisplay(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }
}
