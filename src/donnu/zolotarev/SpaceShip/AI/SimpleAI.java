package donnu.zolotarev.SpaceShip.AI;

import donnu.zolotarev.SpaceShip.Scenes.BaseGameScene;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Units.Hero;
import donnu.zolotarev.SpaceShip.Units.WaySpecifications;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class SimpleAI extends SpriteAI {

    protected WaySpecifications specifications;

    boolean flagFirstX = false;
    boolean flagFirstY = false;
    private int timeScan = 0 ;
    private int timeScan2 = 0;
    private int startTimeScan = 5;

    private float dX;
    private float dY;
    //todo
    private Hero hero = BaseGameScene.getActiveScene().getHero();

    public SimpleAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
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
        updateSpriteRotarion(specifications.getSpeed());
    }

    @Override
    public void start(WaySpecifications specifications) {
        this.specifications = specifications;
    }

    protected final void prosecutionHero(int minDist,int maxDist){
        timeScan2-- ;
        float dist = Utils.distance(mX,mY,hero.getPosition().x,hero.getPosition().y);
        if(timeScan2<0 ){
            //todo заменить коэфициенты
            if ( 100 < dist){
                dX =  Utils.random(-50f,50f);
                dY =  Utils.random(-50f,50f);
            } else {
                dX = dY = 0;
            }
            timeScan2 = startTimeScan*10;
        }

        if (minDist <= dist && dist <= maxDist){
            timeScan--;
            if (timeScan<0){
                float angle =  Utils.getAngle(mX+dX,mY+dY,hero.getPosition().x,hero.getPosition().y);
                angle = Utils.dAngleDegree(angle,mRotation);
                angle = Utils.equals(0,angle,specifications.getMaxRotationAndle())?
                        angle: specifications.getMaxRotationAndle()*Utils.getSign(angle) ;
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
                mX = SpaceShipActivity.getCameraWidth();
            }
        }else if (this.mX > SpaceShipActivity.getCameraWidth()){
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
                mY = SpaceShipActivity.getCameraHeight();
            }
        } else if (this.mY > SpaceShipActivity.getCameraHeight()){
            if (flagFirstY){
                mY = -this.getHeight();
            }
        } else{
            flagFirstY = true;
        }
    }

    protected final void reflectionFromX(){
        if (this.mX < -this.getWidth() ){
            if (flagFirstX){
                mRotation = -Utils.dAngleDegree(mRotation, 180);
            }
        }else if (this.mX > SpaceShipActivity.getCameraWidth()){
            if (flagFirstX){
                mRotation = 180 - Utils.dAngleDegree(mRotation, 0);
            }
        }else{
            flagFirstX = true;
        }
    }

    protected final void reflectionFromY(){
        if (this.mY < -this.getHeight()  ){
            if (flagFirstY){
                mRotation = 90 - Utils.dAngleDegree(mRotation, 270);
            }
        } else if (this.mY > SpaceShipActivity.getCameraHeight()){
            if (flagFirstY){
                mRotation = 270 - Utils.dAngleDegree(mRotation, 90);
            }
        }else{
            flagFirstY = true;
        }
    }

    protected final void turnonY(){
        if (this.mY < -this.getHeight()/2 || this.mY > SpaceShipActivity.getCameraHeight()-this.getHeight()/2 ){
            if (flagFirstY){
                prosecutionHero(0,1500);
            }
        }else{
            flagFirstY = true;
        }
    }

    protected final void turnonX(){
        if (this.mX < -this.getWidth()/2|| this.mX > SpaceShipActivity.getCameraWidth() -this.getWidth()/2 ){
            if (flagFirstX){
                prosecutionHero(0,1500);
            }
        }else{
            flagFirstX = true;
        }
    }

}
