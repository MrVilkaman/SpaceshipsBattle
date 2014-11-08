package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.AI.EnemyAI.HeroAI;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.Effects.Shield;
import donnu.zolotarev.SpaceShip.GameData.HeroFeatures;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.UI.IHealthBar;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import donnu.zolotarev.SpaceShip.Weapons.*;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.DamageModificator;
import donnu.zolotarev.SpaceShip.Weapons.Modificator.IWeaponModificator;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.sprite.Sprite;

public class Hero extends BaseUnit {
    private final int MAX_ANGLE = 7;
    private final int SPEED = 500;
    private int health;

    private boolean isAlive = true;
    private IHealthBar healthBar;
    private HeroFeatures heroFeatures;

    protected RocketController rocketController;

    public Hero(IHealthBar healthBar) {
        super();
        this.healthBar = healthBar;
        sprite =  new HeroAI(TextureLoader.getShip(), engine.getVertexBufferObjectManager()){
            @Override
            protected void doAfterUpdate() {
                weaponController.weaponCooldown();
            }
        };
        hero = this;
        attachToScene();
        heroFeatures = HeroFeatures.get();

    }

    @Override
    protected void loadWeapon(int level) {
        weaponController = new WeaponController(this,
                new WeaponPos[]{
                        new WeaponPos(sprite, 70, 56, 0),
                        new WeaponPos(sprite, 35, 30, -2),
                        new WeaponPos(sprite, 35, 70, 2)});

        IWeaponModificator mode = new DamageModificator(heroFeatures.getExtraBulletDamege(), IWeaponModificator.Mode.Add);
        IGun gun;
        // todo получаить инко об оружии.
        if (heroFeatures.isHaveDoubleAmmo()){
            heroFeatures.useDoubleGun();
            gun =  new DoubleGun(true, BaseBullet.TYPE_SIMPLE_BULLET,mode);
        }else{
            gun =  new SimpleGun(true, BaseBullet.TYPE_SIMPLE_BULLET,mode);
        }
        weaponController.loadWeapon(gun, 0);
        // todo Rocket
        rocketController = new RocketController(this,
                new WeaponPos[]{
                        new WeaponPos(sprite, 35, 30, -2),
                        new WeaponPos(sprite, 35, 70, 2)});
        gun =  new SimpleGun(true, BaseBullet.TYPE_ROCKET_AUTO,mode);
        rocketController.loadWeapon(gun, 0);
        gun =  new SimpleGun(true, BaseBullet.TYPE_ROCKET_AUTO,mode);
        rocketController.loadWeapon(gun, 1);

        if (heroFeatures.isHaveShield()){
            heroFeatures.useShield();
            if (shield == null){
                shield = Shield.useShield();
            }
            shieldPoint = heroFeatures.getShieldPoint();
        }else{
            shieldPoint = 0;
        }

    }

    @Override
    protected void loadParam(int level) {
        health = heroFeatures.getMaxHealth();
    }

    @Override
    public void canFire(boolean b) {
        weaponController.setShoot(b);
    }

    @Override
    public void init(int level, Point point) {
        setStartPosition(point);
        setSize();
        loadWeapon(level);
        loadParam(level);
        if (shield != null){
            shield.start(this);
        }
        healthBar.updateHealthBar(health);
        if (shield != null){
            healthBar.updateShueldBar(shield.getHealth());
        }
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
                float x = 1.5f*pValueX;
                float y = 1.5f*pValueY;
                x = ( Math.abs(x) <= 1)? x:  Utils.getSign(x);
                y = ( Math.abs(y) <= 1)? y:  Utils.getSign(y);
                sprite.getPhysicsHandler().setVelocity(x * SPEED, y * SPEED);
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

        if (shield != null){
            damage = shield.addDamage(damage);
            healthBar.updateShueldBar(shield.getHealth());
        }
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
    public  void destroy(Boolean withAnimate) {
        isAlive = false;
        weaponController.setShoot(false);
        super.destroy(withAnimate);
    }

    public void fireRocket(){
        rocketController.fire();
    }
}
