package donnu.zolotarev.SpaceShip.Utils;

import org.andengine.entity.shape.IShape;

public interface ICollisionObject {
    public IShape getShape();
    public  void destroy();
    public boolean checkHit(ICollisionObject2 object);
}

