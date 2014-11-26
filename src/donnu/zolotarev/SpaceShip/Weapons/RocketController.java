package donnu.zolotarev.SpaceShip.Weapons;

import donnu.zolotarev.SpaceShip.Units.BaseUnit;

import java.util.ArrayList;

public class RocketController extends WeaponController {
    public RocketController(BaseUnit carrier, WeaponPos[] weaponPoses) {
        super(weaponPoses);
    }

    private int last = 0;

    public void fire(){
        for (int i = 0; i<weaponPoses.length;i++) {
            IGun gun =  guns[i];
            if(gun !=null){
                if (last == i){
                    ArrayList<WeaponPos> it = gun.getWeaponPos();
                    for (int j = it.size()-1;0<=j;j-- ){
                        changePos(weaponPoses[i].add(it.get(j)));
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
