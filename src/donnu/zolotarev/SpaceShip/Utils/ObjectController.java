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
       // object.destroy();
        objects.remove(object);
    }

    public synchronized void cleer(){
        Iterator<E> it = objects.iterator();
        while (it.hasNext()){
            E obj = it.next();
            obj.destroy();
        }
    }

    public Iterator<E> haveCollision(ICollisionObject2 object){
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

    private boolean haveCollision(E shape, E object) {
        //return shape.getShape().collidesWith(object.getShape()); // todo Старая реализация

        return  false;
    }


}
