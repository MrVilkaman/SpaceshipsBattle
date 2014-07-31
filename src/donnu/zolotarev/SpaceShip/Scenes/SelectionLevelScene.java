package donnu.zolotarev.SpaceShip.Scenes;

import android.view.KeyEvent;
import android.widget.Toast;
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

    public SelectionLevelScene() {
       shipActivity = SpaceShipActivity.getInstance();
       engine = shipActivity.getEngine();
        setBackground(new Background(Color.WHITE));
       initUI();

    }

    private void initUI() {
        int x = Constants.CAMERA_WIDTH_HALF;
        int y = 0;

        final Runnable  runnable =  new Runnable() {
            @Override
            public void run() {
                Toast.makeText(shipActivity,"!!!",Toast.LENGTH_SHORT).show();
            }
        };
        ISimpleClick iSimpleClick = new ISimpleClick() {
            @Override
            public void onClick() {
                shipActivity.runOnUiThread(runnable);
            }
        };

        MenuScene menuFactory = MenuFactory.createMenu()
                .addedText("Hello", TextureLoader.getFont())
                .addedText("Hello,Mather Fucker", TextureLoader.getFont(), 1, 99, 100)
                .addedText("Hello,Mather Fucker TOUCH",TextureLoader.getFont(), iSimpleClick, 1, 399, 100)
                .addedItem(TextureLoader.getShip(), iSimpleClick)
                .addedItem(TextureLoader.getShip(), iSimpleClick, 2, 1000, 500).enableAnimation().build();
        setChildScene(menuFactory, false, true, true);

       /* Sprite sprite = new Sprite(0,0, TextureLoader.getChangeLevelLableTextureRegion1()
                ,engine.getVertexBufferObjectManager());
        sprite.setPosition(x - sprite.getWidth()/2,y);
        attachChild(sprite);*/


    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {

    }
}
