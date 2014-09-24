package donnu.zolotarev.SpaceShip.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.R;

public class MainMenuFragment extends BaseMenuFragment {

    @InjectView(R.id.txt_main_menu_version)
    TextView versionInfoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_main_menu, inflater, container);

        versionInfoUpdate();
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
        openSelectLevels();
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

}
