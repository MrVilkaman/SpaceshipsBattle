package donnu.zolotarev.SpaceShip.Scenes;

import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.GameData.UserDataProcessor;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.LevelInfo;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.SpaceShipActivity;
import donnu.zolotarev.SpaceShip.Textures.TextureLoader;
import donnu.zolotarev.SpaceShip.Utils.Constants;
import donnu.zolotarev.SpaceShip.Utils.HALIGMENT;
import donnu.zolotarev.SpaceShip.Utils.MenuFactory;
import donnu.zolotarev.SpaceShip.Utils.WALIGMENT;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import java.util.Iterator;

public class SelectionLevelScene extends MyScene implements IParentScene {
    private final SpaceShipActivity shipActivity;
    private final Engine engine;
    private final IParentScene parentScene;
    private final SelectionLevelScene self;
    private final UserDataProcessor dataProcessor;

    private MyScene  gameScene;
    private MenuScene menuFactory;
    private int activeLevel;
    private LevelController levels;
    private int lastSceneId;

    private Text goldBar;
    private ISimpleClick shopListner;

    public SelectionLevelScene(IParentScene parentScene) {
        super(parentScene);
        self = this;
        this.parentScene = parentScene;
        shipActivity = SpaceShipActivity.getInstance();
       engine = shipActivity.getEngine();
       setBackground(new Background(Color.WHITE));

       initLevels();
        dataProcessor = UserDataProcessor.get();
       initUI();
    }

    private void initLevels() {
        loadGame();
        levels = loadLevels();
    }

    private void initUI() {
        shopListner = new ISimpleClick() {
            @Override
            public void onClick(int id) {
                gameScene =  new ShopScene(SelectionLevelScene.this,SelectionLevelScene.this);
            }
        };

        createGoldBar();
        int x = Constants.CAMERA_WIDTH_HALF;
        int y = 0;
        menuFactory = MenuFactory.createMenu(engine, shipActivity.getCamera())
                .addedItem(TextureLoader.getChangeLevelLableTextureRegion1(), Constants.CAMERA_WIDTH_HALF,5, WALIGMENT.CENTER, HALIGMENT.TOP )
                .addedItem(TextureLoader.getMenuBackToMainMenuTextureRegion(), new ISimpleClick() {
                    @Override
                    public void onClick(int id) {
                        parentScene.returnToParentScene(- 1);
                    }
                }, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT, WALIGMENT.RIGHT, HALIGMENT.BOTTOM)
                .addedItem(TextureLoader.getChangeLevelIconShop(),shopListner,Constants.CAMERA_WIDTH -Constants.CAMERA_WIDTH_HALF/2,0, WALIGMENT.CENTER, HALIGMENT.TOP)
                .enableAnimation().build();


        setChildScene(menuFactory);
        redrawLevelsUI();


    }

    private void redrawLevelsUI() {
        menuFactory.clearChildScene();
        MenuFactory qq = MenuFactory.createMenu(engine, shipActivity.getCamera());
        Iterator<LevelInfo> iter = levels.getIterator();
        while(iter.hasNext()){
            final LevelInfo item = iter.next();
            String name;
            Color color;
            if(item.getLevelId() == WaveContainer.LEVEL_INFINITY){
                color = Color.BLACK;
                item.setEnabled(true);
                item.setWin(true);
                name = "Inf";
            }else if(item.getLevelId() == WaveContainer.LEVEL_TEST){
                color = Color.BLACK;
                item.setEnabled(true);
                item.setWin(true);
                name = "TEST";
            } else {
                name = String.valueOf(item.getLevelId());
                if (item.isEnabled()){
                    if (item.isNew()){
                        color = Color.BLACK;
                        // name = "N";
                    }else {
                        if (item.isWin()){
                            color = Color.GREEN;
                            //  name = "X";
                        } else {
                            color = Color.RED;
                            //  name = "O";
                        }
                    }
                } else {
                    color = new Color(184/255f,  183/255f,  183/255f, 1);
                }
            }
            ISimpleClick click = null;
            if (item.isEnabled()){
                click = new ISimpleClick() {
                    @Override
                    public void onClick(int id) {
                        createGameScene(item.getLevelId());
                        lastSceneId = id;
                    }
                };
            }
            qq.addedText(name, TextureLoader.getFontBig(), click, color, item.getX(), item.getY());
        }
        qq.enableAnimation().build();
        menuFactory.setChildScene(qq.build());

        updateInfo();

        saveLevels(levels);
        saveGameState();
    }


    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
        if (gameScene != null){
            gameScene.onKeyPressed(keyCode, event);
        }else{
            if(keyCode == KeyEvent.KEYCODE_BACK ){
                parentScene.returnToParentScene(- 1);
            }
        }
    }

    private void createGameScene(int type) {
        if (gameScene != null){
            deactive();
        }
        switch (type){
            case WaveContainer.LEVEL_INFINITY:
                gameScene = new InfinityGameScene(this);
                break;
            case WaveContainer.LEVEL_TEST:
                gameScene = new TestGameScene(this);
                ((TestGameScene)gameScene).addNewWaveController( WaveContainer.getWaveControllerById(type,(TestGameScene)gameScene));

                break;
            default:
                gameScene = new MaketGameScene(this);
                ((MaketGameScene)gameScene).addNewWaveController( WaveContainer.getWaveControllerById(type,(MaketGameScene)gameScene));
                break;
        }
        activeLevel = type;
        setChildScene(gameScene,false,true,true);
    }

    @Override
    public void returnToParentScene(int statusCode) {
        deactive();
        processResault(statusCode);
        redrawLevelsUI();
        setChildScene(menuFactory, false, true, true);
    }

    private void processResault(int statusCode) {
       switch (statusCode){
           case IParentScene.EXIT_USER:
               break;
           case IParentScene.EXIT_DIE:
               levels.changeStateById(lastSceneId,false);
               dataProcessor.processGold(levels.newestById(lastSceneId),false);
              break;
           case IParentScene.EXIT_WIN:
               levels.changeStateById(lastSceneId,true);
               levels.changeEnabled();
               dataProcessor.processGold(levels.newestById(lastSceneId),true);
               break;
       }
    }

    private void deactive(){
        detachChild(gameScene);
        gameScene.clearTouchAreas();
        gameScene.clearEntityModifiers();
        gameScene.clearUpdateHandlers();
        gameScene.back();
        gameScene = null;
    }

    @Override
    public void restart() {
        createGameScene(activeLevel);
    }

    @Override
    public void callback(int statusCode) {
        switch (statusCode){
            case ShopScene.CALLBACK_UPDATE_MONEY:
                updateInfo();
                break;
        }
    }

    private void createGoldBar(){
        try {
            int y = 12;
            int x = SpaceShipActivity.getCameraWidth()-5;
            goldBar = new Text(x,y,TextureLoader.getFont(),"000000",new TextOptions(HorizontalAlign.LEFT),engine.getVertexBufferObjectManager());
            x = SpaceShipActivity.getCameraWidth() - (int)goldBar.getWidth();
            goldBar.setPosition(x,y);
            attachChild(goldBar);
            Text text = new Text(x,y,TextureLoader.getFont(),"Деньги: ",new TextOptions(HorizontalAlign.LEFT),engine.getVertexBufferObjectManager());
            attachChild(text);
            x -= (text.getWidth()+10);
            text.setPosition(x,y);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateInfo(){
        UserData userData = UserData.get();
        goldBar.setText(String.valueOf(userData.getMoney()));
    }
}
