package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.EnemyAI.HeroAI;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.UI.IHealthBar;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.sprite.Sprite;

public class Hero extends BaseUnit {
    private final int MAX_ANGLE = 15;
    private final int SPEED = 500;
    private int health;

    private boolean isAlive = true;
    private IHealthBar healthBar;
    private HeroFeatures heroFeatures;

    public Hero(IHealthBar healthBar) {
        super();
        heroFeatures = HeroFeatures.get();
        health = heroFeatures.getMaxHealth();
        ///
        this.healthBar = healthBar;

        healthBar.updateHealthBar(health);
        sprite =  new HeroAI(TextureLoader.getShip(), engine.getVertexBufferObjectManager()){
            @Override
            protected void doAfterUpdate() {
                weaponController.weaponCooldown();
            }
        };

        loadWeapon();
        attachToScene();
    }

    @Override
    protected void loadWeapon() {
        weaponController = new WeaponController(this, new WeaponPos[]{new WeaponPos(70, 50, 0), new WeaponPos(35, 30,
                -2), new WeaponPos(35, 70, 2)});
        weaponController.loadWeapon(new SimpleGun(true, BaseBullet.TYPE_SIMPLE_BULLET), 0);
    }

    @Override
    public void canFire(boolean b) {
        weaponController.setShoot(b);
    }

    @Override
    public void init(Point point) {
        setStartPosition(point);
        setSize();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public AnalogOnScreenControl.IAnalogOnScreenControlListener getCallback() {
        //registerPhysicsHandler();
        return new AnalogOnScreenControl.IAnalogOnScreenControlListener() {
            @Override
            public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX,
                    final float pValueY) {
                sprite.getPhysicsHandler().setVelocity(pValueX * SPEED, pValueY * SPEED);
               // float ang = (pValueY <= 0.5f) ?  pValueY: 0.5f ;
                sprite.setRotateAngle(pValueY*MAX_ANGLE);
            }

            @Override
            public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
             /*   sprite.registerEntityModifier(new SequenceEntityModifier(new ScaleModifier(0.25f, 1, 1.5f),
                        new ScaleModifier(0.25f, 1.5f, 1))); */ //todo Не понятно на что они влияют!
            }
        };
    }

    @Override
    public boolean addDamageAndCheckDeath(int damage) {
        health -= damage;
        if(health < 0){
            health = 0;
        }
        healthBar.updateHealthBar(health);
        return health == 0;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public  void destroy() {
        isAlive = false;
        weaponController.setShoot(false);
        super.destroy();
    }
}
