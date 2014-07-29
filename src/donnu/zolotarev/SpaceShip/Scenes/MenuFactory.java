package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;

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

    public MenuFactory addedText(String text, IFont iFont){
        return addedText(text, iFont, 1);
    }

    public MenuFactory addedText(String text, IFont iFont, int scale){
        TextMenuItem textMenuItem = new TextMenuItem(-1,iFont, text,new TextOptions(HorizontalAlign.CENTER),engine.getVertexBufferObjectManager());
        textMenuItem.setScale(scale);
        textMenuItem.setIgnoreUpdate(true);
        textMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        menuScene.addMenuItem(textMenuItem);
        return this;
    }

    public MenuFactory addedText(String text, IFont iFont, int scale, float x, float y){
        TextMenuItem textMenuItem = new TextMenuItem(-1,iFont, text
                ,engine.getVertexBufferObjectManager());

        textMenuItem.setHorizontalAlign(HorizontalAlign.CENTER);
        textMenuItem.setIgnoreUpdate(true);
        textMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        textMenuItem.setPosition(x - textMenuItem.getWidth()/2,y);
        textMenuItem.setScale(scale);
        menuScene.attachChild(textMenuItem);
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
