package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import android.view.KeyEvent;
import android.widget.Toast;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.GameState.IHeroDieListener;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IWaveBar;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.UI.IHealthBar;
import donnu.zolotarev.SpaceShip.UI.IScoreBar;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Utils.*;
import donnu.zolotarev.SpaceShip.Waves.IAddedEnemy;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSCounter;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

public abstract class BaseGameScene extends MyScene implements IAddedEnemy, IScoreBar {

    protected static BaseGameScene activeScene;
    protected static Engine engine;
    protected final SpaceShipActivity shipActivity;
    protected IWaveController waveController;
    private final ObjectController enemyController;
    private final ObjectController bulletController;
    private final IParentScene parentScene;
    private AnalogOnScreenControl analogOnScreenControl;

    protected Hero hero;
    private Text waveCountBar;
    private Text scoreBar;
    private Text healthBar;

    private MenuScene menuScene;
    private MenuScene dieMenuScene;
    private boolean isShowMenuScene = false;
    private boolean enablePauseMenu = true;
    private boolean isActive = false;

    private int score;
    protected int waveIndex = 0;

    protected IHealthBar textHealthBarCallback = new IHealthBar() {
        @Override
        public void updateHealthBar(int health) {
            healthBar.setText(String.valueOf(health));
        }
    };

    protected IWaveBar textWaveBarCallback = new IWaveBar() {
        @Override
        public void onNextWave(int count) {
            waveCountBar.setText(String.format("%02d", count));
        }
    };
    private int status;


    public BaseGameScene(IParentScene self) {
        super(self);
        parentScene = self;
        activeScene = this;
        shipActivity = SpaceShipActivity.getInstance();
        engine = shipActivity.getEngine();
        setBackground(new Background(0.9f, 0.9f, 0.9f));
        enemyController = new ObjectController<BaseUnit>();
        bulletController = new ObjectController<BaseBullet>();

        createFPSBase();
        createHealthBar();
        createScoreBar();
        createWaveCountBar();
        initUnitsPools();
        initHero();
        initBulletsPools();
        addHeroMoveControl();

        BaseBullet.setDieListener(new IHeroDieListener() {
            @Override
            public void heroDie() {
                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isActive = false;
                        enablePauseMenu = false;
                        setChildScene(dieMenuScene, false, true, true);
                        status = IParentScene.EXIT_DIE;
                    }
                });
            }
        });
        initWave();
        createMenu();
        status = IParentScene.EXIT_USER;
    }


    protected abstract void initWave();

    protected abstract void initHero();

    protected abstract void initBulletsPools();

    protected abstract void initUnitsPools();

    private void createMenu() {

        ISimpleClick restart = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                clearItem();
                parentScene.restart();
            }
        };

        ISimpleClick exit = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                returnToParentScene(status);
            }
        };

        menuScene = MenuFactory.createMenu(engine, shipActivity.getCamera())
                .addedItem(TextureLoader.getMenuResumeTextureRegion(), new ISimpleClick() {
                    @Override
                    public void onClick(int id) {
                        activeScene.detachChild(menuScene);
                        activeScene.setChildScene(analogOnScreenControl);
                        isShowMenuScene = false;
                        isActive = true;
                    }
                })
                .addedItem(TextureLoader.getMenuRestartTextureRegion(), restart)
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(), exit)
                .enableAnimation()
                .build();

        dieMenuScene = MenuFactory.createMenu(engine,shipActivity.getCamera())
                .addedText(shipActivity.getString(R.string.lose_text),TextureLoader.getFont(),
                        Constants.CAMERA_WIDTH_HALF,100, WALIGMENT.CENTER, HALIGMENT.CENTER)
                .addedItem(TextureLoader.getMenuRestartTextureRegion(), restart)
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(), exit)
                .enableAnimation()
                .build();
    }

    protected void addHeroMoveControl() {
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
            boolean flag = false;
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(pSceneTouchEvent.isActionDown()){
                            hero.canFire(true);
                            flag = true;
                        } else if(pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionOutside() || pSceneTouchEvent.isActionCancel()){
                            hero.canFire(false);
                            flag = true;
                        }
                    }
                });

                return flag;
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
                            Toast.makeText(shipActivity, "БУ!!", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        if (isActive){
            waveController.updateWave(pSecondsElapsed);
        }
        super.onManagedUpdate(pSecondsElapsed);
    }

    public void onKeyPressed(int keyCode, KeyEvent event) {
        if (enablePauseMenu){
            if (!isShowMenuScene){
                isShowMenuScene = true;
                isActive = false;
                setChildScene(menuScene, false, true, true);
            }else{
                isActive = true;
                isShowMenuScene = false;
                activeScene.detachChild(menuScene);
                activeScene.setChildScene(analogOnScreenControl);
            }
        }
    }

    protected void returnToParentScene(int statusCode){
        clearItem();
        parentScene.returnToParentScene(statusCode);
    }

    protected void clearItem(){
        activeScene.clearUpdateHandlers();
        activeScene.clearChildScene();
        activeScene.clearEntityModifiers();
        activeScene.clearTouchAreas();
        hero.destroy();
        getEnemyController().cleer();
        getBulletController().cleer();
        detachSelf();
        BaseBullet.resetPool();
        BaseUnit.resetPool();
    }

    public void addToScore(int value){
        score += value;
        scoreBar.setText(String.format("%08d", score));
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

    private void createFPSBase() {
        final FPSCounter fpsCounter = new FPSCounter();
        engine.registerUpdateHandler(fpsCounter);
        final Text fpsText = new Text(0, 0, TextureLoader.getFont(), "FPS:", "FPS: 1234567890.".length(),engine.getVertexBufferObjectManager());
        attachChild(fpsText);
        registerUpdateHandler(new TimerHandler(1 / 20.0f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler) {

                fpsText.setText("FPS: " + String.valueOf(fpsCounter.getFPS()));
            }
        }));
    }

    private void createWaveCountBar() {
        try {
            waveCountBar = new Text(0,0, TextureLoader.getFont(),"00",new TextOptions(HorizontalAlign.RIGHT),engine.getVertexBufferObjectManager());
            int x = SpaceShipActivity.getCameraWidth() - (int)scoreBar.getWidth() - (int)scoreBar.getWidth() -20;
            waveCountBar.setPosition(x,0);
            attachChild(waveCountBar);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createScoreBar() {
        try {
            scoreBar = new Text(0,0,TextureLoader.getFont(),"000000000",new TextOptions(HorizontalAlign.RIGHT),engine.getVertexBufferObjectManager());
            int x = SpaceShipActivity.getCameraWidth() - (int)scoreBar.getWidth();
            scoreBar.setPosition(x,0);
            attachChild(scoreBar);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Hero getHero() {
        return hero;
    }

    public ObjectController getEnemyController() {
        return enemyController;
    }

    public ObjectController getBulletController() {
        return bulletController;
    }

    public static Engine getEngine() {
        return engine;
    }

    public static BaseGameScene getActiveScene() {
        return activeScene;
    }

    public void toast(final String msg){
       shipActivity.runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Toast.makeText(shipActivity,msg,Toast.LENGTH_SHORT).show();
           }
       });
    }

    public void start(){
        isActive = true;
    }

    public void addNewWaveController(IWaveController controller){
        waveController = controller;
        start();
    }
}
