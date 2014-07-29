package donnu.zolotarev.SpaceShip.Scenes;

import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
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
        menuScene = MenuFactory.createMenu()
                .addedItem(TextureLoader.getMenuNewGameTextureRegion(),new ISimpleClick() {
                    @Override
                    public void onClick() {
                        createGameScene();
                    }
                })
                .addedItem(TextureLoader.getMenuExitTextureRegion(), new ISimpleClick() {
                    @Override
                    public void onClick() {
                        activity.exit();
                    }
                })
                .enableAnimation()
                .build();
    }

    private void createGameScene() {
        if (mainScene == null){
            mainScene = new MainScene(this);
        }
        setChildScene(mainScene,false,true,true);
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
        BaseBullet.resetPool();
        detachChild(mainScene);
        mainScene.clearTouchAreas();
        mainScene.clearEntityModifiers();
        mainScene.clearUpdateHandlers();
        mainScene.back();
        mainScene = null;
        BaseUnit.resetPool();
        text.setVisible(true);
    }

    @Override
    public void restart() {
        createGameScene();
    }
}
