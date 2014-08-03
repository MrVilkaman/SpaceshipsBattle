package donnu.zolotarev.SpaceShip.Scenes;

import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.util.color.Color;

public class SelectionLevelScene extends MyScene implements IParentScene {
    private static final int LEVEL_INFINITY = 0;
    private static final int LEVEL_1 = 1;

    private final SpaceShipActivity shipActivity;
    private final Engine engine;
    private final IParentScene parentScene;
    private final SelectionLevelScene self;
    private BaseGameScene  gameScene;
    private MenuScene menuFactory;
    private int activeLevel;

    public SelectionLevelScene(IParentScene parentScene) {
        super(parentScene);
        self = this;
        this.parentScene = parentScene;
        shipActivity = SpaceShipActivity.getInstance();
       engine = shipActivity.getEngine();
        setBackground(new Background(Color.WHITE));
       initUI();
    }

    private void initUI() {

        int x = Constants.CAMERA_WIDTH_HALF;
        int y = 0;
        menuFactory = MenuFactory.createMenu(engine,shipActivity.getCamera())
                .addedItem(TextureLoader.getChangeLevelLableTextureRegion1(), Constants.CAMERA_WIDTH_HALF,0,WALIGMENT.CENTER, HALIGMENT.TOP )
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(), new ISimpleClick() {
                    @Override
                    public void onClick() {
                        parentScene.returnToParentScene();
                    }
                }, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT, WALIGMENT.RIGHT, HALIGMENT.BOTTOM)
                .enableAnimation().build();
        MenuScene game = MenuFactory.createMenu(engine,shipActivity.getCamera())
                .addedText("Inf", TextureLoader.getFontBig(), new ISimpleClick() {
                    @Override
                    public void onClick() {
                        createGameScene(LEVEL_INFINITY);
                    }
                }, 100, 100)
                .addedText("X", TextureLoader.getFontBig(), new ISimpleClick() {
                    @Override
                    public void onClick() {
                        createGameScene(LEVEL_1);
                    }
                }, 200, 300)
                .enableAnimation().build();
        menuFactory.setChildScene(game);
        setChildScene(menuFactory);
    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
        if (gameScene != null){
            gameScene.onKeyPressed(keyCode, event);
        }else{
            if(keyCode == KeyEvent.KEYCODE_BACK ){
                parentScene.returnToParentScene();
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
    public void returnToParentScene() {
        deactive();
        // text.setVisible(true);
        setChildScene(menuFactory, false, true, true);
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
