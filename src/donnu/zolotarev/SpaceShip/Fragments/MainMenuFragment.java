package donnu.zolotarev.SpaceShip.Fragments;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick2;
import donnu.zolotarev.SpaceShip.Utils.ImageChangeManager;

public class MainMenuFragment extends BaseMenuFragment {


    @InjectView(R.id.btn_main_menu_continue)
    Button btnContinue;

    @InjectView(R.id.image_background)
    ImageView imageBack;

    @InjectView(R.id.txt_main_menu_version)
    TextView versionInfoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_main_menu, inflater, container);

       /* Drawable[] layers = new Drawable[3];
        layers[0] = getActivity().getResources().getDrawable(R.drawable.main_wall_2);
        layers[1] = getActivity().getResources().getDrawable(R.drawable.main_wall_3);
        layers[2] = getActivity().getResources().getDrawable(R.drawable.main_wall_4);

        TransitionDrawable tr = new TransitionDrawable(layers);
        imageBack.setImageDrawable(tr);
        tr.startTransition(2000);*/

        new ImageChangeManager(getActivity(),imageBack, new int[]{
                R.drawable.main_wall_1,
                R.drawable.main_wall_2,
                R.drawable.main_wall_3,
                R.drawable.main_wall_4,
                R.drawable.main_wall_5,
                R.drawable.main_wall_6
        }).start();
        //imageBack.setImageDrawable();
        versionInfoUpdate();

        PackageInfo packinfo = null;

        try {
            packinfo = getActivity().getPackageManager().getPackageInfo("donnu.zolotarev.SpaceShip", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (!actualCodeVersion() && haveCurrentGame()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showAlert(getActivity(), R.string.msg_sorry_message, "Упс(");
                }
            });
            clearCurrentGame();
        }

        if (haveCurrentGame()){
            btnContinue.setVisibility(View.VISIBLE);
        }else{
            btnContinue.setVisibility(View.GONE);
        }

        return view;
    }

    private void versionInfoUpdate() {
        PackageInfo packinfo = null;
        try {
            packinfo = getActivity().getPackageManager().getPackageInfo("donnu.zolotarev.SpaceShip", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
        }
        versionInfoView.setText("v" + packinfo.versionName.toString());
    }

    @OnClick(R.id.btn_main_menu_new_game)
    public void onNewGame(){
        if (haveCurrentGame()){
            DialogFragment fragment = new DialogFragment();
            fragment.show(getFragmentManager(),"1");
            fragment.setTitle(getString(R.string.dialog_text_new_game_message));
            fragment.setOkListener(new ISimpleClick2() {
                @Override
                public void onClick() {
                    clearCurrentGame();
                    openSelectLevels();
                }
            });
            fragment.setCancelListener(new ISimpleClick2() {
                @Override
                public void onClick() {

                }
            });

        }else{
            openSelectLevels();
        }
    }

    @OnClick(R.id.btn_main_menu_continue)
    public void onContinue(){
        openSelectLevels();
    }

    @OnClick(R.id.btn_main_menu_about)
    public void onAbout(){

        DialogFragment fragment = new DialogFragment();
        fragment.show(getFragmentManager(),"1");
        fragment.setTitle(getActivity().getString(R.string.msg_about));
        fragment.setViewResId(R.layout.about);
        fragment.setOkListener(new ISimpleClick2() {
            @Override
            public void onClick() {

            }
        });

    }

    @OnClick(R.id.btn_main_menu_exit)
    public void onExit(){
        getMainActivity().showExitDialog();
    }

    private void openSelectLevels(){
        showFragment(new SelectLevelFragment(),true);
    }

    public void clearCurrentGame(){

        getActivity().getSharedPreferences(FILE_GAME_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_USER_STATS,"")
                .putString(PREF_HERO_STATS,"")
                .putString(PREF_SHOP_ITEMS,"")
                .commit();
        getActivity().getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .edit().putString(PREF_LEVELS,"")
                .commit();
    }

}
