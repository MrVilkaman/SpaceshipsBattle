package donnu.zolotarev.SpaceShip.Bullets;

import donnu.zolotarev.SpaceShip.AI.BulletAI.SimpleBulletAI;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;
import org.andengine.util.adt.pool.GenericPool;

public class SimpleBullet extends BaseBullet {

    public static final int BULLET_FRAME_COUNT = 5;
    private static boolean isRegistredPool = false;

    private SimpleBullet() {

        initCharacteristics(1100, 40, 0f);
        sprite = new SimpleBulletAI(TextureLoader.getSimpleBulletTextureRegion(),
                GameActivity.getInstance().getEngine().getVertexBufferObjectManager()){
            @Override
            protected void destroyed() {
               destroy(true);
            }

            @Override
            protected void doAfterUpdate() {
                checkHit();
            }
        };
        settings();
    }

    @Override
    public void init(float x, float y, float direction, int bullitType, boolean unitTarget,
            IWeaponModificator[] weaponModificator, int bulletFrameNumber) {

        super.init(x, y, direction, bullitType, unitTarget, weaponModificator, bulletFrameNumber);
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

