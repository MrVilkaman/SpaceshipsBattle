package donnu.zolotarev.SpaceShip;

import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.util.adt.pool.GenericPool;

public class Shield extends AnimatedSprite {

    protected static GenericPool<Shield> bulletsPool;
    private final int cx;
    private final int cy;
    private BaseUnit unit;

    private Shield() {
        super(0, 0, TextureLoader.getShieldTextureRegion(), GameActivity.engine().getVertexBufferObjectManager());
        BaseGameScene.getActiveScene().attachChild(this);
        cx =  (int)getWidth()/2;
        cy =  (int)getHeight()/2;
    }

    private void destroy() {
        bulletsPool.recyclePoolItem(this);
        setVisible(false);
        setIgnoreUpdate(true);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        this.setPosition(unit.getCenterX()-cx,unit.getCenterY()-cy);
    }

    public static void run(BaseUnit baseUnit) {

        if (bulletsPool == null){
            bulletsPool = new GenericPool<Shield>() {
                @Override
                protected Shield onAllocatePoolItem() {
                    return new Shield();
                }
            };
        }

        Shield boom = bulletsPool.obtainPoolItem();
        /*PointF f = baseUnit.getPosition();
        boom.setPosition(f.x - boom.getWidth()/2 ,f.y- boom.getHeight()/2);*/
        boom.setUnit(baseUnit);
        boom.setVisible(true);
        boom.setIgnoreUpdate(false);
    }

    public void setUnit(BaseUnit unit) {
        this.unit = unit;
    }
}
