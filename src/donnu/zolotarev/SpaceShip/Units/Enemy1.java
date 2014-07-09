package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.WeaponPos;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.entity.sprite.Sprite;

public class Enemy1  extends BaseUnit {

    public Enemy1(){
        super();
        health = 500;
        ///
        sprite = new Sprite(0, 0, TextureLoader.getEnemyShip(), engine.getVertexBufferObjectManager()){
            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                if(mX - getWidth()<200){
                    physicsHandler.setVelocityX(0);
                }
                super.onManagedUpdate(pSecondsElapsed);

                weaponController.weaponCooldown();
            }

        };
        attachToScene();
        loadWeapon();
        sprite.setRotation(180);

        registerPhysicsHandler();
       physicsHandler.setVelocityX(-100);

    }

    @Override
    public boolean addDamageAndCheckDeath(int damage) {
        health -= damage;
        return health < 0;
    }

    @Override
    protected void loadWeapon() {
        weaponController = new WeaponController(this, new WeaponPos[]{
                new WeaponPos(0,50,180)
        });
        weaponController.setShoot(true);
        weaponController.loadWeapon(new SimpleGun(false), 0);
    }

    @Override
    public void canFire(boolean b) {

    }
}
