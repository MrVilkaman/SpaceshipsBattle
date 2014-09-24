package donnu.zolotarev.SpaceShip.Scenes;

import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

public class qwe extends Scene {

    public qwe() {

        setBackground(new Background(0.9f, 0.9f, 0.9f));
        attachChild(new Text(50,30, TextureLoader.getFont(),"1234567890/"
                ,new TextOptions(HorizontalAlign.LEFT), GameActivity.getInstance().getEngine().getVertexBufferObjectManager()));

    }
}
