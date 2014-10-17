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
    private int shuieldHealthDefault = 200;
    private int shuieldHealth = shuieldHealthDefault;

    private Shield() {
        super(0, 0, TextureLoader.getShieldTextureRegion(), GameActivity.engine().getVertexBufferObjectManager());
        BaseGameScene.getActiveScene().attachChild(this);
        cx =  (int)getWidth()/2;
        cy =  (int)getHeight()/2;
    }

    public void destroy() {
        bulletsPool.recyclePoolItem(this);
        setVisible(false);
        setIgnoreUpdate(true);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        this.setPosition(unit.getCenterX()-cx,unit.getCenterY()-cy);
    }

    public void start(BaseUnit unit) {
        this.unit = unit;
        shuieldHealth = shuieldHealthDefault;
        changeVisibility();
        setVisible(true);
        setIgnoreUpdate(false);
    }

    public static  Shield useShield(){
        if (bulletsPool == null){
            bulletsPool = new GenericPool<Shield>() {
                @Override
                protected Shield onAllocatePoolItem() {
                    return new Shield();
                }
            };
        }
        Shield shield = bulletsPool.obtainPoolItem();
        return shield;
    }

    public int addDamage(int damage) {
        changeVisibility();
        shuieldHealth -= damage;
        if (0 < shuieldHealth){
            return 0;
        }else{
            int i = -shuieldHealth;
            shuieldHealth = 0;
            return i;
        }

    }

    private void changeVisibility() {
        setAlpha(1.0f*shuieldHealth/shuieldHealthDefault);
    }

    public int getHealth() {
        return shuieldHealth;
    }
}
