package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import android.opengl.GLES20;
import android.widget.Toast;
import donnu.zolotarev.SpaceShip.Bullets.BulletBase;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Enemy1;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.ObjectController;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import java.util.Iterator;
import java.util.Random;

public class MainScene extends Scene {
    private final Hero hero;

    private static MainScene acitveScene;
    private static Engine engine;

    private final ObjectController enemyController;
    private final ObjectController bulletController;

    private SpaceShipActivity shipActivity;

    public  ObjectController  getBulletController() {
        return bulletController;
    }

    public MainScene() {
        //super();

      //  BulletBase.initPool();
        /////////
        acitveScene = this;
        engine = getEngine();
        shipActivity = SpaceShipActivity.getInstance();
        setBackground(new Background(0.9f, 0.9f, 0.9f));
        hero = new Hero(shipActivity.getEngine());
        hero.setStartPosition(new Point(0,250));
        addHeroMoveControl();

        enemyController = new ObjectController<BaseUnit>();

        Enemy1 enemy1;
        for (int i=0; i<10 ; ++i) {
            Random random = new Random();
            enemy1 = new Enemy1();
            enemy1.setStartPosition(new Point(1200 +i*300+ random.nextInt(5)*10, 250+ random.nextInt(5)*10));
            enemyController.add(enemy1);

            enemy1 = new Enemy1();
            enemy1.setStartPosition(new Point(1100+i*340+ random.nextInt(5)*10, 300+ random.nextInt(5)*10));
            enemyController.add(enemy1);

            enemy1 = new Enemy1();
            enemy1.setStartPosition(new Point(1200+i*290+ random.nextInt(5)*10, 100+ random.nextInt(5)*10));
            enemyController.add(enemy1);

            enemy1 = new Enemy1();
            enemy1.setStartPosition(new Point(1300+i*300+ random.nextInt(5)*10, 270+ random.nextInt(5)*10));
            enemyController.add(enemy1);
        }

        bulletController = new ObjectController<BulletBase>();

        registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {

            try {
                Iterator<BulletBase> it = bulletController.getObjects();
                while (it.hasNext()){
                    BulletBase shape = it.next();
                    Iterator<BaseUnit>  col = enemyController.haveCollision(shape);

                    while (col.hasNext()){
                        BaseUnit unit = col.next();
                        if (unit.addDamageAndCheckDeath(shape.getDamage())){
                            unit.destroy();
                            col.remove();
                        }
                        shape.deleteBullet();
                        it.remove();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            }

            @Override
            public void reset() {

            }
        });
    }

    private void addHeroMoveControl() {
        final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(30,
                SpaceShipActivity.getCameraHeight() - TextureLoader.getScreenControlBaseTextureRegion().getHeight() - 30,
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


        Rectangle btnFire = new Rectangle(SpaceShipActivity.getCameraWidth()-130,SpaceShipActivity.getCameraHeight()-230,
                100,100,shipActivity.getEngine().getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(pSceneTouchEvent.isActionDown()){
                            hero.fire();
                        }
                    }
                });
                return true;
            }
        };

        Rectangle btnFire2 = new Rectangle(SpaceShipActivity.getCameraWidth()-230,SpaceShipActivity.getCameraHeight()-130,
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

    public static MainScene getAcitveScene() {
        return acitveScene;
    }

    public static Engine getEngine() {
        return engine;
    }

    public ObjectController getEnemyController() {
        return enemyController;
    }

}
