package donnu.zolotarev.SpaceShip;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

}
