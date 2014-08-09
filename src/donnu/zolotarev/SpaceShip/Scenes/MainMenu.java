package donnu.zolotarev.SpaceShip.Scenes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.MenuFactory;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;

public class MainMenu extends MyScene implements IParentScene {
    private static final int MENU_NEWGAME = 0;
    private static final int MENU_EXIT = MENU_NEWGAME + 1;

    private final SpaceShipActivity activity;
    private final Engine engine;
    private final Text text;
    private MyScene infinityGameScene;
    private MenuScene menuScene;

    public MainMenu() {
        super(null);
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
        ISimpleClick click = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                createGameScene();
            }
        };

        ISimpleClick click2 =  new ISimpleClick() {
            @Override
            public void onClick(int id) {
                showExitDialog();
            }
        };

        menuScene = MenuFactory.createMenu(engine, activity.getCamera())
                .addedItem(TextureLoader.getMenuNewGameTextureRegion(), click)
                .addedItem(TextureLoader.getMenuExitTextureRegion(), click2)
                .enableAnimation()
                .build();

    }

    private void createGameScene() {
        if (infinityGameScene == null){
            // todo вернуть
            infinityGameScene = new SelectionLevelScene(this);//new InfinityGameScene(this);
        }
        setChildScene(infinityGameScene,false,true,true);
    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {

        if (infinityGameScene != null){
            infinityGameScene.onKeyPressed(keyCode, event);
        }else if(keyCode == KeyEvent.KEYCODE_BACK ){
            showExitDialog();
        }
    }

    private void showExitDialog() {
        // Диалог выхода
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(activity)
                        .setMessage(R.string.dialog_text_exit_message)
                        .setPositiveButton(R.string.dialog_text_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                activity.finish();
                            }
                        })
                        .setNegativeButton(R.string.dialog_text_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }

    @Override
    public void returnToParentScene(int statusCode) {
        detachChild(infinityGameScene);
        infinityGameScene.clearTouchAreas();
        infinityGameScene.clearEntityModifiers();
        infinityGameScene.clearUpdateHandlers();
        infinityGameScene.back();
        infinityGameScene = null;
       // text.setVisible(true);
        setChildScene(menuScene, false, true, true);
    }

    @Override
    public void restart() {
        createGameScene();
    }
}
