package donnu.zolotarev.SpaceShip.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.gson.Gson;
import donnu.zolotarev.SpaceShip.GameData.Settings;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick2;
import donnu.zolotarev.SpaceShip.UI.ControlMode;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import org.andengine.util.color.Color;

public class FlySettingsFragment extends android.app.DialogFragment {

    @InjectView(R.id.setting_sound)
    Button soundButton;

    @InjectView(R.id.setting_music)
    Button musicButton;

    @InjectView(R.id.radiogroup_fire_mode_byhold)
    RadioButton fireModeRadioByhold;

    @InjectView(R.id.radiogroup_fire_mode_always)
    RadioButton fireModeRadioAlways;

    @InjectView(R.id.radiogroup_fire_mode)
    RadioGroup fireModeRadio;

    private Settings setting;
    boolean sound = false;
    boolean music = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setting = Settings.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_fly_settings, null);
        ButterKnife.inject(this, v);
        Point screeSize = new Point();
        getDefaultDisplay(getActivity()).getSize(screeSize);
        v.setMinimumWidth(2*screeSize.x/3);
        //  v.setMinimumHeight(screeSize.y);
        music = setting.isMusic();
        sound = setting.isSound();
        changeSoundIcon(sound);
        changeMusicIcon(music);
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT_ARGB_PACKED_INT));

        if (setting.getControlMode() == ControlMode.BY_HOLD){
            fireModeRadioByhold.setChecked(true);
        }else{
            fireModeRadioAlways.setChecked(true);
        }
        return v;
    }

    @Override
    public void onDestroyView() {
        ControlMode mode = null;
        super.onDestroyView();
        if (fireModeRadio.getCheckedRadioButtonId() == R.id.radiogroup_fire_mode_byhold){
            mode = ControlMode.BY_HOLD;
        }else {
            mode = ControlMode.ALWAIS;
        }
        setting.setControlMode(mode);
        setting.setMusic(music);
        setting.setSound(sound);
        Gson gson = new Gson();
        getActivity().getSharedPreferences(BaseMenuFragment.FILE_SETTINGS, Context.MODE_PRIVATE)
                .edit()
                .putString(BaseMenuFragment.PREF_USER_STATS,gson.toJson(Settings.get()))
                .commit();
    }

    //
    @OnClick(R.id.setting_sound)
    void onChangeSoundState(){
        sound = ! sound;
        changeSoundIcon(sound);
    }

    @OnClick(R.id.setting_music)
    void changeMusicIcon(){
        music = ! music;
        changeMusicIcon(music);
    }

    @OnClick(R.id.cansel)
    void onCancel(){
        dismiss();
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
