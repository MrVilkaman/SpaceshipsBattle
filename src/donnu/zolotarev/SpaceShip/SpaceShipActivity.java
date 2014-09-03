package donnu.zolotarev.SpaceShip;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.Scenes.MainMenu;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class SpaceShipActivity extends SimpleBaseGameActivity {


    private Camera camera;
    private MainMenu mainMenu;
    private static SpaceShipActivity instance;

    public static SpaceShipActivity getInstance() {
        return instance;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        Resources res = getResources();
//        CAMERA_WIDTH = res.getDisplayMetrics().widthPixels;
//        CAMERA_HEIGHT = res.getDisplayMetrics().heightPixels;
        camera = new Camera(0,0,Constants.CAMERA_WIDTH,Constants.CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                          new RatioResolutionPolicy(Constants.CAMERA_WIDTH,Constants.CAMERA_HEIGHT)
                ,camera);
        engineOptions.getTouchOptions().setNeedsMultiTouch(true);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        engineOptions.getAudioOptions().setNeedsSound(true);
        return engineOptions;
    }

    @Override
    protected void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        TextureLoader.loadTexture(this,getEngine());
    }

    @Override
    protected Scene onCreateScene() {
        mainMenu = new MainMenu();
        return mainMenu;
    }

    public static int getCameraWidth() {
        return Constants.CAMERA_WIDTH;
    }

    public static int getCameraHeight() {
        return Constants.CAMERA_HEIGHT;
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    protected void onDestroy() {
        finish();
//        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode == KeyEvent.KEYCODE_MENU || keyCode == KeyEvent.KEYCODE_BACK ) && event.getAction() == KeyEvent.ACTION_DOWN){
            mainMenu.onKeyPressed(keyCode, event);
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("XXX", "Its work,SpaceShipActivity onStart" + (mainMenu != null));
        if (mainMenu != null){
            mainMenu.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("XXX", "Its work,SpaceShipActivity onStop" + (mainMenu != null));
        if (mainMenu != null){
            mainMenu.onPause();
        }
    }

    public void exit() {
        onDestroy();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
