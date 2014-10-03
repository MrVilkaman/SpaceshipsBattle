package donnu.zolotarev.SpaceShip.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import donnu.zolotarev.SpaceShip.Fragments.DialogFragment;
import donnu.zolotarev.SpaceShip.Fragments.MainMenuFragment;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick2;

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
        DialogFragment fragment = new DialogFragment();
        fragment.show(getFragmentManager(), "1");
        fragment.setTitle(getString(R.string.dialog_text_exit_message));
        fragment.setOkListener(new ISimpleClick2() {
            @Override
            public void onClick() {
                self.finish();
            }
        });
        fragment.setCancelListener(new ISimpleClick2() {
            @Override
            public void onClick() {

            }
        });

    }
}
