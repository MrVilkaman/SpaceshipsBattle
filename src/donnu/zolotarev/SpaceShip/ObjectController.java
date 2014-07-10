package donnu.zolotarev.SpaceShip;

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

    public synchronized void add(E object){
        objects.add(object);
    }

    public synchronized void remove(E object){
        object.destroy();
        objects.remove(object);
    }

    public synchronized void cleer(){
        Iterator<E> it = objects.iterator();
        while (it.hasNext()){
            E obj = it.next();
            obj.destroy();
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
