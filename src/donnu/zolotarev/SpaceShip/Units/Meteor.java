package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import donnu.zolotarev.SpaceShip.AI.BulletAI.MeteorAI;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.util.adt.pool.GenericPool;

public class Meteor extends BaseUnit {

    private Meteor(){
        super();
        sprite = new MeteorAI(TextureLoader.getmMeteorite1TextureRegion(), engine.getVertexBufferObjectManager()){
            @Override
            protected void destroyed() {
                destroy();
            }

            @Override
            protected void doAfterUpdate() {
                checkHitHero();
                mRotation +=1f;
            }
        };
        sprite.animate(100);
        attachToScene();
    }

    @Override
    protected void loadWeapon(int level) {

    }

    @Override
    protected void loadParam(int level) {
        defaultSpeed = 1200;
        price = 20;
        defaultHealth = 200;
        defaultDamage = 300;
    }

    @Override
    public void init(int level, Point point, float angle) {
        super.init(level, point, angle + Utils.random(- 20f, 20f));
    }

    @Override
    public void init(int level, Point point, float angle, WaySpecifications us) {
        super.init(level, point, angle + Utils.random(- 20f, 20f), us);
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
