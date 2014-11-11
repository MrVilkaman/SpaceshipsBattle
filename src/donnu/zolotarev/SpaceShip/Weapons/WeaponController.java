package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.entity.sprite.Sprite;

import java.util.Iterator;

public class WeaponController {

    protected final WeaponPos[] weaponPoses;
    protected IGun[] guns;
    private boolean shoot;
    protected WeaponPos bufferWeaponPos;

    public WeaponController(WeaponPos[] weaponPoses){
        if (weaponPoses == null){
            new Exception("weaponPoses не может быть null!");
        }
        this.weaponPoses = weaponPoses;
        guns = new IGun[weaponPoses.length];
        bufferWeaponPos = new WeaponPos(null, 0, 0, 0);
    }

    public boolean loadWeapon(IGun gun, int slot){
        if (weaponPoses.length>slot){
            guns[slot] = gun;
            return true;
        }
        return false;
    }

    private void fire(){
        for (int i = 0; i<weaponPoses.length;i++) {
            IGun gun =  guns[i];
            if(gun !=null){
                if (gun.shoot()){
                    Iterator<WeaponPos> it = gun.getWeaponPos();
                    while (it.hasNext()){
                        WeaponPos weaponPos = it.next();
                        changePos(weaponPoses[i].add(weaponPos));
                        gun.fire(bufferWeaponPos);
                    }
                }
            }
        }
    }

    protected void changePos(WeaponPos weaponPos){
        Sprite sp =  weaponPos.getSprite();
        float rad = sp.getRotation();
        bufferWeaponPos.x = weaponPos.rad*(float)Math.cos(Utils.degreeToRad(rad + weaponPos.radAngle)) + sp.getRotationCenterX() + sp.getX()-7;
        bufferWeaponPos.y = weaponPos.rad*(float)Math.sin(Utils.degreeToRad(rad + weaponPos.radAngle)) + sp.getRotationCenterY() + sp.getY();
        bufferWeaponPos.anlge = weaponPos.anlge + rad ;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
        if(!shoot){
            for (IGun gun: guns){
                if (gun != null){
                    gun.reload();
                }
            }
        }
    }

    public void weaponCooldown(){
        if (shoot){
            fire();
        }
    }
}
