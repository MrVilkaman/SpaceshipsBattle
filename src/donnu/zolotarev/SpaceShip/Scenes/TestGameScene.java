package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;

import java.util.Random;

public class TestGameScene extends BaseGameScene implements IAmDie {

    private int lastRand = 0;

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
        hero.init(0, new Point(0, 250));
    }

    @Override
    protected void initBulletsPools() {
        BaseBullet.initPool();
        BaseBullet.setUnitDieListener(this);
    }

    @Override
    protected void initUnitsPools() {
        BaseUnit.initPool();
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
    public boolean addEnemy(AddedEnemyParam param) {

        if (super.addEnemy(param)){
            BaseUnit enemy1 = BaseUnit.getEnemy(Constants.MAX_UNIT_LEVEL_WITH_SHIELD * (param.getKind()/Constants.MAX_UNIT_LEVEL_WITH_SHIELD));
            Random random = new Random();
            Point point = param.getStartPosition();
            if (point == null){
                int rand = random.nextInt(60);
                if (Utils.equals(lastRand, rand, 10)){
                    rand = 35 + (int)Utils.random(-10,15);
                }
                lastRand = rand;
                point = new Point(1300, lastRand * 10);
            }
            // todo
            enemy1.init(param.getKind()% Constants.MAX_UNIT_LEVEL_WITH_SHIELD, point, param.getStartAngle(),new WaySpecifications(180,0)/*Utils.getAngle(point.x, point.y, pointF.x, pointF.y)*/);
            i++;
        }
        return true;
    }


    @Override
    public void destroyed(BaseUnit unit) {

    }
}
