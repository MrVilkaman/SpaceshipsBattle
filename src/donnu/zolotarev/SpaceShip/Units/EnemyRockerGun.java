package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.AI.EnemyAI.RocketAI;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import org.andengine.util.adt.pool.GenericPool;

public class EnemyRockerGun extends BaseUnit {

    private static boolean isRegistredPool = false;


    private EnemyRockerGun(){
        super();
        sprite = new RocketAI(TextureLoader.getEnemyShipGreen(), engine.getVertexBufferObjectManager()){

            @Override
            protected void doAfterUpdate() {
                weaponController.weaponCooldown();
                checkHitHero();
            }
        };
        attachToScene();
    }

    @Override
    protected void loadWeapon(int level) {
        weaponController = new WeaponController(new WeaponPos[]{
                new WeaponPos(sprite, 115, 37 , 0),
                new WeaponPos(sprite, 40, 14 , 0),
                new WeaponPos(sprite, 40, 59 , 0)
        });
        weaponController.setShoot(true);
        if(!(level < Constants.MAX_UNIT_LEVEL)){
            level = level - Constants.MAX_UNIT_LEVEL;
        }
        switch (level){
            case 0:
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_ROCKET,null), 0);
                break;
            case 1:
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_ROCKET_AUTO,null), 0);
                break;
        }

    }

    @Override
    protected void loadParam(int level) {
        boolean haveShield = false;
        defaultSpeed = 200;
        defaultMaxAngle = 4f;
        if(!(level < Constants.MAX_UNIT_LEVEL)){
            level = level - Constants.MAX_UNIT_LEVEL;
            haveShield = true;
        }

        switch (level){
            case 0:
                defaultHealth = 1000;
                price = 120;
                break;
            case 1:
                defaultHealth = 1500;
                price = 180;
                break;
        }

        if (haveShield){
            switch (level){
                case 0:
                    shieldPoint = 800;
                    price = 160;
                    break;
                case 1:
                    shieldPoint = 1000;
                    price = 250;
                    break;
                default:
                    shieldPoint = 0;
            }
        }else{
            shieldPoint = 0;
        }

    }

    protected static void poolInit() {
        isRegistredPool = true;
        registredPool(EnemyRockerGun.class,new GenericPool() {
            @Override
            protected EnemyRockerGun onAllocatePoolItem() {
                return new EnemyRockerGun();
            }

        });
    }

}
