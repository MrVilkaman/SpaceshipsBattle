package donnu.zolotarev.SpaceShip.Scenes;

import android.opengl.GLES20;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.Background;
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
    private final SpaceShipActivity shipActivity;

    private HashMap<Integer,ISimpleClick> clicks;
    private Integer clickCounter;
    private int lostIndex = -1;



    public static enum WALIGMENT{
        LEFT,CENTER,RIGHT
    }
    public static enum HALIGMENT{
        TOP,CENTER,BOTTOM
    }

    public static MenuFactory createMenu(){

        return new MenuFactory();
    }

    private MenuFactory() {
        shipActivity = SpaceShipActivity.getInstance();
        clickCounter = 0;
        this.menuScene = new MenuScene(shipActivity.getCamera());
        this.engine = shipActivity.getEngine();
        clicks = new HashMap<Integer, ISimpleClick>();
        menuScene.setBackground(new Background(Color.RED));
    }

    // Картинка с нажатием
    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick){
        reqFromClick(simpleClick);
        return addMenuItem(createSpriteItem(texture));
    }

    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick,int scale ){
        reqFromClick(simpleClick);
        return addMenuItem(createSpriteItem(texture,scale));
    }

    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick,int scale, float x, float y,WALIGMENT waligment, HALIGMENT haligment ){
       reqFromClick(simpleClick);
        return attachChild(createSpriteItem(texture,scale,x,y,waligment,haligment));
    }
    public MenuFactory addedItem(ITextureRegion texture, ISimpleClick simpleClick,int scale, float x, float y){
        reqFromClick(simpleClick);
        return attachChild(createSpriteItem(texture,scale,x,y));
    }

    // Просто картинка
    public MenuFactory addedItem(ITextureRegion texture){
        return addMenuItem(createSpriteItem(texture));
    }

    public MenuFactory addedItem(ITextureRegion texture,int scale ){
        return addMenuItem(createSpriteItem(texture,scale));
    }

    public MenuFactory addedItem(ITextureRegion texture,int scale, float x, float y ){
        return attachChild(createSpriteItem(texture,scale,x,y));
    }

    public MenuFactory addedItem(ITextureRegion texture,int scale, float x, float y,WALIGMENT waligment, HALIGMENT haligment ) {
        return attachChild(createSpriteItem(texture,scale,x,y,waligment,haligment));
    }

    // Текст с нажатием
    public MenuFactory addedText(String text, IFont iFont,ISimpleClick simpleClick){
        reqFromClick(simpleClick);
        return addMenuItem(createText(text, iFont));
    }

    public MenuFactory addedText(String text, IFont iFont,ISimpleClick simpleClick, int scale){
        reqFromClick(simpleClick);
        return addMenuItem(createText(text, iFont, scale));
    }

    public MenuFactory addedText(String text, IFont iFont,ISimpleClick simpleClick, int scale, float x, float y){
        reqFromClick(simpleClick);
        return attachChild(createText(text, iFont,scale,x,y));
    }

    // Текст без нажатиея
    public MenuFactory addedText(String text, IFont iFont){
        return addMenuItem(createText(text, iFont));
    }

    public MenuFactory addedText(String text, IFont iFont, int scale){
        return addMenuItem(createText(text, iFont, scale));
    }

    public MenuFactory addedText(String text, IFont iFont, int scale, float x, float y){
        return attachChild(createText(text, iFont,scale,x,y));
    }

    private IMenuItem createSpriteItem(ITextureRegion texture){
        SpriteMenuItem resetMenuItem = new SpriteMenuItem(lostIndex, texture,
                engine.getVertexBufferObjectManager());
        resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        return resetMenuItem;
    }

    private IMenuItem createSpriteItem(ITextureRegion texture, int scale){
        IMenuItem resetMenuItem = createSpriteItem(texture);
        resetMenuItem.setScale(scale);
        return resetMenuItem;
    }

    private IMenuItem createSpriteItem(ITextureRegion texture, int scale,float x, float y){
        IMenuItem resetMenuItem = createSpriteItem(texture,scale);
        resetMenuItem.setPosition(x,y);
        return resetMenuItem;
    }

    private IMenuItem createSpriteItem(ITextureRegion texture, int scale,float x, float y,WALIGMENT waligment, HALIGMENT haligment ){
        IMenuItem resetMenuItem = createSpriteItem(texture,scale);
        int dx = 0;
        int dy = 0;
        switch (waligment){
            case LEFT:
                dx = 0;
                break;
            case CENTER:
                dx = (int)resetMenuItem.getWidth()/2;
                break;
            case RIGHT:
                dx = (int)resetMenuItem.getWidth();
                break;
        }

        switch (haligment){
            case TOP:
                dy = 0;
                break;
            case CENTER:
                dy = (int)resetMenuItem.getHeight()/2;
                break;
            case BOTTOM:
                dy = (int)resetMenuItem.getHeight();
                break;
        }
        resetMenuItem.setPosition(x - dx,y - dy);
        return resetMenuItem;
    }

    private IMenuItem createText(String text, IFont iFont){
        TextMenuItem textMenuItem = new TextMenuItem(lostIndex,iFont, text,engine.getVertexBufferObjectManager());
        textMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        return textMenuItem;
    }

    private IMenuItem createText(String text, IFont iFont,int scale){
        IMenuItem textMenuItem = createText(text, iFont);
        textMenuItem.setScale(scale);
        return textMenuItem;
    }

    private IMenuItem createText(String text, IFont iFont,int scale,float x, float y){
        IMenuItem textMenuItem = createText(text, iFont,scale);
        textMenuItem.setPosition(x,y);
        return textMenuItem;
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
                    d.onClick();
                    return true;
                }
                return false;
            }
        });
        return menuScene;
    }

}
