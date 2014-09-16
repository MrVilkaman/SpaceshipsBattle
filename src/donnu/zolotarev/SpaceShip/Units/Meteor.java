package donnu.zolotarev.SpaceShip.Units;

import donnu.zolotarev.SpaceShip.AI.EnemyAI.Enemy1AI;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import org.andengine.util.adt.pool.GenericPool;

public class Meteor extends BaseUnit {

    private Meteor(){
        super();

        sprite = new Enemy1AI(TextureLoader.getmMeteorite1TextureRegion(), engine.getVertexBufferObjectManager());
        sprite.animate(100);
        attachToScene();
    }

    @Override
    protected void loadWeapon(int level) {

    }

    @Override
    protected void loadParam(int level) {
        defaultSpeed = 100;
        defaultMaxAngle = 3f;
    }

    protected static void poolInit() {
        registredPool(Meteor.class,new GenericPool() {
            @Override
            protected Meteor onAllocatePoolItem() {
                return new Meteor();
            }
        });
    }
}
