package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.EnemyAI.Enemy1AI;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.Minigun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import org.andengine.util.adt.pool.GenericPool;

public class EnemyWithMiniGun extends BaseUnit {

    private static boolean isRegistredPool = false;


    private EnemyWithMiniGun(){
        super();

        sprite = new Enemy1AI(TextureLoader.getEnemyShipOrange(), engine.getVertexBufferObjectManager()){

            @Override
            protected void doAfterUpdate() {
                weaponController.weaponCooldown();
            }
        };

        attachToScene();
    }

    @Override
    public boolean addDamageAndCheckDeath(int damage) {
        int health =  unitSpecifications.getHealth() - damage;
        unitSpecifications.setHealth(health);
        return health < 0;
    }

    @Override
    protected void loadWeapon(int level) {
        weaponController = new WeaponController(this, new WeaponPos[]{
                new WeaponPos(sprite, 115, 37 , 0),
                new WeaponPos(sprite, 40, 14 , 0),
                new WeaponPos(sprite, 40, 59 , 0)
        });
        weaponController.setShoot(true);
        weaponController.loadWeapon(new Minigun(false, BaseBullet.TYPE_SIMPLE_BULLET,null), 0);
    }

    @Override
    protected void loadParam(int level) {
        defaultHealth = 500;
        defaultSpeed = 100;
        defaultMaxAngle = 3f;
    }

    @Override
    public void canFire(boolean b) {

    }

    public static void initPool() {
    //    if (!isRegistredPool){
            isRegistredPool = true;
            registredPool(EnemyWithMiniGun.class,new GenericPool() {
                @Override
                protected EnemyWithMiniGun onAllocatePoolItem() {
                    return new EnemyWithMiniGun();
                }
            });
     //   }
    }

}
