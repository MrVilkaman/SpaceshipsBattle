package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Enemy1;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Units.UnitSpecifications;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;

import java.util.Random;

public class TestGameScene extends BaseGameScene implements IAmDie {

    public TestGameScene(IParentScene self) {
        super(self);
    }

    @Override
    public void addNewWaveController(IWaveController controller) {
        ((SimpleWave)controller).addListener(new IStatusGameInfo() {
            @Override
            public void onNextWave(int count) {
                waveIndex++;
                textWaveBarCallback.onNextWave(waveIndex);
            }

            @Override
            public void onWinLevel() {
                toast("Победа!");
//                returnToParentScene(IParentScene.EXIT_WIN);
            }
        });
        super.addNewWaveController(controller);
    }

    @Override
    protected void initWave() {
    }

    @Override
    protected void initHero() {
        hero = new Hero(textHealthBarCallback);
        hero.init(new Point(0, 250));
    }

    @Override
    protected void initBulletsPools() {
        SimpleBullet.initPool();
        BaseBullet.setUnitDieListener(this);
    }

    @Override
    protected void initUnitsPools() {
        Enemy1.initPool();
    }

    @Override
    protected void beforeReturnToParent(int status) {
        UserDataProcessor dataProcessor = UserDataProcessor.get();
        if (status == IParentScene.EXIT_WIN || status == IParentScene.EXIT_DIE){
            score =  dataProcessor.processGold(score,status == IParentScene.EXIT_WIN);
        }
    }

    int i = 0;
    @Override
    public void addEnemy(int kind) {

        BaseUnit enemy1 = BaseUnit.getEnemy(kind);
        Random random = new Random();
        Point point = new Point(600+ 200*i, 300);

        PointF pointF =  activeScene.getHero().getPosition();
        enemy1.init(point, 0+90*i, new UnitSpecifications(2000,0,5f));
        i++;
    }

    @Override
    public void destroyed(Class o) {
        addToScore(10+o.hashCode()%10);
    }


    //  dataProcessor.processGold(levels.newestById(lastSceneId),true);
}
