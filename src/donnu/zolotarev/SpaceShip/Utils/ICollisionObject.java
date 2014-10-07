package donnu.zolotarev.SpaceShip.Utils;

public interface ICollisionObject extends IDestroy {
    public org.andengine.entity.sprite.Sprite getShape();

    public boolean checkHit(IHaveCoords object);
}

