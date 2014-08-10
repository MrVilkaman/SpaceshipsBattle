package donnu.zolotarev.SpaceShip.Utils;

import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.color.Color;

import java.util.HashMap;

public  class MenuFactory {
    private final Engine engine;
    private final MenuScene menuScene;
    private HashMap<Integer,ISimpleClick> clicks;
    private Integer clickCounter;
    private int lostIndex = -1;

    public static MenuFactory createMenu(Engine engine,Camera camera){

        return new MenuFactory(engine, camera);
    }

    private MenuFactory(Engine engine,Camera camera) {
        clickCounter = 0;
        this.menuScene = new MenuScene(camera);
        this.engine = engine;
        clicks = new HashMap<Integer, ISimpleClick>();
    }

    // Картинка с нажатием
    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick){
        reqFromClick(simpleClick);
        return addMenuItem(createSpriteItem(texture));
    }

    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick, float x, float y, WALIGMENT waligment, HALIGMENT haligment){
       reqFromClick(simpleClick);
        return attachChild(createSpriteItem(texture, x,y,waligment,haligment));
    }

    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick, float x, float y){
        reqFromClick(simpleClick);
        return attachChild(createSpriteItem(texture, x,y));
    }

    // Просто картинка
    public MenuFactory addedItem(ITextureRegion texture){
        return addMenuItem(createSpriteItem(texture));
    }

    public MenuFactory addedItem(ITextureRegion texture, float x, float y){
        return attachChild(createSpriteItem(texture, x,y));
    }

    public MenuFactory addedItem(ITextureRegion texture, float x, float y, WALIGMENT waligment, HALIGMENT haligment) {
        return attachChild(createSpriteItem(texture, x,y,waligment,haligment));
    }

    // Текст с нажатием
    public MenuFactory addedText(String text, IFont iFont,ISimpleClick simpleClick){
        reqFromClick(simpleClick);
        return addMenuItem(createText(text, iFont));
    }


    public MenuFactory addedText(String text, IFont iFont, ISimpleClick simpleClick, Color color, float x, float y){
        reqFromClick(simpleClick);
        return attachChild(createText(text, iFont, color, x,y));
    }

    public MenuFactory addedText(String text, IFont iFont, ISimpleClick simpleClick, float x, float y, WALIGMENT waligment, HALIGMENT haligment){
        reqFromClick(simpleClick);
        return attachChild(createText(text, iFont,x,y,waligment,haligment));
    }

    // Текст без нажатиея
    public MenuFactory addedText(String text, IFont iFont){
        return addMenuItem(createText(text, iFont));
    }

    public MenuFactory addedText(String text, IFont iFont, float x, float y){
        return attachChild(createText(text, iFont, Color.BLACK, x,y));
    }

    public MenuFactory addedText(String text, IFont iFont, float x, float y,WALIGMENT waligment, HALIGMENT haligment){
        return attachChild(createText(text, iFont,x,y,waligment,haligment));
    }

    private IMenuItem createSpriteItem(ITextureRegion texture){
        SpriteMenuItem resetMenuItem = new SpriteMenuItem(lostIndex, texture,
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        return resetMenuItem;
    }

    private IMenuItem createSpriteItem(ITextureRegion texture, float x, float y){
        IMenuItem resetMenuItem = createSpriteItem(texture);
        resetMenuItem.setPosition(x,y);
        return resetMenuItem;
    }

    private IMenuItem createSpriteItem(ITextureRegion texture, float x, float y, WALIGMENT waligment, HALIGMENT haligment){
        IMenuItem resetMenuItem = createSpriteItem(texture);
        return alihment(resetMenuItem,x,y,waligment,haligment);
    }

    private IMenuItem createText(String text, IFont iFont){
        TextMenuItem textMenuItem = new TextMenuItem(lostIndex,iFont, text,engine.getVertexBufferObjectManager());
        textMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        return textMenuItem;
    }

    private IMenuItem createText(String text, IFont iFont, Color color, float x, float y){
        IMenuItem textMenuItem = createText(text, iFont);
        textMenuItem.setPosition(x,y);
        textMenuItem.setColor(color);
        return textMenuItem;
    }

    private IMenuItem createText(String text, IFont iFont,float x, float y,WALIGMENT waligment, HALIGMENT haligment){
        IMenuItem textMenuItem = createText(text, iFont);
        return alihment(textMenuItem,x,y,waligment,haligment);
    }

    private MenuFactory addMenuItem(IMenuItem entity){
        menuScene.addMenuItem(entity);
        return this;
    }

    private MenuFactory attachChild(IMenuItem entity){
        menuScene.registerTouchArea(entity);
        menuScene.attachChild(entity);
        return this;
    }

    public MenuFactory enableAnimation(){
        this.menuScene.buildAnimations();
        this.menuScene.setBackgroundEnabled(false);
        return this;
    }

    private void reqFromClick(ISimpleClick simpleClick){
        clicks.put(clickCounter,simpleClick);
        lostIndex = clickCounter++;
    }

    public MenuScene build(){
        menuScene.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
                    float pMenuItemLocalY) {
                ISimpleClick d = clicks.get(pMenuItem.getID());
                if (d != null){
                    d.onClick(pMenuItem.getID());
                    return true;
                }
                return false;
            }
        });
        return menuScene;
    }

    private IMenuItem alihment(IMenuItem iMenuItem,float x, float y,WALIGMENT waligment, HALIGMENT haligment){
        int dx = 0;
        int dy = 0;
        switch (waligment){
            case LEFT:
                dx = 0;
                break;
            case CENTER:
                dx = (int)iMenuItem.getWidth()/2;
                break;
            case RIGHT:
                dx = (int)iMenuItem.getWidth();
                break;
        }

        switch (haligment){
            case TOP:
                dy = 0;
                break;
            case CENTER:
                dy = (int)iMenuItem.getHeight()/2;
                break;
            case BOTTOM:
                dy = (int)iMenuItem.getHeight();
                break;
        }
        iMenuItem.setPosition(x - dx,y - dy);
        return iMenuItem;
    }

}
