package donnu.zolotarev.SpaceShip.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.R;

public class MainMenuFragment extends BaseMenuFragment {


    @InjectView(R.id.btn_main_menu_continue)
    Button btnContinue;

    @InjectView(R.id.txt_main_menu_version)
    TextView versionInfoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_main_menu, inflater, container);


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
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.dialog_text_new_game_message)
                    .setPositiveButton(R.string.dialog_text_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            clearCurrentGame();
                            openSelectLevels();
                        }
                    })
                    .setNegativeButton(R.string.dialog_text_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
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
        View view =  getActivity().getLayoutInflater().inflate(R.layout.about, null);
        AlertDialog.Builder builderAbout = new AlertDialog.Builder(getActivity());
        builderAbout.setTitle(R.string.msg_about)
                .setView(view)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
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
