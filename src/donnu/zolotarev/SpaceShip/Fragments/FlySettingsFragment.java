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
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.gson.Gson;
import donnu.zolotarev.SpaceShip.GameData.Settings;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick2;
import donnu.zolotarev.SpaceShip.UI.FileMode;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.SoundHelper;
import org.andengine.util.color.Color;

public class FlySettingsFragment extends android.app.DialogFragment {

    @InjectView(R.id.setting_sound)
    Button vibroButton;

    @InjectView(R.id.setting_music)
    Button musicButton;

    @InjectView(R.id.radiogroup_fire_mode_byhold)
    RadioButton fireModeRadioByhold;

    @InjectView(R.id.radiogroup_fire_mode_always)
    RadioButton fireModeRadioAlways;

    @InjectView(R.id.radiogroup_fire_mode)
    RadioGroup fireModeRadio;

    @InjectView(R.id.radiogroup_fly_mode)
    RadioGroup flyModeRadio;

    private Settings setting;
    boolean vibro = false;
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
        vibro = setting.needVibro();
        changeVibroIcon(vibro);
        changeMusicIcon(music);
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT_ARGB_PACKED_INT));

        if (setting.getFileMode() == FileMode.BY_HOLD){
            fireModeRadio.check(R.id.radiogroup_fire_mode_byhold);
//            fireModeRadioByhold.setChecked(true);
        }else{
            fireModeRadio.check(R.id.radiogroup_fire_mode_always);
            //            fireModeRadioAlways.setChecked(true);
        }
        if (setting.isAnalogflyControlMode()){
            flyModeRadio.check(R.id.radiogroup_fly_mode_analog);
        }else{
            flyModeRadio.check(R.id.radiogroup_fly_mode_touch);
        }


        return v;
    }

    @Override
    public void onDestroyView() {
        FileMode mode = null;
        super.onDestroyView();
        if (fireModeRadio.getCheckedRadioButtonId() == R.id.radiogroup_fire_mode_byhold){
            mode = FileMode.BY_HOLD;
        }else {
            mode = FileMode.ALWAIS;
        }


        setting.setAnalogflyControlMode(flyModeRadio.getCheckedRadioButtonId() == R.id.radiogroup_fly_mode_analog);
        setting.setFireMode(mode);
        setting.setMusic(music);
        setting.setNeedVibro(vibro);
        Gson gson = new Gson();
        getActivity().getSharedPreferences(BaseMenuFragment.FILE_SETTINGS, Context.MODE_PRIVATE)
                .edit()
                .putString(BaseMenuFragment.PREF_USER_STATS,gson.toJson(Settings.get()))
                .commit();
    }

    //
    @OnClick(R.id.setting_sound)
    void onChangeSoundState(){
        vibro = ! vibro;
        changeVibroIcon(vibro);
    }

    @OnClick(R.id.setting_music)
    void changeMusicIcon(){
        music = ! music;
        changeMusicIcon(music);
        changeMusicState(music);
    }

    private void changeMusicState(boolean music) {
        if (music){
            SoundHelper.menuSoundOn(getActivity());
        }else{
            SoundHelper.menuSoundOff();
        }
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

        view.findViewById(R.id.btn_credits).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.credits_text,null);
                donnu.zolotarev.SpaceShip.Fragments.DialogFragment fragment = new donnu.zolotarev.SpaceShip.Fragments.DialogFragment();
                fragment.show(getFragmentManager(),"1");
                fragment.setTitle(getActivity().getString(R.string.msg_about));
                fragment.setView(view2);
                fragment.setOkListener(new ISimpleClick2() {
                    @Override
                    public void onClick() {

                    }
                });
                ((TextView)view2.findViewById(R.id.textView)).setText(R.string.credits_text);
            }
        });

    }

    private void changeVibroIcon(boolean isEnable){
        Drawable icon = null;
        if (isEnable){
            icon = getResources().getDrawable(R.drawable.vibro_on);
        } else  {
            icon = getResources().getDrawable(R.drawable.vibro_off);
        }

        vibroButton.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    private void changeMusicIcon(boolean isEnable){
        Drawable icon = null;
        if (isEnable){
            icon = getResources().getDrawable(R.drawable.sound_on);
        } else  {
            icon = getResources().getDrawable(R.drawable.sound_off);
        }

        musicButton.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    private static Display getDefaultDisplay(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }
}
