package meet.patel.n01460090;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsScreen extends Fragment {

    RadioGroup radioGroupTime, radioGroupHome;
    String setTime, setHomeBackground;
    private Bundle bundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings_screen, container, false);

        radioGroupTime = view.findViewById(R.id.MeetTimeRadioGroup);
        radioGroupTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.Meet12HrRadioBtn:
                        setTime = "12hr";
                        bundle.putString(getString(R.string.bundle_time_put_string),setTime);
                        getParentFragmentManager().setFragmentResult(getString(R.string.bundle_time_key),bundle);
                        break;
                    case R.id.Meet24HrRadioBtn:
                        setTime = "24hr";
                        bundle.putString(getString(R.string.bundle_time_put_string),setTime);
                        getParentFragmentManager().setFragmentResult(getString(R.string.bundle_time_key),bundle);
                }
            }
        });


        radioGroupHome = (RadioGroup) view.findViewById(R.id.MeetHomeBackgroundColorSettingsRadioGroup);
        radioGroupHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.MeetPink:
                        setHomeBackground = "Pink";
                        bundle.putString(getString(R.string.bundle_background_put_string), setHomeBackground);
                        getParentFragmentManager().setFragmentResult(getString(R.string.bundle_background_key), bundle);
                        break;
                    case R.id.MeetBlue:
                        setHomeBackground = "Blue";
                        bundle.putString(getString(R.string.bundle_background_put_string),setHomeBackground);
                        getParentFragmentManager().setFragmentResult(getString(R.string.bundle_background_key), bundle);
                        break;
                    case R.id.MeetWhite:
                        setHomeBackground = "White";
                        bundle.putString(getString(R.string.bundle_background_put_string),setHomeBackground);
                        getParentFragmentManager().setFragmentResult(getString(R.string.bundle_background_key), bundle);
                        break;
                }
            }
        });

        return view;
    }


}