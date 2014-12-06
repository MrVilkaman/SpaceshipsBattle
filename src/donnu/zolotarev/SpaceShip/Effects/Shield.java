package donnu.zolotarev.SpaceShip.Effects;

import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

public class Shield extends AnimatedSprite {

    protected static GenericPool<Shield> bulletsPool;
    private final int cx;
    private final int cy;
    private final Sprite sprite;
    private final int cx2;
    private final int cy2;
    private BaseUnit unit;
    private int shuieldHealthDefault = 0;
    private int shuieldHealth = 0;
    private boolean isAlive = false;

    private Shield() {
        super(0, 0, TextureLoader.getShieldAnimationTextureRegion(), GameActivity.engine().getVertexBufferObjectManager());
        sprite = new Sprite(0,0,TextureLoader.getShieldTextureRegion(),GameActivity.engine().getVertexBufferObjectManager());
        BaseGameScene scene = BaseGameScene.getActiveScene();
        scene.attachChild(this);
        scene.attachChild(sprite);
        setZIndex(4);
        sprite.setZIndex(3);
        sprite.setScale(1.1f);
        scene.sortChildren();
        cx =  (int)sprite.getWidth()/2;
        cy =  (int)sprite.getHeight()/2;
        cx2 =  (int)getWidth()/2;
        cy2 =  (int)getHeight()/2;
        setVisible(false);
        setIgnoreUpdate(true);
        sprite.setIgnoreUpdate(true);
        setRotation(180);
    }

    public void destroy() {

        isAlive = false;
        if (isAlive){
            bulletsPool.recyclePoolItem(this);
        }
        setVisible(false);
        setIgnoreUpdate(true);
        sprite.setIgnoreUpdate(true);

    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        if (isAlive){
            sprite.setPosition(unit.getCenterX() - cx, unit.getCenterY() - cy);
            setPosition(unit.getCenterX() - cx2, unit.getCenterY() - cy2);
        }
    }

    public void start(BaseUnit unit) {
        this.unit = unit;
        isAlive = true;
        shuieldHealth = unit.getShieldPoint();
        shuieldHealthDefault = shuieldHealth;
       // setVisible(true);
        setRotation(unit.getShape().getRotation()-180);
        sprite.setIgnoreUpdate(false);
        setIgnoreUpdate(false);
        stopAnimation(0);
        changeVisibility();
        sprite.setPosition(unit.getCenterX() - cx, unit.getCenterY() - cy);
        setPosition(unit.getCenterX() - cx2, unit.getCenterY() - cy2);
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
        if (0 < shuieldHealth){;
            animate();
            return 0;
        }else{
            int i = -shuieldHealth;
            shuieldHealth = 0;
            return i;
        }
    }

    public void animate() {
        setVisible(true);
        if (!isAnimationRunning() || 6   < getCurrentTileIndex()){

        super.animate(30, new IAnimationListener() {
            @Override
            public void onAnimationStarted(AnimatedSprite pAnimatedSprite, int pInitialLoopCount) {

            }

            @Override
            public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
                    int pNewFrameIndex) {

            }

            @Override
            public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite, int pRemainingLoopCount,
                    int pInitialLoopCount) {
                stopAnimation(0);
                setVisible(false);
               //destroy();
            }

            @Override
            public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {

            }
        });
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
        sprite.setAlpha(v);
    }

    public int getHealth() {
        return shuieldHealth;
    }

    public static void bulletsPool(){
        bulletsPool = null;
    }
}
