package donnu.zolotarev.SpaceShip.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import donnu.zolotarev.SpaceShip.R;

public class FlySettingsFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_fly_settings, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setView(v);

        return alertDialog.create();
    }
}
