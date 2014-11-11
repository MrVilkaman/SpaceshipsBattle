package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.AI.EnemyAI.Boss1AI;
import donnu.zolotarev.SpaceShip.AI.TurretAI;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.SpeedModificator;
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
                mX = sprite.getX()+193 - turret.gethW();
                mY = sprite.getY()+143 - turret.gethH();
            }
        };

        sprite = new Boss1AI(TextureLoader.getBossBaseTextureRegion(), engine.getVertexBufferObjectManager()){
            @Override
            protected void doAfterUpdate() {
                weaponController.weaponCooldown();
                checkHitHero();
            }
        };

        turret.setScaleCenter(53,37);
        turret.setRotation(180);
        mainScene.attachChild(turret);
        turret.setZIndex(1);
        attachToScene();
    }

    @Override
    protected void loadWeapon(int level) {
        weaponController = new WeaponController(turret, new WeaponPos[]{
                new WeaponPos(turret, 125, 23 , 0),
                new WeaponPos(turret, 125, 36 , 0),
                new WeaponPos(turret, 125, 49 , 0),
                new WeaponPos(turret, 125, 49 , 0),
                new WeaponPos(turret, 1, 49 , 0),
        });
        weaponController.setShoot(true);
        SpeedModificator modificator = new SpeedModificator(0.25f);
        weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,modificator), 0);
        weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,modificator), 1);
        weaponController.loadWeapon(new SimpleGun(false, BaseBullet.TYPE_SIMPLE_BULLET,modificator), 2);
    }

    @Override
    protected void loadParam(int level) {
        defaultSpeed = 150;
        defaultMaxAngle = 0.7f;
        defaultHealth = 15000;
        price = 600;
    }

    @Override
    public void destroy(Boolean withAnimate) {
        super.destroy(withAnimate);
        turret.setVisible(false);
        turret.setIgnoreUpdate(true);
        turret.restart();
    }
}
