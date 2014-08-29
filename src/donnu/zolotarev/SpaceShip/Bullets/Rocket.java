package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.BulletAI.SimpleBulletAI;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.util.adt.pool.GenericPool;

public class Rocket extends BaseBullet {

    private static boolean isRegistredPool = false;

    private Rocket() {
        initCharacteristics(1200, 200);
        sprite = new SimpleBulletAI(TextureLoader.getRocketAmmoTextureRegion(),
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

