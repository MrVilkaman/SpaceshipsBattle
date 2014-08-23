package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.entity.sprite.Sprite;

public class WeaponPos {
    public float rad;
    public float radAngle;
    public float anlge;
    public float x;
    public float y;

    public WeaponPos(Sprite sprite, float x, float y, float anlge){
        this.y = y;
        this.x = x;
        if (sprite!=null){
            radAngle = Utils.getAngle(sprite.getRotationCenterX(), sprite.getRotationCenterY(),x,y);
            rad = Utils.distance(sprite.getRotationCenterX(), sprite.getRotationCenterY(),x,y);
        }
        this.anlge = anlge;
    }
}
