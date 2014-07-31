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
    private final SpaceShipActivity shipActivity;
    private final Engine engine;
    private final IParentScene parentScene;
    private final SelectionLevelScene self;
    private MyScene infinityGameScene;
    private MenuScene menuFactory;

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
        menuFactory = MenuFactory.createMenu()
                .addedItem(TextureLoader.getChangeLevelLableTextureRegion1(),1,Constants.CAMERA_WIDTH_HALF,0,WALIGMENT.CENTER, HALIGMENT.TOP )
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(), new ISimpleClick() {
                    @Override
                    public void onClick() {
                        parentScene.returnToParentScene();
                    }
                }, 1, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT, WALIGMENT.RIGHT, HALIGMENT.BOTTOM)
                .enableAnimation().build();
        MenuScene game = MenuFactory.createMenu()
                .addedText("X", TextureLoader.getFont(), new ISimpleClick() {
                    @Override
                    public void onClick() {
                        createGameScene();
                    }
                }, 3, 100, 100)
                .enableAnimation().build();
        menuFactory.setChildScene(game);
        setChildScene(menuFactory);
    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
        parentScene.returnToParentScene();
    }

    private void createGameScene() {
        if (infinityGameScene == null){
            infinityGameScene = new InfinityGameScene(this);
        }
        setChildScene(infinityGameScene,false,true,true);
    }

    @Override
    public void returnToParentScene() {
        detachChild(infinityGameScene);
        infinityGameScene.clearTouchAreas();
        infinityGameScene.clearEntityModifiers();
        infinityGameScene.clearUpdateHandlers();
        infinityGameScene.back();
        infinityGameScene = null;
        // text.setVisible(true);
        setChildScene(menuFactory, false, true, true);
    }

    @Override
    public void restart() {
        createGameScene();
    }
}
