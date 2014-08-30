package donnu.zolotarev.SpaceShip.AI.BulletAI;

import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Scenes.InfinityGameScene;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Iterator;

public class AutoguiderRocketAI extends BulletBaseAI {
    private boolean autoguider = false;
    private boolean targetUnit = true;

    class Info{
        public PointF point;
        public float dist;

        public Info(PointF pos, float dist) {
            this.point = pos;
            this.dist = dist;
        }
    }

    public AutoguiderRocketAI(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void destroyed() {

    }

    @Override
    protected void doUpdate() {
        if (autoguider){
            if(!targetUnit){
                prosecutionHero(0,800,false);
            }else{
                prosecutionEnemy(30, 800);
            }
        }

        destroyOutScreen();
    }

    public void setAutoguider(boolean unitTarget){
        autoguider = true;
        targetUnit = unitTarget;

    }

    public void setNoAutoguider(){
        autoguider = false;
    }

    private void prosecutionEnemy(int minDist,int maxDist){
        Info info = findNear(minDist, maxDist);

        if (minDist <= info.dist && info.dist <= maxDist){
            timeScan--;
            if (timeScan<0){
                float angle =  Utils.getAngle(mX + dX, mY + dY, info.point.x, info.point.y);
                angle = Utils.dAngleDegree(angle,mRotation);
                angle = Utils.equals(0,angle,specifications.getMaxRotationAndle())?
                        angle: specifications.getMaxRotationAndle()*Utils.getSign(angle) ;
                mRotation += angle;
                timeScan = startTimeScan;
            }
        }
    }

    private Info findNear(int minDist,int maxDist){
        float mDist = 9999;
        float dist = -1;
        PointF pos = null;
        PointF mPos = new PointF(0,0);
        Iterator<BaseUnit> col =  InfinityGameScene.getActiveScene().getEnemyController().getObjects();
        while (col.hasNext()){
            BaseUnit unit = col.next();
            pos = unit.getPosition();
           dist = Utils.distance(mX,mY,pos.x,pos.y);
            if(dist < mDist){
                mDist = dist;
                mPos = pos;
            }
        }
        return new Info(mPos,mDist);
    }
}
