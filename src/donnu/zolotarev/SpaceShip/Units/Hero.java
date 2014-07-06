package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.WeaponPos;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import donnu.zolotarev.SpaceShip.Weapons.WeaponController;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.sprite.Sprite;

public class Hero extends BaseUnit {

    private final int SPEED = 500;
    private WeaponController weaponController;

    public Hero(Engine shipActivity) {
        sprite = new Sprite(0, 0, TextureLoader.getShip(), shipActivity.getVertexBufferObjectManager()) {
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
                if (weaponController.isShoot()){
                    weaponController.fire();
                }

            }
        };
        //   sprite.setScale(0.5f);
        loadWeapon();
        attachToScene();
    }

    public void loadWeapon() {
        weaponController = new WeaponController(this, new WeaponPos[]{new WeaponPos(70, 50, 0), new WeaponPos(35, 30,
                0), new WeaponPos(35, 70, 0)});
        weaponController.loadWeapon(new SimpleGun(), 0);
        weaponController.loadWeapon(new SimpleGun(), 1);
        weaponController.loadWeapon(new SimpleGun(), 2);
    }

    public void fire(boolean b) {
        weaponController.setShoot(b);
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
        return false;
    }
}
