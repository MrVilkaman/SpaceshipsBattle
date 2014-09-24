package donnu.zolotarev.SpaceShip;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import donnu.zolotarev.SpaceShip.Fragments.MainMenuFragment;

public class MenuActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MainMenuFragment();
    }

    public void loadRootFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.replace(getViewPlaceID(), fragment).commit();
    }

    @Override
    public void onBackPressed() {
        int backStackEntries = getFragmentManager().getBackStackEntryCount();
        if(backStackEntries == 0){
            showExitDialog();
        } else {
            MenuActivity.super.onBackPressed();
        }
    }

    public void popBackStack(){
        getFragmentManager().popBackStack();
    }

    public void showExitDialog() {
        // Диалог выхода
        final Activity self = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(self).setMessage(R.string.dialog_text_exit_message)
                        .setPositiveButton(R.string.dialog_text_yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        self.finish();
                                    }
                                }).setNegativeButton(R.string.dialog_text_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });

    }
}
