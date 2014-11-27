package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.entity.sprite.Sprite;

public class WeaponPos {
    private Sprite sprite;
    public float rad;
    public float radAngle;
    public float anlge;
    public float x;
    public float y;

    public WeaponPos(Sprite sprite, float x, float y, float anlge){
        this.y = y;
        this.x = x;
        if (sprite!=null){
            this.sprite = sprite;
            radAngle = Utils.getAngle(sprite.getRotationCenterX(), sprite.getRotationCenterY(),x,y);
            rad = Utils.distance(sprite.getRotationCenterX(), sprite.getRotationCenterY(),x,y);
        }
        this.anlge = anlge;
    }

    public WeaponPos add(WeaponPos weaponPos){
        return add(weaponPos.x,weaponPos.y,weaponPos.anlge);
    }

    public WeaponPos add(float xx, float yy, float anlge){
        if (sprite!=null){
            radAngle = Utils.getAngle(sprite.getRotationCenterX(), sprite.getRotationCenterY(),x+xx,y+yy);
            rad = Utils.distance(sprite.getRotationCenterX(), sprite.getRotationCenterY(),x+xx,y+yy);
        }
        this.anlge += anlge;
        return this;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
