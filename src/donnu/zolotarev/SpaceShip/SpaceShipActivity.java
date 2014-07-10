package donnu.zolotarev.SpaceShip;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.Scenes.MainMenu;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class SpaceShipActivity extends SimpleBaseGameActivity {


    private static int CAMERA_WIDTH = 1280;
    private static int CAMERA_HEIGHT = 720;
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
        camera = new Camera(0,0,CAMERA_WIDTH,CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                          new RatioResolutionPolicy(CAMERA_WIDTH,CAMERA_HEIGHT)
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
        return CAMERA_WIDTH;
    }

    public static int getCameraHeight() {
        return CAMERA_HEIGHT;
    }
    public Camera getCamera() {
        return camera;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN){
            mainMenu.onKeyPressed(keyCode, event);
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        onDestroy();
    }
}
