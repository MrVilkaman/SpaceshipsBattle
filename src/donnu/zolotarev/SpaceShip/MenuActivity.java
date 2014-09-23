package donnu.zolotarev.SpaceShip;

import android.app.Fragment;
import donnu.zolotarev.SpaceShip.Fragments.MainMenu;

public class MenuActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MainMenu();
    }
}
