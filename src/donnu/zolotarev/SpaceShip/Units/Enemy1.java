package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.EnemyAI.Enemy1AI;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import org.andengine.util.adt.pool.GenericPool;

public class Enemy1  extends BaseUnit {

    private static boolean isRegistredPool = false;


    private Enemy1(){
        super();
        defaultHealth = 200;
        defaultSpeed = 100;
        ///
        sprite = new Enemy1AI(TextureLoader.getEnemyShip(), engine.getVertexBufferObjectManager()){
            private float oldAngle = -999;
            @Override
            protected void doAfterUpdate() {
                if (oldAngle != sprite.getRotation()){
                    oldAngle =  sprite.getRotation();
                    sprite.getPhysicsHandler().setVelocityX((float)(speed * Math.cos(Utils.degreeToRad(oldAngle))));
                    sprite.getPhysicsHandler().setVelocityY((float) (speed * Math.sin(Utils.degreeToRad(oldAngle))));
                }
                weaponController.weaponCooldown();
            }
        };
        loadWeapon();
        attachToScene();
        //registerPhysicsHandler();
    }

    @Override
    public boolean addDamageAndCheckDeath(int damage) {
        health -= damage;
        return health < 0;
    }

    @Override
    protected void loadWeapon() {
        weaponController = new WeaponController(this, new WeaponPos[]{
                new WeaponPos(5,45,0)
        });
        weaponController.setShoot(true);
        weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET), 0);
    }

    @Override
    public void canFire(boolean b) {

    }

    public static void initPool() {
    //    if (!isRegistredPool){
            isRegistredPool = true;
            registredPool(Enemy1.class,new GenericPool() {
                @Override
                protected Enemy1 onAllocatePoolItem() {
                    return new Enemy1();
                }
            });
     //   }
    }

}
