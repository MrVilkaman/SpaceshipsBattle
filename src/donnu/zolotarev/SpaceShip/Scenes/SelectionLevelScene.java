package donnu.zolotarev.SpaceShip.Scenes;

import android.content.Context;
import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.LevelController;
import donnu.zolotarev.SpaceShip.LevelInfo;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.util.color.Color;

import java.util.Iterator;

public class SelectionLevelScene extends MyScene implements IParentScene {
    private static final int LEVEL_INFINITY = 0;
    private static final int LEVEL_1 = 1;
    private static final String FILE_LEVELS = "file_levels";
    private static final String PREF_LEVELS = "pref_levels";

    private final SpaceShipActivity shipActivity;
    private final Engine engine;
    private final IParentScene parentScene;
    private final SelectionLevelScene self;
    private BaseGameScene  gameScene;
    private MenuScene menuFactory;
    private int activeLevel;
    private LevelController levels;
    private int lastSceneId;

    public SelectionLevelScene(IParentScene parentScene) {
        super(parentScene);
        self = this;
        this.parentScene = parentScene;
        shipActivity = SpaceShipActivity.getInstance();
       engine = shipActivity.getEngine();
       setBackground(new Background(Color.WHITE));
       initLevels();
       initUI();
    }

    private void initLevels() {
        String levelsJson =  shipActivity.getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .getString(PREF_LEVELS,"");
        if (!levelsJson.isEmpty()){
            levels = new LevelController(levelsJson);
        } else {
            levels = new LevelController();
            levels.addLevel(LEVEL_INFINITY, 100, 100, true);
            levels.addLevel(LEVEL_1, 200,300, false);
            levels.addLevel(LEVEL_1, 300,350, false);
            levels.addLevel(LEVEL_1, 400,180, false);
        }


    }

    private void initUI() {
        int x = Constants.CAMERA_WIDTH_HALF;
        int y = 0;
        menuFactory = MenuFactory.createMenu(engine,shipActivity.getCamera())
                .addedItem(TextureLoader.getChangeLevelLableTextureRegion1(), Constants.CAMERA_WIDTH_HALF,0,WALIGMENT.CENTER, HALIGMENT.TOP )
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(), new ISimpleClick() {
                    @Override
                    public void onClick(int id) {
                        parentScene.returnToParentScene(- 1);
                    }
                }, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT, WALIGMENT.RIGHT, HALIGMENT.BOTTOM)
                .enableAnimation().build();


        setChildScene(menuFactory);
        redrawLevelsUI();
    }

    private void redrawLevelsUI() {
        menuFactory.clearChildScene();
        MenuFactory qq = MenuFactory.createMenu(engine, shipActivity.getCamera());
        Iterator<LevelInfo> iter = levels.getIterator();
        while(iter.hasNext()){
            final LevelInfo item = iter.next();
            String name;
            if(item.getLevelId() != LEVEL_INFINITY){
                if (item.isNew()){
                    name = "N";
                }else {
                    if (item.isWin()){
                        name = "X";
                    } else {
                        name = "O";
                    }
                }
            }else{
                name = "Inf";
            }
            qq.addedText(name, TextureLoader.getFontBig(), new ISimpleClick() {
                @Override
                public void onClick(int id) {
                    createGameScene(item.getLevelId());
                    lastSceneId = id;
                }
            }, item.getX(), item.getY());
        }
        qq.enableAnimation().build();

        shipActivity.getSharedPreferences(FILE_LEVELS, Context.MODE_PRIVATE)
                .edit().putString(PREF_LEVELS,levels.toJson())
                .commit();

        menuFactory.setChildScene(qq.build());
    }


    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
        if (gameScene != null){
            gameScene.onKeyPressed(keyCode, event);
        }else{
            if(keyCode == KeyEvent.KEYCODE_BACK ){
                parentScene.returnToParentScene(- 1);
            }
        }
    }

    private void createGameScene(int type) {
        if (gameScene != null){
            deactive();
        }
        switch (type){
            case LEVEL_INFINITY:
                gameScene = new InfinityGameScene(this);
                break;
            case LEVEL_1:
                gameScene = new FirstGameScene(this);
                break;

        }
        activeLevel = type;
        setChildScene(gameScene,false,true,true);
    }

    @Override
    public void returnToParentScene(int statusCode) {
        deactive();
        processResault(statusCode);
        redrawLevelsUI();
        setChildScene(menuFactory, false, true, true);
    }

    private void processResault(int statusCode) {
       switch (statusCode){
           case IParentScene.EXIT_USER:
               break;
           case IParentScene.EXIT_DIE:
               levels.changeStateById(lastSceneId,false);
              break;
           case IParentScene.EXIT_WIN:
               levels.changeStateById(lastSceneId,true);
               break;
       }
    }

    private void deactive(){
        detachChild(gameScene);
        gameScene.clearTouchAreas();
        gameScene.clearEntityModifiers();
        gameScene.clearUpdateHandlers();
        gameScene.back();
        gameScene = null;
    }

    @Override
    public void restart() {
        createGameScene(activeLevel);
    }
}
