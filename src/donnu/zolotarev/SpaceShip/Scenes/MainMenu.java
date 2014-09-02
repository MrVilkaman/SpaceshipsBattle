package donnu.zolotarev.SpaceShip.Scenes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.HALIGMENT;
import donnu.zolotarev.SpaceShip.Utils.MenuFactory;
import donnu.zolotarev.SpaceShip.Utils.WALIGMENT;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.text.Text;

public class MainMenu extends MyScene implements IParentScene {
    private static final int MENU_NEWGAME = 0;
    private static final int MENU_EXIT = MENU_NEWGAME + 1;

    private final SpaceShipActivity activity;
    private final Engine engine;
    private Text text;
    private MyScene infinityGameScene;
    private MenuScene menuScene;
    private Runnable runnableAbout;

    public MainMenu() {
        super(null);
        activity = SpaceShipActivity.getInstance();
        engine = activity.getEngine();
        setBackground(new Background(0.9f, 0.9f, 0.9f));
        createMenuScene();
        setChildScene(menuScene, false, true, true);
        /*text = new Text(450,SpaceShipActivity.getCameraHeight()-100,
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
        });*/
    }

    protected void createMenuScene() {
        if (menuScene != null){
            menuScene.detachSelf();
            menuScene = null;
        }

        ISimpleClick clickResumeGame = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                createGameScene();
            }
        };

        ISimpleClick clickExit =  new ISimpleClick() {
            @Override
            public void onClick(int id) {
                showExitDialog();
            }
        };

        ISimpleClick clickNewGame = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                if (haveCurrentGame()){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(activity)
                                    .setMessage(R.string.dialog_text_new_game_message)
                                    .setPositiveButton(R.string.dialog_text_yes, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            clearCurrentGame();
                                            createGameScene();
                                        }
                                    })
                                    .setNegativeButton(R.string.dialog_text_no, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();
                        }
                    });
                    activity.runOnUiThread(runnableAbout);
                } else {
                    clearCurrentGame();
                    createGameScene();
                }

            }
        };
        ISimpleClick clickAbout = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                showAboutDialog();
            }
        };

        PackageInfo packinfo = null;

        try {
            packinfo = activity.getPackageManager().getPackageInfo("donnu.zolotarev.SpaceShip", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (!actualCodeVersion() && haveCurrentGame()){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showAlert(activity, R.string.msg_sorry_message, "Упс(");
                }
            });
            clearCurrentGame();
        }


        MenuFactory m = MenuFactory.createMenu(engine, activity.getCamera());
        if (haveCurrentGame()){
            m.addedItem(TextureLoader.getMenuResumeTextureRegion(), clickResumeGame);
        }
        m.addedItem(TextureLoader.getMenuNewGameTextureRegion(), clickNewGame)
                .addedItem(TextureLoader.getMenuAboutTextureRegion(), clickAbout)
                .addedItem(TextureLoader.getMenuExitTextureRegion(), clickExit)
                .addedText("v" + packinfo.versionName.toString(), TextureLoader.getFont(), 0, Constants.CAMERA_HEIGHT, WALIGMENT.LEFT,
                        HALIGMENT.BOTTOM)
                .enableAnimation();
        menuScene = m.build();

    }

    private void createGameScene() {
        if (infinityGameScene == null){
            infinityGameScene = new SelectionLevelScene(this);
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

    @Override
    public void onResume() {

        if (infinityGameScene != null){
            infinityGameScene.onResume();
        }
    }

    @Override
    public void onPause() {
        Log.i("XXX", "Its work, " + (infinityGameScene != null));
        if (infinityGameScene != null){
            Log.i("XXX", "Its work, inside");
            infinityGameScene.onPause();
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
        createMenuScene();
        setChildScene(menuScene, false, true, true);
    }

    @Override
    public void restart(int statusCode) {
        createGameScene();
    }

    @Override
    public void callback(int statusCode) {

    }

    private void showAboutDialog(){

        if (runnableAbout == null){
            runnableAbout =  new Runnable(){
               @Override
               public void run() {
                   View view =  activity.getLayoutInflater().inflate(R.layout.about, null);
                   AlertDialog.Builder builderAbout = new AlertDialog.Builder(activity);
                   builderAbout.setTitle(R.string.msg_about)
                           .setView(view)
                           .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {
                               }
                           }).show();
               }
           };
        }
        activity.runOnUiThread(runnableAbout);
    }
}
