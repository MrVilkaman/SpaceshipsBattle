package donnu.zolotarev.SpaceShip.AI.BulletAI;

import android.graphics.PointF;
import donnu.zolotarev.SpaceShip.Scenes.InfinityGameScene;
import donnu.zolotarev.SpaceShip.Units.BaseUnit;
import donnu.zolotarev.SpaceShip.Utils.Utils;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Iterator;

public class AutoguiderRocketAI extends BulletBaseAI {
    private boolean autoguider = false;
    protected boolean targetUnit = false;

    class Info{
        public PointF point;
        public float dist;

        public Info(PointF pos, float dist) {
            this.point = pos;
            this.dist = dist;
        }
    }

    public AutoguiderRocketAI(ITiledTextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void destroyed() {

    }

    @Override
    protected void doUpdate() {
        if (autoguider){
            if(!targetUnit){
                prosecutionHero(0,1000,false);
            }else{
                prosecutionEnemy(0, 1000);
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
            float angle =  Utils.getAngle(mX,mY,pos.x,pos.y,false);
            if(dist < mDist){
                boolean f = Utils.inRadius(Utils.dAngleDegree(angle,mRotation), 45f);
                if (f){
                    mDist = dist;
                    mPos = pos;
                } else {
                    int r = 0;
                    r= 7;
                }
            }
        }
        return new Info(mPos,mDist);
    }
}
