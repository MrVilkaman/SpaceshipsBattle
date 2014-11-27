package donnu.zolotarev.SpaceShip.Scenes;

import android.view.KeyEvent;
import donnu.zolotarev.SpaceShip.GameState.IParentScene;
import donnu.zolotarev.SpaceShip.Levels.LevelController;
import donnu.zolotarev.SpaceShip.Levels.WaveContainer;
import org.andengine.entity.scene.background.Background;
import org.andengine.util.color.Color;

public class SelectionLevelScene extends MyScene implements IParentScene {
    private final IParentScene parentScene;

    private MyScene  gameScene;
    private int activeLevel;
    private LevelController levels;
    private int lastSceneId;

    public SelectionLevelScene(IParentScene parentScene,int level) {
        super(parentScene);
        this.parentScene = parentScene;
        setBackground(new Background(Color.WHITE));
        initLevels();
        lastSceneId = level;
        createGameScene(lastSceneId);
    }

    private void initLevels() {
     levels = LevelController.getInstance();
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

    @Override
    public void onResume() {
        if (gameScene != null){
            gameScene.onResume();
        }

    }

    @Override
    public void onPause() {
        if (gameScene != null){
            gameScene.onPause();
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
            case WaveContainer.LEVEL_MUSEUM:
                gameScene = new MuseumScene(this);
                ((MuseumScene)gameScene).addNewWaveController( WaveContainer.getWaveControllerById(type,(MuseumScene)gameScene));

                break;
            default:
                gameScene = new MaketGameScene(this,levels.getById(lastSceneId));
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
        parentScene.returnToParentScene(statusCode);
    }

    private void processResault(int statusCode) {
       switch (statusCode){
           case IParentScene.EXIT_USER:
               levels.getById(lastSceneId).addTotalRestart();
               break;
           case IParentScene.EXIT_DIE:
               levels.changeStateById(lastSceneId,false);
               levels.getById(lastSceneId).addTotalLose();
//               dataProcessor.processGold(levels.newestById(lastSceneId),false);
              break;
           case IParentScene.EXIT_WIN:
               levels.changeStateById(lastSceneId,true);
               levels.getById(lastSceneId).addTotalWin();
               levels.changeEnabled();
//               dataProcessor.processGold(levels.newestById(lastSceneId),true);
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
    public void restart(int statusCode) {
        processResault(statusCode);
        createGameScene(activeLevel);
    }


    @Override
    public void onDetached() {
        super.onDetached();
        levels.clear();
    }
}
