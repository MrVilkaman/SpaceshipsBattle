package donnu.zolotarev.SpaceShip.Scenes;

import android.graphics.Point;
import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IAmDie;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.GameState.IStatusGameInfo;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Waves.IWaveController;
import donnu.zolotarev.SpaceShip.Waves.SimpleWave;

import java.util.Random;

public class MuseumScene extends BaseGameScene implements IAmDie {

    public MuseumScene(IParentScene self) {
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

    int x = 0;
    int y = 0;

    @Override
    public void addEnemy(AddedEnemyParam param) {

        BaseUnit enemy1 = BaseUnit.getEnemy(Constants.MAX_UNIT_LEVEL_WITH_SHIELD * (param.getKind()/Constants.MAX_UNIT_LEVEL_WITH_SHIELD));
        Random random = new Random();
        Point point;
        y++;
        if(param.getKind() == BaseUnit.TYPE_ENEMY_ROCKET_L_1 || param.getKind() == BaseUnit.TYPE_ENEMY_SINGLE_GUN_L_1
                || param.getKind() == BaseUnit.TYPE_ENEMY_MINIGUN_L_1 || param.getKind() == BaseUnit.TYPE_ENEMY_METEOR_L_1){
            y = 0;
            x++;
        }
        point = new Point(250*x, 100 + 200*y);


        PointF pointF =  activeScene.getHero().getPosition();
        enemy1.init(param.getKind()% Constants.MAX_UNIT_LEVEL_WITH_SHIELD, point,0, new WaySpecifications(0,0f));

    }

    @Override
    public void destroyed(BaseUnit unit) {

    }


    //  dataProcessor.processGold(levels.newestById(lastSceneId),true);
}
