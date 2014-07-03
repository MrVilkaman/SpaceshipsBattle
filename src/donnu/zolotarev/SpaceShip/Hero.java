package donnu.zolotarev.SpaceShip;

import donnu.zolotarev.SpaceShip.Enemy.BaseUnit;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.sprite.Sprite;

public class Hero extends BaseUnit {

    private final int SPEED = 500;
    private SimpleGun simpleGun;

    public Hero(Engine shipActivity) {
        sprite = new Sprite(0, 0, TextureLoader.getShip(), shipActivity.getVertexBufferObjectManager()) {
            public float xOld;
            public float yOld;

            @Override
            protected void onManagedUpdate(float pSecondsElapsed) {
                xOld = mX;
                yOld = mY;
                super.onManagedUpdate(pSecondsElapsed);
                if(this.mX < 0 || this.mX + this.getWidth() > SpaceShipActivity.getCameraWidth()) {
                    mX = xOld;
                }

                if(this.mY < 0 || this.mY + this.getHeight() > SpaceShipActivity.getCameraHeight()) {
                    mY = yOld;
                }
            }
        };
     //   sprite.setScale(0.5f);
        loadWeapon();
        attachToScene();
    }

    public WeaponPos[] getWeaponPos(){
        WeaponPos[] weaponPoses = {
                new WeaponPos(sprite.getX()+70,sprite.getY()+50,0),
                new WeaponPos(sprite.getX()+70,sprite.getY()+62,0),
                new WeaponPos(sprite.getX()+55,sprite.getY()+44,-1),
                new WeaponPos(sprite.getX()+55,sprite.getY()+68,1)
        };
        return weaponPoses;
    }

    public void loadWeapon(){
        simpleGun =  new SimpleGun(this);
    }

    public void fire() {
       simpleGun.fire();
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


}
