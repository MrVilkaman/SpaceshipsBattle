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
    private int shuieldHealthDefault = 2000;
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
        setPosition(unit.getCenterX()-cx,unit.getCenterY()-cy);
    }

    public void start(BaseUnit unit) {
        this.unit = unit;
        shuieldHealth = shuieldHealthDefault;
        setVisible(true);
        setIgnoreUpdate(false);
        changeVisibility();
        setPosition(unit.getCenterX()-cx,unit.getCenterY()-cy);
    }

    public static Shield useShield(){
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
        shuieldHealth -= damage;
        changeVisibility();
        if (0 < shuieldHealth){
            return 0;
        }else{
            int i = -shuieldHealth;
            shuieldHealth = 0;
            return i;
        }
    }

    private void changeVisibility() {
        float v = 1.0f*shuieldHealth/shuieldHealthDefault;
        if (0.75f<v){
            v = 1f;
        }else if(0.5f<v){
            v = 0.8f;
        }else if(0.25f<v){
            v = 0.6f;
        }else if(0.0f<v){
            v = 0.4f;
        }else{
            v = 0.0f;
        }
        setAlpha(v);
    }

    public int getHealth() {
        return shuieldHealth;
    }

    public static void resetPool(){
        bulletsPool = null;
    }
}
