package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.AI.EnemyAI.Enemy1AI;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Bullets.SimpleBullet;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Weapons.DoubleGun;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.BulletFrameNumberModificator;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import org.andengine.util.adt.pool.GenericPool;

import java.util.Random;

public class EnemySingleGun extends BaseUnit {

    private static boolean isRegistredPool = false;

    private EnemySingleGun(){
        super();
        sprite = new Enemy1AI(TextureLoader.getEnemyShipLightBlue(), engine.getVertexBufferObjectManager()){

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
        weaponController.reloadWeapons();
        if(!(level < Constants.MAX_UNIT_LEVEL)){
            level = level - Constants.MAX_UNIT_LEVEL;
        }

        Random random = new Random();
        IWeaponModificator[] mod = {
        new BulletFrameNumberModificator(random.nextInt(SimpleBullet.BULLET_FRAME_COUNT))
        };
        switch (level){
            case 0:
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,mod), 0);
                break;
            case 1:
                weaponController.loadWeapon(new DoubleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,mod), 0);
                break;
            case 2:
                weaponController.loadWeapon(new DoubleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,mod), 0);
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,mod), 1);
                weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,mod), 2);
                break;
        }

    }

    @Override
    protected void loadParam(int level) {
        boolean haveShield = false;
        defaultSpeed = 230;
        defaultMaxAngle = 3f;
        if(!(level < Constants.MAX_UNIT_LEVEL)){
            level = level - Constants.MAX_UNIT_LEVEL;
            haveShield = true;
        }
        switch (level){
            case 0:
                defaultHealth = 300;
                price = 10;
                break;
            case 1:
                defaultHealth = 400;
                price = 20;
                break;
            case 2:
                price = 45;
                defaultHealth = 500;
                defaultSpeed = 200;
                defaultMaxAngle = 2f;
                break;
        }

        if (haveShield){
            switch (level){
                case 0:
                    shieldPoint = 300;
                    price = 15;
                    break;
                case 1:
                    shieldPoint = 400;
                    price = 30;
                    break;
                case 2:
                    shieldPoint = 400;
                    price = 65;
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
        registredPool(EnemySingleGun.class,new GenericPool() {
            @Override
            protected EnemySingleGun onAllocatePoolItem() {
                return new EnemySingleGun();
            }
        });
    }

}
