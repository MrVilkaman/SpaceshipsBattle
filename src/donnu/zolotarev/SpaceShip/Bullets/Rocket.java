package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.BulletAI.AutoguiderRocketAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;
import org.andengine.util.adt.pool.GenericPool;

import java.util.ArrayList;

public class Rocket extends BaseBullet {

    public static final int NAME_HASH = Rocket.class.getSimpleName().hashCode();

    private static boolean isRegistredPool = false;
    private final int splushRadiusMax;
    private final int splushRadiusMin;
    private final int splushRadius;

    private Rocket() {
        super(NAME_HASH);
        sprite = new AutoguiderRocketAI(TextureLoader.getRocketAmmoTextureRegion(),
                GameActivity.getInstance().getEngine().getVertexBufferObjectManager()){
            @Override
            protected void destroyed() {
                destroy(true);
            }

            @Override
            protected void doAfterUpdate() {
                if(targetUnit){
                    checkSplashHitUnit();
                }else{
                    checkHitHero();
                }
            }
        };
        settings();
        splushRadiusMin = 40;
        splushRadiusMax = 250;
        splushRadius = splushRadiusMax - splushRadiusMin;
    }

    @Override
    public void init(float x, float y, float direction, int bullitType, boolean unitTarget,
            IWeaponModificator[] weaponModificator, int bulletFrameNumber) {
        if (unitTarget){
            initCharacteristics(1600, 700, 10f);
        }else{
            initCharacteristics(1600, 450, 6f);
        }

        if (weaponModificator != null){
            for (IWeaponModificator modificator: weaponModificator){
                if(modificator.getTarget() == IWeaponModificator.Target.ROTATE_ANGLE){
                    DEFAULT_ROTATE_ANGLE = modificator.use(DEFAULT_ROTATE_ANGLE);
                }
            }
        }

        if (bullitType == BaseBullet.TYPE_ROCKET_AUTO){
            ((AutoguiderRocketAI)sprite).setAutoguider(unitTarget);
        }else{
            ((AutoguiderRocketAI)sprite).setNoAutoguider();
        }
        super.init(x, y, direction, bullitType, unitTarget, weaponModificator, bulletFrameNumber);
    }

    public static void poolInit() {
        isRegistredPool = true;
        registredPool(Rocket.class,new GenericPool() {
                @Override
                protected Rocket onAllocatePoolItem() {
                    return new Rocket();
                }
            });
    }


    private void checkSplashHitUnit() {
        /*Iterator items = enemyController.getObjects();
        items.
        (ICollisionObject)*/
        /*Iterator<BaseUnit> col = enemyController.haveCollision(this);
        while (col.hasNext()){*/
        ArrayList<BaseUnit> objects = enemyController.haveCollision(this);
        for (int i = objects.size()-1; 0<=i;i--){
            BaseUnit unit = objects.get(i);

          /*  unit.getPosition();*/
           /* Iterator<BaseUnit> items = enemyController.getObjects();
            while (items.hasNext()) {*/
            ArrayList<BaseUnit> items = enemyController.get();
            for (int j = items.size()-1; 0<=j;j--){
                BaseUnit unit2 = items.get(j);
                float dist =  Utils.distance(unit.getPosition(), unit2.getPosition());
                // 200 - 250;
                float splushDist = splushRadiusMax - dist;
                splushDist = splushDist < 0? 0:splushDist;

                splushDist = ( splushDist < splushRadius )? splushDist : splushRadius;

               // splushRadiusMin - splushDist
                float damage = getDamage() *(splushDist/splushRadius);

                if (damage != 0){
                    if (unit.addDamageAndCheckDeath((int)damage)){
                        items.remove(unit2);
                        unit2.destroy(true);
                        if (iAmDie != null){
                            iAmDie.destroyed(unit2);
                        }
                    }
                }
            }

            destroy(true);
        }

    }
}

