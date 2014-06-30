package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.Hero;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;

public class MainScene extends Scene {
    private final Hero hero;

    private SpaceShipActivity shipActivity;

    public MainScene() {
        //super();
        shipActivity = SpaceShipActivity.getInstance();
        setBackground(new Background(0.9f, 0.9f, 0.9f));
        hero = new Hero(shipActivity.getEngine());
        hero.attachToScene(this);

        addHeroMoveControl();
    }

    private void addHeroMoveControl() {
        final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(30,
                SpaceShipActivity.getCAMERA_HEIGHT() - TextureLoader.getScreenControlBaseTextureRegion().getHeight() - 30,
                shipActivity.getCamera(), TextureLoader.getScreenControlBaseTextureRegion(),
                TextureLoader.getScreenControlKnobTextureRegion(), 0.1f, 200,
                shipActivity.getEngine().getVertexBufferObjectManager(),
                hero.getCallback());
        analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        analogOnScreenControl.getControlBase().setAlpha(0.5f);
        analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
        analogOnScreenControl.getControlBase().setScale(1.25f);
        analogOnScreenControl.getControlKnob().setScale(1.25f);
        analogOnScreenControl.refreshControlKnobPosition();

        setChildScene(analogOnScreenControl);
    }


}
