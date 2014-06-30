package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.Hero;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
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

        qwe();
    }

    private void qwe() {
        final PhysicsHandler physicsHandler = new PhysicsHandler(hero.getSprite());
        hero.getSprite().registerUpdateHandler(physicsHandler);
        final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(0,
                SpaceShipActivity.getCAMERA_HEIGHT() - TextureLoader.getScreenControlBaseTextureRegion().getHeight(),
                shipActivity.getCamera(), TextureLoader.getScreenControlBaseTextureRegion(),
                TextureLoader.getScreenControlKnobTextureRegion(), 0.1f, 200,
                shipActivity.getEngine().getVertexBufferObjectManager(),
                new AnalogOnScreenControl.IAnalogOnScreenControlListener() {
                    @Override
                    public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX,
                            final float pValueY) {
                        physicsHandler.setVelocity(pValueX * 100, pValueY * 100);
                    }

                    @Override
                    public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
                        hero.getSprite().registerEntityModifier(new SequenceEntityModifier(new ScaleModifier(0.25f, 1, 1.5f),
                                new ScaleModifier(0.25f, 1.5f, 1)));
                    }
                });
        analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        analogOnScreenControl.getControlBase().setAlpha(0.5f);
        analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
        analogOnScreenControl.getControlBase().setScale(1.25f);
        analogOnScreenControl.getControlKnob().setScale(1.25f);
        analogOnScreenControl.refreshControlKnobPosition();

        setChildScene(analogOnScreenControl);
    }


}
