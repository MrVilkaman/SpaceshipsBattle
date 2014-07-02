package donnu.zolotarev.SpaceShip;

import donnu.zolotarev.SpaceShip.Enemy.BaseUnit;
import donnu.zolotarev.SpaceShip.Enemy.UnitShape;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class Hero extends BaseUnit {

    private final int SPEED = 500;
    private SimpleGun simpleGun;

    public Hero(Engine shipActivity) {
        sprite = new UnitShape(0, 0, TextureLoader.getShip(), shipActivity.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                return true;
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
                new WeaponPos(sprite.getX()+55,sprite.getY()+44,0),
                new WeaponPos(sprite.getX()+55,sprite.getY()+68,0)
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
