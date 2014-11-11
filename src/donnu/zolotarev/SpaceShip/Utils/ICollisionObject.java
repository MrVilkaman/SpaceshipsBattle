package donnu.zolotarev.SpaceShip.Utils;

public interface ICollisionObject extends IDestroy,IGetShape {
    public boolean checkHit(IHaveCoords object);
}

