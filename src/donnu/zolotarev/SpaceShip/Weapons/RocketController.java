package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Units.BaseUnit;

import java.util.Iterator;

public class RocketController extends WeaponController {
    public RocketController(BaseUnit carrier, WeaponPos[] weaponPoses) {
        super(carrier, weaponPoses);
    }

    private int last = 0;

    public void fire(){
        for (int i = 0; i<weaponPoses.length;i++) {
            IGun gun =  guns[i];
            if(gun !=null){
                if (last == i){
                    Iterator<WeaponPos> it = gun.getWeaponPos();
                    while (it.hasNext()){
                        WeaponPos weaponPos = it.next();
                        changePos(weaponPoses[i].add(weaponPos));
                        gun.fire(bufferWeaponPos);
                    }
                    inc();
                    return;
                }
            }else{
                inc();
            }
        }
    }

    private void inc() {
        last++;
        if(!(last < weaponPoses.length)){
            last = 0;
        }

    }
}
