package donnu.zolotarev.SpaceShip.Units;

import android.graphics.Point;
import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.AI.BulletAI.MeteorAI;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.util.adt.pool.GenericPool;

import java.util.Random;

public class Meteor extends BaseUnit {

    private Meteor(){
        super();
        sprite = new MeteorAI(TextureLoader.getmMeteorite1TextureRegion(), engine.getVertexBufferObjectManager()){
            @Override
            protected void destroyed() {
                // todo Учитывать размер объекта
                destroy(false);
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
        defaultSpeed = 1800;
        price = 50;
        defaultHealth = 300;
        defaultDamage = 500;
    }

    Random random = new Random();
    @Override
    public void init(int level, Point point, float angle) {
        PointF pointF = hero.getPosition();


        pointF.y += ((int) Utils.random(- 5f, 5f)) * 10;
        if (angle == 180){
            point = new Point(1300, random.nextInt(65) * 10);
        }

        angle = Utils.getAngle(point.x, point.y, pointF.x, pointF.y);
        super.init(level, point, angle /*+ Utils.random(- 10f, 10f)*/);
        waySpecifications = new WaySpecifications(defaultSpeed,defaultMaxAngle);
        sprite.start(waySpecifications);
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
