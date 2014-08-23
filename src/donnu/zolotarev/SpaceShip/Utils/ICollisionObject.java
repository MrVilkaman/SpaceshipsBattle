package donnu.zolotarev.SpaceShip.Utils;

import donnu.zolotarev.SpaceShip.EnemyAI.SpriteAI;

public interface ICollisionObject {
    public org.andengine.entity.sprite.Sprite getShape();
    public  void destroy();
    public boolean checkHit(IHaveCoords object);
}

