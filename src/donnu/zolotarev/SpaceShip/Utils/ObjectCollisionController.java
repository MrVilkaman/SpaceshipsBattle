package donnu.zolotarev.SpaceShip.Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class ObjectCollisionController<E extends ICollisionObject> {

    private ArrayList<E> objects;
    private ArrayList<E> iShapes;
    private Iterator<E> it;

    public ObjectCollisionController() {
       objects = new ArrayList<E>();
       iShapes = new ArrayList<E>();
    }

    public Iterator<E> getObjects() {
        return objects.iterator();
    }

    public ArrayList<E> get() {
        return objects;
    }

    public void add(E object){
        objects.add(object);
    }

    public  void remove(E object){
        objects.remove(object);
    }

    public  void cleer(){
        for (int i = objects.size()-1; 0<=i;i--) {
            objects.get(i).destroy(false);
        }
        objects.clear();
        objects = null;
        iShapes = null;
    }

    public ArrayList<E> haveCollision(IHaveCoords object){
        iShapes.clear();
    //     it = objects.iterator();
        ;
       for (int i = objects.size()-1; 0<=i;i--){
           E it =  objects.get(i);
            if(it.checkHit(object)&& !it.getShape().isIgnoreUpdate() ){
                iShapes.add(it);
                break;
            }
        }
        return iShapes;
    }
}
