package donnu.zolotarev.SpaceShip.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.HorizontalListView;
import donnu.zolotarev.SpaceShip.LevelsAdapter;
import donnu.zolotarev.SpaceShip.R;

public class SelectLevelFragment extends BaseFragment {

    @InjectView(R.id.select_levels_list_view)
    HorizontalListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_select_levels,inflater,container);

        listView.setAdapter(new LevelsAdapter(getActivity()));
        return view;
    }

    @OnClick(R.id.select_levels_back)
    public void onBack(){
       back();
    }
}
