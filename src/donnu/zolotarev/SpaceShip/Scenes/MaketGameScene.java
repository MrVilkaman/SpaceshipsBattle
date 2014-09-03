package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Levels.LevelInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;

import java.util.Random;

public class MaketGameScene extends BaseGameScene implements IAmDie {

    private LevelInfo levelInfo;

    public MaketGameScene(IParentScene self) {
        super(self);
    }

    public MaketGameScene(SelectionLevelScene selectionLevelScene, LevelInfo level) {
       super(selectionLevelScene);
       this.levelInfo = level;

    }

    @Override
    public void addNewWaveController(IWaveController controller) {
        ((SimpleWave)controller).addListener(new IStatusGameInfo() {
            @Override
            public void onNextWave(int count) {
               /* waveIndex++;
                textWaveBarCallback.onNextWave(waveIndex);*/
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
        hero.init(0, new Point(600, 300));
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
    public void addEnemy(AddedEnemyParam param) {
        BaseUnit enemy1 = BaseUnit.getEnemy(Constants.MAX_UNIT_LEVEL* (param.getKind()/Constants.MAX_UNIT_LEVEL));
        Random random = new Random();

        Point point = param.getStartPosition();
        PointF pointF =  activeScene.getHero().getPosition();
        if (point == null){
            point = new Point(1300, random.nextInt(65) * 10);
        }
        // todo
        enemy1.init(param.getKind()% Constants.MAX_UNIT_LEVEL, point, param.getStartAngle()/*Utils.getAngle(point.x, point.y, pointF.x, pointF.y)*/);
    }

    @Override
    protected void beforeReturnToParent(int status) {
        UserDataProcessor dataProcessor = UserDataProcessor.get();
        if (status == IParentScene.EXIT_WIN || status == IParentScene.EXIT_DIE){
            boolean flag = status == IParentScene.EXIT_WIN;
            if (levelInfo != null){
                flag  = flag && !levelInfo.isWin();
            }
            score =  dataProcessor.processGold(score,flag);
        }
    }

    @Override
    public void destroyed(BaseUnit o) {
        waveIndex++;
        textWaveBarCallback.onNextWave(waveIndex);
        addToScore(o.getMoney() + (int) Utils.random(0,5));
    }
}
