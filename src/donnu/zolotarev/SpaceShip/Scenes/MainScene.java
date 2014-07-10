package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import android.opengl.GLES20;
import android.view.KeyEvent;
import android.widget.Toast;
import donnu.zolotarev.SpaceShip.Bullets.BulletBase;
import donnu.zolotarev.SpaceShip.IHealthBar;
import donnu.zolotarev.SpaceShip.IHeroDieListener;
import donnu.zolotarev.SpaceShip.ObjectController;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Enemy1;
import donnu.zolotarev.SpaceShip.Units.Hero;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import java.util.Random;

public class MainScene extends Scene {

    private static final int MENU_RESET = 0;
    private static final int MENU_QUIT = MENU_RESET + 1;
    private final Hero hero;

    private static MainScene acitveScene;
    private static Engine engine;

    private final ObjectController enemyController;
    private final ObjectController bulletController;

    private SpaceShipActivity shipActivity;
    private Text healthBar;
    private MenuScene menuScene;

    public  ObjectController  getBulletController() {
        return bulletController;
    }

    public MainScene() {
        //super();

      //  BulletBase.initPool();
        /////////
        acitveScene = this;
        shipActivity = SpaceShipActivity.getInstance();
        engine = shipActivity.getEngine();
        setBackground(new Background(0.9f, 0.9f, 0.9f));

        createHealthBar();

        hero = new Hero(new IHealthBar() {
            @Override
            public void updateHealthBar(int health) {
               healthBar.setText(String.valueOf(health));
            }
        });
        hero.setStartPosition(new Point(0,250));
        addHeroMoveControl();

        enemyController = new ObjectController<BaseUnit>();

        Enemy1 enemy1;
        /*enemy1 = new Enemy1();
        enemy1.setStartPosition(new Point(1200 , 250));
        enemyController.add(enemy1);
        enemy1 = new Enemy1();
        enemy1.setStartPosition(new Point(1200 , 400));
        enemyController.add(enemy1);*/
        for (int i=0; i<3 ; ++i) {
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

        BulletBase.setDieListener(new IHeroDieListener() {
            @Override
            public void heroDie() {
                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shipActivity,"ТЫ ПРОИГРАЛ!",Toast.LENGTH_SHORT).show();
                        acitveScene.setIgnoreUpdate(true);
                        bulletController.cleer();
                    }
                });
            }
        });

        createMenuScene();
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

    public Hero getHero() {
        return hero;
    }


    private void createHealthBar(){
        try {
            int y = SpaceShipActivity.getCameraHeight() - 32;
            int x = (int)TextureLoader.getScreenControlBaseTextureRegion().getWidth() + 30 +100;
            Text text = new Text(x,y,TextureLoader.getFont(),"Прочность: ",new TextOptions(HorizontalAlign.LEFT),engine.getVertexBufferObjectManager());
            attachChild(text);
            x += text.getWidth();
            healthBar = new Text(x,y,TextureLoader.getFont(),"1234567890/",new TextOptions(HorizontalAlign.LEFT),engine.getVertexBufferObjectManager());
            attachChild(healthBar);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                            hero.canFire(true);
                        } else if(pSceneTouchEvent.isActionUp()){
                            hero.canFire(false);
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

    protected void createMenuScene() {
        this.menuScene = new MenuScene(shipActivity.getCamera());

        final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_RESET, TextureLoader.getMenuResetTextureRegion(),
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.menuScene.addMenuItem(resetMenuItem);

        final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT, TextureLoader.getMenuQuitTextureRegion(),
                engine.getVertexBufferObjectManager());
        quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.menuScene.addMenuItem(quitMenuItem);

        this.menuScene.buildAnimations();

        this.menuScene.setBackgroundEnabled(false);

        this.menuScene.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
                    float pMenuItemLocalY) {
                switch (pMenuItem.getID()) {
                    case MENU_RESET:
                        /* Restart the animation. */
                        acitveScene.reset();

				/* Remove the menu and reset it. */
                        acitveScene.clearChildScene();
                        menuScene.reset();
                        break;
                    case MENU_QUIT:
                        //  activity.exit();
                        break;
                }
                return false;
            }
        });
    }

    public void onKeyPressed(int keyCode, KeyEvent event) {

            acitveScene.setChildScene(menuScene, false, true, true);

    }

}
