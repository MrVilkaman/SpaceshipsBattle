package donnu.zolotarev.SpaceShip.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Scenes.MyScene;
import donnu.zolotarev.SpaceShip.Scenes.SelectionLevelScene;
import donnu.zolotarev.SpaceShip.Textures.MusicLoader;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class GameActivity extends SimpleBaseGameActivity implements IParentScene {


    public static final String EXTRA_LEVEL = "level";
    private int level;
    private Camera camera;
    private MyScene mainMenu;
    private static GameActivity instance;


    public static Intent createIntent(Context context, int levelId){
        Intent intent = new Intent(context,GameActivity.class);
        intent.putExtra(GameActivity.EXTRA_LEVEL,levelId);
      //      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static GameActivity getInstance() {
        return instance;
    }

    public static Engine engine() {
        return instance.getEngine();
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        level = getIntent().getExtras().getInt(EXTRA_LEVEL);
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        Resources res = getResources();
//        CAMERA_WIDTH = res.getDisplayMetrics().widthPixels;
//        CAMERA_HEIGHT = res.getDisplayMetrics().heightPixels;
        camera = new Camera(0,0,Constants.CAMERA_WIDTH,Constants.CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                          new FillResolutionPolicy()
                ,camera);
        engineOptions.getTouchOptions().setNeedsMultiTouch(true);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        engineOptions.getAudioOptions().setNeedsSound(true);
        return engineOptions;
    }

    @Override
    protected void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        SoundFactory.setAssetBasePath("mfx/");
        MusicFactory.setAssetBasePath("mfx/");
        TextureLoader.loadTexture(this,getEngine());
        MusicLoader.loadTexture(this,getEngine());
    }

    @Override
    protected Scene onCreateScene() {
        mainMenu = new SelectionLevelScene(this,level);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode == KeyEvent.KEYCODE_MENU || keyCode == KeyEvent.KEYCODE_BACK ) && event.getAction() == KeyEvent.ACTION_DOWN){
            mainMenu.onKeyPressed(keyCode, event);
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
          //  return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MusicLoader.getSound()!=null){
            if (!MusicLoader.getSound().isReleased()){
                MusicLoader.getSound().resume();
            }
        }
        Log.i("XXX", "Its work,SpaceShipActivity onStart" + (mainMenu != null));
        if (mainMenu != null){
            mainMenu.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicLoader.getSound().pause();
        Log.i("XXX", "Its work,SpaceShipActivity onStop" + (mainMenu != null));
        if (mainMenu != null){
            mainMenu.onPause();
        }
    }

    public void exit() {
        onDestroy();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onDestroyResources() throws Exception {
        super.onDestroyResources();
        TextureLoader.clearMemory();
        MusicLoader.clear();
    }

    @Override
    public void returnToParentScene(int statusCode) {
        Intent intent =  new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void restart(int statusCode) {
    }

}
