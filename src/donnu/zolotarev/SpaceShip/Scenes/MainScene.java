package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import android.opengl.GLES20;
import android.view.KeyEvent;
import android.widget.Toast;
import donnu.zolotarev.SpaceShip.Bullets.BulletBase;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet2;
import donnu.zolotarev.SpaceShip.*;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Enemy1;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Waves.IAddedEnemy;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;
import donnu.zolotarev.SpaceShip.Waves.WaveController;
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

public class MainScene extends Scene implements IAddedEnemy {

    private static final int MENU_RESUME = 0;
    private static final int MENU_BACK_TO_MAIN = MENU_RESUME + 1;
    private final Hero hero;

    private static MainScene acitveScene;
    private static Engine engine;

    private final ObjectController enemyController;
    private final ObjectController bulletController;
    private final IParentScene parrentScene;

    private SpaceShipActivity shipActivity;
    private Text healthBar;
    private MenuScene menuScene;

    private AnalogOnScreenControl analogOnScreenControl;
    private boolean isShowMenuScene = false;
    private WaveController waveController;
    private boolean isVictory = false;

    public  ObjectController  getBulletController() {
        return bulletController;
    }

    public MainScene(IParentScene self) {
        //super();
        parrentScene = self;
      //  BulletBase.initPool();
        /////////
        acitveScene = this;
        shipActivity = SpaceShipActivity.getInstance();
        engine = shipActivity.getEngine();
        setBackground(new Background(0.9f, 0.9f, 0.9f));

        createHealthBar();

        enemyController = new ObjectController<BaseUnit>();

        initWave();

        bulletController = new ObjectController<BulletBase>();

        Enemy1.initPool();

        hero = new Hero(new IHealthBar() {
            @Override
            public void updateHealthBar(int health) {
                healthBar.setText(String.valueOf(health));
            }
        });
        hero.init(new Point(0, 250));
        addHeroMoveControl();

        SimpleBullet.initPool();
        SimpleBullet2.initPool();
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

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
       updateWave(pSecondsElapsed);
        super.onManagedUpdate(pSecondsElapsed);
    }


    UnitWave _currentWave = null;
    private void updateWave(float pSecondsElapsed) {
        if (!waveController.isEmpty()){
            if (_currentWave == null ){
                if ( BaseUnit.getEnemiesOnMap() < 3){
                    _currentWave  = waveController.getNextWave();
                    _currentWave.startWave();
//                    _game.changeWaveInfo(_waveIndex,_waves.length);
                }
            } else {
                _currentWave.update(pSecondsElapsed);

                if (_currentWave.isFinished()){
                    waveController.waveEnds();
                    _currentWave = null;
                }
            }
        } else {
            if (BaseUnit.getEnemiesOnMap() == 0 && !isVictory){
                isVictory = true;
                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shipActivity,"ТЫ выиграл!",Toast.LENGTH_SHORT).show();
                      //  acitveScene.setIgnoreUpdate(true);
                      //  bulletController.cleer();
                    }
                });
            }
        }
    }

    private void initWave() {
        waveController = new WaveController();

        UnitWave unitWave = new UnitWave(this);
        unitWave.addEnemy(0,10,1);
        unitWave.addDelay(5);
        unitWave.addEnemy(0,5,0.1f);
        unitWave.addDelay(5);
        unitWave.addEnemy(0,10,1);
        unitWave.addDelay(5);
        unitWave.addEnemy(0,5,0.2f);
        unitWave.addDelay(5);
        unitWave.addEnemy(0,15,0.4f);
        unitWave.addDelay(5);
        unitWave.addEnemy(0,40,0.9f);


        waveController.addWave(unitWave);

    }

    public void addEnemy(int kind){
        BaseUnit enemy1 = BaseUnit.getBullet(kind);
        Random random = new Random();
        enemy1.init(new Point(1300, random.nextInt(65) * 10));
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
        analogOnScreenControl = new AnalogOnScreenControl(30,
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
        analogOnScreenControl.attachChild(btnFire);
        analogOnScreenControl.attachChild(btnFire2);
        analogOnScreenControl.registerTouchArea(btnFire);
        analogOnScreenControl.registerTouchArea(btnFire2);
    }

    protected void createMenuScene() {
        this.menuScene = new MenuScene(shipActivity.getCamera());

        final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_RESUME, TextureLoader.getMenuResumeTextureRegion(),
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.menuScene.addMenuItem(resetMenuItem);

        final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_BACK_TO_MAIN, TextureLoader.getMenuBackToMainMenuTextureRegion(),
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
                    case MENU_RESUME:
                        /* Restart the animation. */
                       //acitveScene.reset();
                        acitveScene.detachChild(menuScene);
                        acitveScene.setChildScene(analogOnScreenControl);
                        isShowMenuScene = false;
                        //menuScene = null;
                        break;
                    case MENU_BACK_TO_MAIN:
                        returnToParentScene();
                        break;
                }
                return true;
            }
        });
    }

    private void returnToParentScene(){
        enemyController.cleer();
        bulletController.cleer();
        parrentScene.returnToParentScene();
    }

    public void onKeyPressed(int keyCode, KeyEvent event) {
        if (!isShowMenuScene){
            isShowMenuScene = true;
            setChildScene(menuScene, false, true, true);
        }else{
            isShowMenuScene = false;
            acitveScene.detachChild(menuScene);
            acitveScene.setChildScene(analogOnScreenControl);
        }
    }

}
