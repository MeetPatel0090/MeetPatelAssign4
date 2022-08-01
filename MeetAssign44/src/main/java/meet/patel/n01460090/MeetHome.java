// Meet Patel N01460090 Section:- RNB

package meet.patel.n01460090;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MeetHome extends Fragment {

    TextView textView, readTextView;
    TextClock textClock;
    Button writeBtn, readBtn;
    EditText editText;
    ConstraintLayout constraintLayout;
    SharedPreferences sharedPreferences;
    String name = "Meet Patel";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.meet_home, container, false);
        sharedPreferences = getActivity().getSharedPreferences("Name", Context.MODE_PRIVATE);

        textClock = (TextClock) view.findViewById(R.id.MeetTextClock);

        getParentFragmentManager().setFragmentResultListener(getString(R.string.bundle_time_key), this, ((requestKey, result) -> {
            String time = result.getString(getString(R.string.bundle_time_put_string));
            switch (time){
                case "12hr":
                    textClock = (TextClock) view.findViewById(R.id.MeetTextClock);
                    textClock.setFormat24Hour(null);
                    textClock.setFormat12Hour("hh:mm:ss a");
                    break;
                case "24hr":
                    textClock = (TextClock) view.findViewById(R.id.MeetTextClock);
                    textClock.setFormat12Hour(null);
                    textClock.setFormat24Hour("kk:mm:ss");
                    break;
            }
        }));

        constraintLayout = view.findViewById(R.id.MeetHomeFrag);
        getParentFragmentManager().setFragmentResultListener(getString(R.string.bundle_background_key), this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String homeBackground = result.getString(getString(R.string.bundle_background_put_string));
                switch (homeBackground){
                    case "Pink":
                        constraintLayout.setBackgroundColor(Color.parseColor("#fab4f4"));
                        break;
                    case "Blue":
                        constraintLayout.setBackgroundColor(Color.parseColor("#b4e7fa"));
                        break;
                    case "White":
                        constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        break;
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = view.findViewById(R.id.MeetHomeDate);
        textViewDate.setText(currentDate);

        writeBtn = view.findViewById(R.id.MeetHomeEnterBtn);
        editText = view.findViewById(R.id.MeetHomeEditText);


        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                writeToFile(getString(R.string.home_file_name),content);
                editText.setText("");
            }
        });

        return view;
    }


    public void writeToFile(String file, String content){
        File path = getActivity().getApplicationContext().getFilesDir();
        try{
            FileOutputStream write = new FileOutputStream(new File(path, file), true);
            write.write(content.getBytes());
            write.close();
            Toast.makeText(getActivity().getApplicationContext(),getString(R.string.home_frag_toast_text) + file, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name",name);
        editor.commit();
    }
}