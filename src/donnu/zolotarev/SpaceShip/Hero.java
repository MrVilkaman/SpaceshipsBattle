package donnu.zolotarev.SpaceShip;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.Scenes.MainScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Weapons.SimpleGun;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class Hero {


    private final Sprite sprite;
    private PhysicsHandler physicsHandler;
    private final int SPEED = 500;
    private SimpleGun simpleGun;

    public Hero(Engine shipActivity) {
        sprite = new Sprite(0, 0, TextureLoader.getShip(), shipActivity.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                return true;
            }
        };
        sprite.setScale(0.5f);
        loadWeapon();
        attachToScene();
    }

    public void setStartPosition(Point point){
        sprite.setX(point.x);
        sprite.setY(point.y);
    }

    public void attachToScene() {
        Scene mainScene = MainScene.getAcitveScene();
        mainScene.attachChild(sprite);
        mainScene.registerTouchArea(sprite);
    }

    public WeaponPos[] getWeaponPos(){
        WeaponPos[] weaponPoses = {
                new WeaponPos(sprite.getX()+140,sprite.getY()+100,0),
                new WeaponPos(sprite.getX()+140,sprite.getY()+124,0),
                new WeaponPos(sprite.getX()+110,sprite.getY()+88,0),
                new WeaponPos(sprite.getX()+110,sprite.getY()+136,0)
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

    public PhysicsHandler registerPhysicsHandler() {
        physicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(physicsHandler);
        return physicsHandler;
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
