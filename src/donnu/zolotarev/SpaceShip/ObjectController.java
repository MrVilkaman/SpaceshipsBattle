package donnu.zolotarev.SpaceShip;

import java.util.HashSet;
import java.util.Iterator;

public class ObjectController<E extends ICollisionObject> {

    private HashSet<E> objects;
    private HashSet<E> iShapes;

    public ObjectController() {
       objects = new HashSet<E>();
       iShapes = new HashSet<E>();
    }

    public Iterator<E> getObjects() {
        return objects.iterator();
    }

    public synchronized void add(E object){
        objects.add(object);
    }

    public synchronized void remove(E object){
        objects.remove(object);
    }

    public synchronized void cleer(){
        Iterator<E> it = objects.iterator();
        while (it.hasNext()){
           it.remove();
        }
    }

    public Iterator<E> haveCollision(E object){
        iShapes.clear();

        Iterator<E> it = objects.iterator();
        while (it.hasNext()){
            E shape = it.next();
            if(shape.getShape().collidesWith(object.getShape()) && !shape.getShape().isIgnoreUpdate() ){
                iShapes.add(shape);
                break;
            }
        }

        return iShapes.iterator();
    }




}
