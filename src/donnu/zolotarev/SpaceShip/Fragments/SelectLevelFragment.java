package donnu.zolotarev.SpaceShip.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.Activity.GameActivity;
import donnu.zolotarev.SpaceShip.Fragments.Adapter.LevelsAdapter;
import donnu.zolotarev.SpaceShip.R;
import donnu.zolotarev.SpaceShip.Scenes.Interfaces.ISimpleClick;
import donnu.zolotarev.SpaceShip.UI.HorizontalListView;

public class SelectLevelFragment extends BaseMenuFragment {

    @InjectView(R.id.select_levels_list_view)
    HorizontalListView listView;
    private LevelsAdapter levelsAdapter;
    private ISimpleClick startLevelListenet = new ISimpleClick() {
        @Override
        public void onClick(int id) {
            toast("Ghbdtn) " + id);
            startActivityForResult(new Intent(getActivity(),GameActivity.class),1);
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        loadGame();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_select_levels,inflater,container);
        levelsAdapter = new LevelsAdapter(getActivity());
        levelsAdapter.setLevels(loadLevels());
        levelsAdapter.setClickListener(startLevelListenet);
        listView.setAdapter(levelsAdapter);


        return view;
    }

    @OnClick(R.id.select_levels_back)
    public void onBack(){
       back();
    }
}
