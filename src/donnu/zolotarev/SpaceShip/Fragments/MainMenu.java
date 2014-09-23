package donnu.zolotarev.SpaceShip.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.InjectView;
import butterknife.OnClick;
import donnu.zolotarev.SpaceShip.GameActivity;
import donnu.zolotarev.SpaceShip.R;

public class MainMenu extends BaseFragment {

    @InjectView(R.id.textView)
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragmentView(R.layout.fragment_main_menu, inflater, container);
        int fd = 5;
        fd = 4;
        return view;
    }

    @OnClick(R.id.textView)
    public void clikc(){
        getActivity().startActivity(new Intent(getActivity(),GameActivity.class));
    }
}
