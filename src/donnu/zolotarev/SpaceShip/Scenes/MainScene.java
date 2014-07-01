package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import android.widget.Toast;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Hero;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

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
        SimpleBullet simpleBullet = new SimpleBullet(null,200,200);
        simpleBullet.attachToScene(this);
        simpleBullet.init(200, 200, 0);

        simpleBullet = new SimpleBullet(null,210,230);
        simpleBullet.init(300, 100, 90);
        simpleBullet.attachToScene(this);

        simpleBullet = new SimpleBullet(null,220,220);
        simpleBullet.init(200, 100, 45);
        simpleBullet.attachToScene(this);

        simpleBullet = new SimpleBullet(null,220,220);
        simpleBullet.init(200, 100, 30);
        simpleBullet.attachToScene(this);

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


        Rectangle btnFire = new Rectangle(SpaceShipActivity.getCAMERA_WIDTH()-130,SpaceShipActivity.getCAMERA_HEIGHT()-230,
                100,100,shipActivity.getEngine().getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(pSceneTouchEvent.isActionDown()){
                            Toast.makeText(shipActivity,"Огонь!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            }
        };

        Rectangle btnFire2 = new Rectangle(SpaceShipActivity.getCAMERA_WIDTH()-230,SpaceShipActivity.getCAMERA_HEIGHT()-130,
                100,100,shipActivity.getEngine().getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(pSceneTouchEvent.isActionDown()){
                            Toast.makeText(shipActivity,"БУ!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            }
        };

        btnFire.setColor(Color.BLACK);
        btnFire.setAlpha(0.5f);
        btnFire2.setColor(Color.BLACK);
        btnFire2.setAlpha(0.5f);
        attachChild(btnFire);
        attachChild(btnFire2);
        registerTouchArea(btnFire);
        registerTouchArea(btnFire2);
    }


}
