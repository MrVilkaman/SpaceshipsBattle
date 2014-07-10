package donnu.zolotarev.SpaceShip;

import org.andengine.entity.shape.IShape;

public interface ICollisionObject {
    public IShape getShape();
    public  void destroy();
}
