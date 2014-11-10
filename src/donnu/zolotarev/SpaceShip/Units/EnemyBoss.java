package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.AI.EnemyAI.Enemy1AI;
import donnu.zolotarev.SpaceShip.AI.TurretAI;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;

public class EnemyBoss extends BaseUnit {

    private TurretAI turret;

    public EnemyBoss(){
        super();
        turret = new TurretAI(TextureLoader.getBossTurelTextureRegion(), engine.getVertexBufferObjectManager()){
            @Override
            protected void doAfterUpdate() {
                mX = sprite.getX()+182 - turret.gethW();
                mY = sprite.getY()+143 - turret.gethH();
                mRotation += 1f;
            }
        };

        sprite = new Enemy1AI(TextureLoader.getBossBaseTextureRegion(), engine.getVertexBufferObjectManager()){
            @Override
            protected void doAfterUpdate() {
                weaponController.weaponCooldown();
                checkHitHero();
            }
        };

        turret.setScaleCenter(52,turret.gethH());
        mainScene.attachChild(turret);
        turret.setZIndex(1);
        attachToScene();
    }

    @Override
    protected void loadWeapon(int level) {
        weaponController = new WeaponController(this, new WeaponPos[]{
                new WeaponPos(sprite, 115, 37 , 0),
        });
        weaponController.setShoot(true);

        weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,null), 0);
    }

    @Override
    protected void loadParam(int level) {
        boolean haveShield = false;
        defaultSpeed = 230;
        defaultMaxAngle = 3f;
    }
}
