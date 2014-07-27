package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Bullets.BaseBullet;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.UI.IHealthBar;
import donnu.zolotarev.SpaceShip.Weapons.DoubleGun;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import donnu.zolotarev.SpaceShip.Weapons.WeaponPos;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.sprite.Sprite;

public class Hero extends BaseUnit {

    private final int SPEED = 500;

    private boolean isAlive = true;
    private IHealthBar healthBar;

    public Hero(IHealthBar healthBar) {
        super();
        health = 1000;
        ///
        this.healthBar = healthBar;

        healthBar.updateHealthBar(health);

        sprite = new Sprite(0, 0, TextureLoader.getShip(), engine.getVertexBufferObjectManager()) {
            public float xOld;
            public float yOld;

            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                // restriction of movement( walls )
                xOld = mX;
                yOld = mY;
                super.onManagedUpdate(pSecondsElapsed);
                if (this.mX < 0 || this.mX + this.getWidth() > SpaceShipActivity.getCameraWidth()){
                    mX = xOld;
                }

                if (this.mY < 0 || this.mY + this.getHeight() > SpaceShipActivity.getCameraHeight()){
                    mY = yOld;
                }
                /// weapon cooldown
               weaponController.weaponCooldown();

            }
        };
        //   sprite.setScale(0.5f);
        loadWeapon();
        attachToScene();
    }

    @Override
    protected void loadWeapon() {
        weaponController = new WeaponController(this, new WeaponPos[]{new WeaponPos(70, 50, 0), new WeaponPos(35, 30,
                -2), new WeaponPos(35, 70, 2)});
        weaponController.loadWeapon(new DoubleGun(true, BaseBullet.TYPE_SIMPLE_BULLET_2), 0);
       // todo раскоментировать
        weaponController.loadWeapon(new SimpleGun(true, BaseBullet.TYPE_SIMPLE_BULLET), 1);
        weaponController.loadWeapon(new SimpleGun(true, BaseBullet.TYPE_SIMPLE_BULLET), 2);
    }

    @Override
    public void canFire(boolean b) {
        weaponController.setShoot(b);
    }

    @Override
    public void init(Point point) {
        setStartPosition(point);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public AnalogOnScreenControl.IAnalogOnScreenControlListener getCallback() {
        registerPhysicsHandler();
        return new AnalogOnScreenControl.IAnalogOnScreenControlListener() {
            @Override
            public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX,
                    final float pValueY) {
                physicsHandler.setVelocity(pValueX * SPEED, pValueY * SPEED);
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
    public synchronized void destroy() {
        isAlive = false;
        super.destroy();
    }
}
