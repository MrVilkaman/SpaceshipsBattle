package donnu.zolotarev.SpaceShip.Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class ObjectController<E extends ICollisionObject> {

    private ArrayList<E> objects;
    private ArrayList<E> iShapes;

    public ObjectController() {
       objects = new ArrayList<E>();
       iShapes = new ArrayList<E>();
    }

    public Iterator<E> getObjects() {
        return objects.iterator();
    }

    public void add(E object){
        objects.add(object);
    }

    public synchronized void remove(E object){
        objects.remove(object);
    }

    public synchronized void cleer(){
        iShapes =  (ArrayList<E>)objects.clone();
        for( E e :iShapes){
            e.destroy();
        }
        iShapes.clear();
    }

    public Iterator<E> haveCollision(IHaveCoords object){
        iShapes.clear();
        Iterator<E> it = objects.iterator();
        while (it.hasNext()){
            E shape = it.next();
            if(shape.checkHit(object)&& !shape.getShape().isIgnoreUpdate() ){
                iShapes.add(shape);
                break;
            }
        }
        return iShapes.iterator();
    }
}
