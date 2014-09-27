package donnu.zolotarev.SpaceShip.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Fragments.Adapter.LevelsAdapter;
import donnu.zolotarev.SpaceShip.GameData.UserData;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.UI.HorizontalListView;

public class SelectLevelFragment extends BaseMenuFragment {

    @InjectView(R.id.select_levels_money_val)
    TextView gold;

    @InjectView(R.id.select_levels_list_view)
    HorizontalListView listView;

    private LevelsAdapter levelsAdapter;
    private ISimpleClick startLevelListenet = new ISimpleClick() {
        @Override
        public void onClick(int id) {
            startActivityForResult(new Intent(getActivity(),GameActivity.class),1);
        }
    };
    private UserData userData;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_select_levels,inflater,container);
        levelsAdapter = new LevelsAdapter(getActivity());
        levelsAdapter.setClickListener(startLevelListenet);
        listView.setAdapter(levelsAdapter);

        loadGame();
        userData = UserData.get();

        return view;
    }

    @Override
    public void onResume() {
       super.onResume();
       gold.setText(String.valueOf(userData.getMoney()));
    }


    @OnClick(R.id.select_levels_back)
    public void onBack(){
       back();
    }

    @OnClick(R.id.select_levels_shop)
    public void onShop(){
        showFragment(new ShopFragment(),true);
    }
    /*
    *  private void processResault(int statusCode) {
       switch (statusCode){
           case IParentScene.EXIT_USER:
               break;
           case IParentScene.EXIT_DIE:
               levels.changeStateById(lastSceneId,false);
//               dataProcessor.processGold(levels.newestById(lastSceneId),false);
              break;
           case IParentScene.EXIT_WIN:
               levels.changeStateById(lastSceneId,true);
               levels.changeEnabled();
//               dataProcessor.processGold(levels.newestById(lastSceneId),true);
               break;
       }
    }
*/
}
