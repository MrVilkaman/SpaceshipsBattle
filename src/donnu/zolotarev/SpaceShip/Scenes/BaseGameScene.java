package donnu.zolotarev.SpaceShip.Scenes;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.GameState.IWaveBar;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.UI.IHealthBar;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Utils.ObjectController;
import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSCounter;
import org.andengine.util.HorizontalAlign;

public abstract class BaseGameScene extends Scene {

    protected static BaseGameScene activeScene;
    protected static Engine engine;
    protected final SpaceShipActivity shipActivity;
    private final ObjectController enemyController;
    private final ObjectController bulletController;

    protected Hero hero;

    private Text waveCountBar;
    private Text scoreBar;
    private Text healthBar;

    private int score;

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

    public BaseGameScene() {
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

}
