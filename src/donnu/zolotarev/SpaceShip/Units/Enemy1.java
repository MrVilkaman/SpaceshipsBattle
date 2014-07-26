package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.Bullets.BulletBase;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

public class Enemy1  extends BaseUnit {

    private static boolean isRegistredPool = false;


    private Enemy1(){
        super();
        health = 500;
        SPEED = 100;
        ///
        sprite = new Sprite(0, 0, TextureLoader.getEnemyShip(), engine.getVertexBufferObjectManager()){
            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                if(mX <250){
                    physicsHandler.setVelocityX(0);
                    mX = 249;
                }
                super.onManagedUpdate(pSecondsElapsed);

                weaponController.weaponCooldown();
            }

        };
        attachToScene();
        loadWeapon();

        registerPhysicsHandler();
    }

    @Override
    public boolean addDamageAndCheckDeath(int damage) {
        health -= damage;
        return health < 0;
    }

    @Override
    protected void loadWeapon() {
        weaponController = new WeaponController(this, new WeaponPos[]{
                new WeaponPos(5,45,180)
        });
        weaponController.setShoot(true);
        weaponController.loadWeapon(new SimpleGun(false, BulletBase.TYPE_SIMPLE_BULLET), 0);
    }

    @Override
    public void canFire(boolean b) {

    }

    public static void initPool() {
        if (!isRegistredPool){
            isRegistredPool = true;
            registredPool(Enemy1.class,new GenericPool() {
                @Override
                protected Enemy1 onAllocatePoolItem() {
                    return new Enemy1();
                }
            });
        }
    }

}
