package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.BulletAI.AutoguiderRocketAI;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;
import org.andengine.util.adt.pool.GenericPool;

public class Rocket extends BaseBullet {

    private static boolean isRegistredPool = false;

    private Rocket() {
        initCharacteristics(1200, 200, 5f);

        sprite = new AutoguiderRocketAI(TextureLoader.getRocketAmmoTextureRegion(),
                SpaceShipActivity.getInstance().getEngine().getVertexBufferObjectManager()){
            @Override
            protected void destroyed() {
                destroy();
            }

            @Override
            protected void doAfterUpdate() {
                checkHit();
            }
        };
        settings();
    }

    @Override
    public void init(float x, float y, float direction, int bullitType, boolean unitTarget, IWeaponModificator weaponModificator) {
        if (bullitType == BaseBullet.TYPE_ROCKET_AUTO){
            ((AutoguiderRocketAI)sprite).setAutoguider();
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

}

