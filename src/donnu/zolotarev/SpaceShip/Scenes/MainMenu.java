package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;

public class MainMenu extends Scene implements IParentScene {
    private static final int MENU_NEWGAME = 0;
    private static final int MENU_EXIT = MENU_NEWGAME + 1;

    private final SpaceShipActivity activity;
    private final Engine engine;
    private final Text text;
    private MainScene mainScene;
    private MenuScene menuScene;

    public MainMenu() {
        activity = SpaceShipActivity.getInstance();
        engine = activity.getEngine();
        setBackground(new Background(0.9f, 0.9f, 0.9f));
        createMenuScene();


        text = new Text(450,SpaceShipActivity.getCameraHeight()-100,
                TextureLoader.getFont(),"Коснитесь для продолжения!",new TextOptions(HorizontalAlign.LEFT),engine.getVertexBufferObjectManager());
        attachChild(text);
        text.setVisible(true);
        setOnSceneTouchListener(new IOnSceneTouchListener() {
            @Override
            public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
                text.setVisible(false);
                setChildScene(menuScene, false, true, true);
                return true;
            }
        });

    }

    protected void createMenuScene() {
        this.menuScene = new MenuScene(activity.getCamera());

        final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_NEWGAME, TextureLoader.getMenuNewGameTextureRegion(),
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.menuScene.addMenuItem(resetMenuItem);

        final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_EXIT, TextureLoader.getMenuExitTextureRegion(),
                engine.getVertexBufferObjectManager());
        quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.menuScene.addMenuItem(quitMenuItem);

        this.menuScene.buildAnimations();

        this.menuScene.setBackgroundEnabled(false);

        final IParentScene self = this;
        this.menuScene.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
                    float pMenuItemLocalY) {
                switch (pMenuItem.getID()){
                    case MENU_NEWGAME:
                        if (mainScene == null){
                            mainScene = new MainScene(self);
                        }
                        text.setVisible(true);
                    setChildScene(mainScene,false,true,true);
                        break;
                    case MENU_EXIT:
                        activity.exit();
                        break;
                }
                return false;
            }
        });
    }

    public void onKeyPressed(int keyCode, KeyEvent event) {

        if (mainScene != null){
            mainScene.onKeyPressed(keyCode, event);
        }else if(keyCode == KeyEvent.KEYCODE_BACK ){
         // Диалог выхода
            activity.finish();
        }
    }

    @Override
    public void returnToParentScene() {
        mainScene.clearTouchAreas();
        mainScene.clearEntityModifiers();
        mainScene.clearUpdateHandlers();
        mainScene.back();
        mainScene = null;
    }
}
