package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.AI.EnemyAI.Enemy1AI;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.DoubleGun;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import org.andengine.util.adt.pool.GenericPool;

public class EnemySingleGun extends BaseUnit {

    private static boolean isRegistredPool = false;

    private EnemySingleGun(){
        super();
        sprite = new Enemy1AI(TextureLoader.getEnemyShipLightBlue(), engine.getVertexBufferObjectManager()){

            @Override
            protected void doAfterUpdate() {
                weaponController.weaponCooldown();
            }
        };
        attachToScene();
    }

    @Override
    protected void loadWeapon(int level) {
        weaponController = new WeaponController(this, new WeaponPos[]{
                new WeaponPos(sprite, 115, 37 , 0),
                new WeaponPos(sprite, 40, 14 , 0),
                new WeaponPos(sprite, 40, 59 , 0)
        });
        weaponController.setShoot(true);
        switch (level){
            case 0:
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,null), 0);
                break;
            case 1:
                weaponController.loadWeapon(new DoubleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,null), 0);
                break;
            case 2:
                weaponController.loadWeapon(new DoubleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,null), 0);
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,null), 1);
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,null), 2);
                break;
        }

    }

    @Override
    protected void loadParam(int level) {
        defaultSpeed = 100;
        defaultMaxAngle = 3f;
        switch (level){
            case 0:
                defaultHealth = 300;
                break;
            case 1:
                defaultHealth = 400;
                break;
            case 2:
                defaultHealth = 500;
                defaultSpeed = 80;
                defaultMaxAngle = 2f;
                break;
        }

    }

    protected static void poolInit() {
        isRegistredPool = true;
        registredPool(EnemySingleGun.class,new GenericPool() {
            @Override
            protected EnemySingleGun onAllocatePoolItem() {
                return new EnemySingleGun();
            }
        });
    }

}
