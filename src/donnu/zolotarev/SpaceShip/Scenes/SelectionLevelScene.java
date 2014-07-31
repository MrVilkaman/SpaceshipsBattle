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

public class SelectionLevelScene extends MyScene {
    private final SpaceShipActivity shipActivity;
    private final Engine engine;
    private final IParentScene parentScene;

    public SelectionLevelScene(IParentScene parentScene) {
        super(parentScene);
        this.parentScene = parentScene;
        shipActivity = SpaceShipActivity.getInstance();
       engine = shipActivity.getEngine();
        setBackground(new Background(Color.WHITE));
       initUI();
    }

    private void initUI() {
        int x = Constants.CAMERA_WIDTH_HALF;
        int y = 0;

        MenuScene menuFactory = MenuFactory.createMenu()
                .addedItem(TextureLoader.getChangeLevelLableTextureRegion1(),1,Constants.CAMERA_WIDTH_HALF,0,MenuFactory.WALIGMENT.CENTER, MenuFactory.HALIGMENT.TOP )
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(),new ISimpleClick() {
                    @Override
                    public void onClick() {
                        parentScene.returnToParentScene();
                    }
                },1,Constants.CAMERA_WIDTH,Constants.CAMERA_HEIGHT, MenuFactory.WALIGMENT.RIGHT, MenuFactory.HALIGMENT.BOTTOM)

                .enableAnimation().build();

        setChildScene(menuFactory);

       /* Sprite sprite = new Sprite(0,0, TextureLoader.getChangeLevelLableTextureRegion1()
                ,engine.getVertexBufferObjectManager());
        sprite.setPosition(x - sprite.getWidth()/2,y);
        attachChild(sprite);*/


    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
        parentScene.returnToParentScene();
    }
}
