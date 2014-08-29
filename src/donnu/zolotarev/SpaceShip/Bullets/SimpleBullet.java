package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.BulletAI.SimpleBulletAI;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet extends BaseBullet {

    private static boolean isRegistredPool = false;

    private SimpleBullet() {

        initCharacteristics(1000, 40, 0f);
        sprite = new SimpleBulletAI(TextureLoader.getSimpleBulletTextureRegion(),
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


    protected static void poolInit() {
        isRegistredPool = true;
        registredPool(SimpleBullet.class,new GenericPool() {
                @Override
                protected SimpleBullet onAllocatePoolItem() {
                    return new SimpleBullet();
                }
            });
    }

}

