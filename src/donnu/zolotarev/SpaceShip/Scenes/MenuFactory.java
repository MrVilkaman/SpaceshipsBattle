package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.HashMap;

public  class MenuFactory {
    private final Engine engine;
    private final MenuScene menuScene;
    private final SpaceShipActivity shipActivity;

    private HashMap<Integer,ISimpleClick> clicks;
    private Integer clickCounter;

    public static MenuFactory createMenu(){

        return new MenuFactory();
    }

    private MenuFactory() {
        shipActivity = SpaceShipActivity.getInstance();
        clickCounter = 0;
        this.menuScene = new MenuScene(shipActivity.getCamera());
        this.engine = shipActivity.getEngine();
        clicks = new HashMap<Integer, ISimpleClick>();
    }

    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick){
        clicks.put(clickCounter,simpleClick);
         SpriteMenuItem resetMenuItem = new SpriteMenuItem(clickCounter, texture,
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        menuScene.addMenuItem(resetMenuItem);
        clickCounter++;
        return this;
    }

    public MenuFactory enableAnimation(){
        this.menuScene.buildAnimations();
        this.menuScene.setBackgroundEnabled(false);
        return this;
    }

    public MenuScene build(){
        menuScene.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
                    float pMenuItemLocalY) {
                // todo обработать нажатие
                ISimpleClick d = clicks.get(pMenuItem.getID());
                if (d != null){
                    d.onClick();
                    return true;
                }
                return false;
            }
        });
        return menuScene;
    }

}
