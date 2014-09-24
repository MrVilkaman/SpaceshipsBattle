package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.BulletAI.AutoguiderRocketAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;
import org.andengine.util.adt.pool.GenericPool;

import java.util.Iterator;

public class Rocket extends BaseBullet {

    private static boolean isRegistredPool = false;
    private final int splushRadiusMax;
    private final int splushRadiusMin;
    private final int splushRadius;

    private Rocket() {
        sprite = new AutoguiderRocketAI(TextureLoader.getRocketAmmoTextureRegion(),
                GameActivity.getInstance().getEngine().getVertexBufferObjectManager()){
            @Override
            protected void destroyed() {
                destroy();
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
    public void init(float x, float y, float direction, int bullitType, boolean unitTarget, IWeaponModificator weaponModificator) {
        if (unitTarget){
            initCharacteristics(1200, 800, 8f);
        }else{
            initCharacteristics(1200, 500, 6f);
        }

        if (bullitType == BaseBullet.TYPE_ROCKET_AUTO){
            ((AutoguiderRocketAI)sprite).setAutoguider(unitTarget);
        }else{
            ((AutoguiderRocketAI)sprite).setNoAutoguider();
        }
        super.init(x, y, direction, bullitType, unitTarget, weaponModificator);
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
        Iterator<BaseUnit> col = enemyController.haveCollision(this);
        while (col.hasNext()){
            BaseUnit unit = col.next();

            unit.getPosition();
            Iterator<BaseUnit> items = enemyController.getObjects();
            while (items.hasNext()) {
                BaseUnit unit2 = items.next();
                float dist =  Utils.distance(unit.getPosition(), unit2.getPosition());
                // 200 - 250;
                float splushDist = splushRadiusMax - dist;
                splushDist = splushDist < 0? 0:splushDist;

                splushDist = ( splushDist < splushRadius )? splushDist : splushRadius;

               // splushRadiusMin - splushDist
                float damage = getDamage() *(splushDist/splushRadius);

                if (damage != 0){
                    if (unit.addDamageAndCheckDeath((int)damage)){
                        items.remove();
                        unit2.destroy();
                        if (iAmDie != null){
                            iAmDie.destroyed(unit2);
                        }
                    }
                }
            }

            destroy();
        }

    }
}

