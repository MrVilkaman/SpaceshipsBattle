package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import android.opengl.GLES20;
import android.view.KeyEvent;
import android.widget.Toast;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet2;
import donnu.zolotarev.SpaceShip.GameState.IHeroDieListener;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IWaveBar;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.UI.IScoreBar;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Enemy1;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Waves.IAddedEnemy;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.InfinityWave;
import donnu.zolotarev.SpaceShip.Waves.UnitWave;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import java.util.Random;

public class MainScene extends BaseGameScene implements IAddedEnemy, IScoreBar {

    private IWaveController waveController;
    private final IParentScene parrentScene;

    private AnalogOnScreenControl analogOnScreenControl;
    private boolean isShowMenuScene = false;

    private int waveIndex = 0;
    private boolean isActive = true;

    private MenuScene menuScene;
    private MenuScene dieMenuScene;
    private boolean enablePauseMenu = true;

    public MainScene(IParentScene self) {
        super();
        parrentScene = self;
        initWave();

        Enemy1.initPool();

        hero = new Hero(textHealthBarCallback);
        hero.init(new Point(0, 250));
        addHeroMoveControl();

        SimpleBullet.initPool();
        SimpleBullet2.initPool();
        BaseBullet.setDieListener(new IHeroDieListener() {
            @Override
            public void heroDie() {
                shipActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isActive = false;
                        enablePauseMenu = false;
                        setChildScene(dieMenuScene, false, true, true);
                    }
                });
            }
        });

        createMenu();
    }

    private void createMenu() {

        ISimpleClick restart = new ISimpleClick() {
            @Override
            public void onClick() {
                returnToParentScene();
                parrentScene.restart();
            }
        };

        ISimpleClick exit = new ISimpleClick() {
            @Override
            public void onClick() {
                returnToParentScene();
            }
        };

        menuScene = MenuFactory.createMenu()
                .addedItem(TextureLoader.getMenuResumeTextureRegion(), new ISimpleClick() {
                    @Override
                    public void onClick() {
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

        dieMenuScene = MenuFactory.createMenu()
                .addedText(shipActivity.getString(R.string.lose_text),TextureLoader.getFont(), 2,
                        Constants.CAMERA_WIDTH_HALF,100)
                .addedItem(TextureLoader.getMenuRestartTextureRegion(), restart)
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(), exit)
                .enableAnimation()
                .build();
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        if (isActive){
            waveController.updateWave(pSecondsElapsed);
        }
        super.onManagedUpdate(pSecondsElapsed);
    }

    private void initWave() {
        waveController = new InfinityWave(new IWaveBar() {
            @Override
            public void onNextWave(int count) {
                waveIndex++;
                textWaveBarCallback.onNextWave(waveIndex);
            }
        });

        UnitWave unitWave = new UnitWave(this);
        unitWave.addEnemy(0, 7, 1);
        unitWave.addEnemy(0, 3, 0.1f);
        unitWave.addEnemy(0,7,0.7f);
        waveController.addWave(unitWave);

        unitWave = new UnitWave(this);
        unitWave.addEnemy(0, 8, 0.3f);
        unitWave.addDelay(2);
        unitWave.addEnemy(0, 10, 0.5f);
        unitWave.addDelay(3);
        unitWave.addEnemy(0,20,0.8f);
        waveController.addWave(unitWave);

        unitWave = new UnitWave(this);
        unitWave.addEnemy(0, 10, 0.3f);
        unitWave.addEnemy(0, 20, 0.4f);
        unitWave.addEnemy(0, 20,0.8f);
        waveController.addWave(unitWave);
    }

    public void addEnemy(int kind){
        BaseUnit enemy1 = BaseUnit.getEnemy(kind);
        Random random = new Random();
        enemy1.init(new Point(1300, random.nextInt(65) * 10));
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

    private void returnToParentScene(){
        activeScene.clearUpdateHandlers();
        activeScene.clearChildScene();
        activeScene.clearEntityModifiers();
        activeScene.clearTouchAreas();
        getBulletController().cleer();
        getEnemyController().cleer();
        detachSelf();
        parrentScene.returnToParentScene();
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
}
