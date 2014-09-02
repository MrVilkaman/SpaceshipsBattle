package donnu.zolotarev.SpaceShip.Scenes.Interfaces;

import android.view.KeyEvent;

public interface IActivityCallback {
    public void onKeyPressed(int keyCode, KeyEvent event);
    public void onResume();
    public void onPause();
}
