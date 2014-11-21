package donnu.zolotarev.SpaceShip.AI;

import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class SimpleAI extends SpriteAI {

    protected WaySpecifications specifications;

    protected boolean flagFirstX = false;
    protected boolean flagFirstY = false;
    protected int timeScan = 0 ;
    protected int timeScan2 = 0;
    protected int startTimeScan = 6;

    protected float dX;
    protected float dY;
    //todo
    private Hero hero = BaseGameScene.getActiveScene().getHero();

    public SimpleAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    public void restart() {
        flagFirstX = false;
        flagFirstY = false;
        dX = dY = 0;
    }

    @Override
    protected final void doInternalUpdate() {
        if (needChangeAngle){
            if (oldAngle != mRotation){
                oldAngle = mRotation;
                updateSpriteRotarion(specifications.getSpeed());
            }
        }
    }

    @Override
    public void start(WaySpecifications specifications) {
        this.specifications = specifications;
        updateSpriteRotarion(specifications.getSpeed());
    }

    protected final void prosecutionHero(int minDist,int maxDist){
        prosecutionHero(minDist, maxDist,true);
    }

    protected final void prosecutionHero(int minDist, int maxDist, boolean flag){
        prosecutionHero(minDist, maxDist, flag, specifications.getMaxRotationAndle());
    }

    protected final void prosecutionHero(int minDist, int maxDist, boolean flag, float maxAngle){

        float dist = Utils.distance(mX,mY,hero.getPosition().x,hero.getPosition().y);
        if (flag){
            timeScan2-- ;
            if(timeScan2<0 ){
                if ( 100 < dist){
                    dX =  Utils.random(-40f,40f);
                    dY =  Utils.random(-40f,40f);
                } else {
                    dX = dY = 0;
                }
                timeScan2 = startTimeScan*10;
            }
        }else {
            dX = dY = 0;
        }

        if (minDist <= dist && dist <= maxDist){
            timeScan--;
            if (timeScan<0){
                float angle =  Utils.getAngle(mX,mY,hero.getPosition().x+dX,hero.getPosition().y+dY);
                angle = Utils.dAngleDegree(angle,mRotation);
                angle = Utils.equals(0,angle,maxAngle)?
                        angle: maxAngle*Utils.getSign(angle) ;
                mRotation += angle;
                timeScan = startTimeScan;
            }
        }
    }
    /* if (! Utils.equals(mRotation, 180 + rotateAngle, 0.1f)){
            mRotation -= Utils.dAngleDegree(mRotation,180+ rotateAngle)/10f;
        }else{
            mRotation = 180+ rotateAngle;
            rotateAngle*=-1;
        }*/
    protected final void flyThroughX(){
        if (this.mX < -this.getWidth() ){
            if (flagFirstX){
                mX = GameActivity.getCameraWidth();
            }
        }else if (this.mX > GameActivity.getCameraWidth()){
            if (flagFirstX){
                mX = -this.getWidth();
            }
        }else{
            flagFirstX = true;
        }
    }

    protected final void flyThroughY(){
        if (this.mY < -this.getHeight()  ){
            if (flagFirstY){
                mY = GameActivity.getCameraHeight();
            }
        } else if (this.mY > GameActivity.getCameraHeight()){
            if (flagFirstY){
                mY = -this.getHeight();
            }
        } else{
            flagFirstY = true;
        }
    }

    private float reflectionCoef = 1.3f;

    protected final void reflectionFromX(){
        if (this.mX < -this.getWidth()*reflectionCoef ){
            if (flagFirstX){
                mRotation = -Utils.dAngleDegree(mRotation, 180);
                flagFirstX = false;
            }
        }else if (this.mX > GameActivity.getCameraWidth()+this.getWidth()*(reflectionCoef-1f)){
            if (flagFirstX){
                mRotation = 180 - Utils.dAngleDegree(mRotation, 0);
                flagFirstX = false;
            }
        }else{
            flagFirstX = true;
        }
    }

    protected final void reflectionFromY(){
        if (this.mY < -this.getHeight()*reflectionCoef  ){
            if (flagFirstY){
                mRotation = 90 - Utils.dAngleDegree(mRotation, 270);
                flagFirstY = false;
            }
        } else if (this.mY > GameActivity.getCameraHeight()+this.getHeight()*(reflectionCoef-1f)){
            if (flagFirstY){
                mRotation = 270 - Utils.dAngleDegree(mRotation, 90);
                flagFirstY = false;
            }
        }else{
            flagFirstY = true;
        }
    }

    protected final void turnonY(){
        if (this.mY < -this.getHeight()/2 || this.mY > GameActivity.getCameraHeight()-this.getHeight()/2 ){
            if (flagFirstY){
                prosecutionHero(0,99999,true,12f);
            }
        }else{
            flagFirstY = true;
        }
    }

    protected final void turnonX(){
        if (this.mX < -this.getWidth()/2|| this.mX > GameActivity.getCameraWidth() -this.getWidth()/2 ){
            if (flagFirstX){
                prosecutionHero(0,99999,true,12f);
            }
        }else{
            flagFirstX = true;
        }
    }

}
