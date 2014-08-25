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
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;

import java.util.Random;

public class MaketGameScene extends BaseGameScene implements IAmDie {

    public MaketGameScene(IParentScene self) {
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
                returnToParentScene(IParentScene.EXIT_WIN);
                toast("Победа! И ты заработал " + score + "$!");
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
    public void addEnemy(int kind) {
        BaseUnit enemy1 = BaseUnit.getEnemy(kind);
        Random random = new Random();

        Point point = new Point(1300, random.nextInt(65) * 10);
        PointF pointF =  activeScene.getHero().getPosition();
        // todo
        enemy1.init(point, Utils.getAngle(point.x, point.y, pointF.x, pointF.y));
    }

    @Override
    protected void beforeReturnToParent(int status) {
        UserDataProcessor dataProcessor = UserDataProcessor.get();
        if (status == IParentScene.EXIT_WIN || status == IParentScene.EXIT_DIE){
            score =  dataProcessor.processGold(score,status == IParentScene.EXIT_WIN);
        }
    }

    @Override
    public void destroyed(Class o) {
        addToScore(10+o.hashCode()%10);
    }
}
