package meet.patel.n01460090;

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

    TextView textView, readFileTextView;
    TextClock textClock;
    Button writeBtn, readBtn;
    EditText editText;
    ConstraintLayout constraintLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.meet_home, container, false);

        textClock = (TextClock) view.findViewById(R.id.MeetTextClock);
        textClock.setText(getCurrentTime());

        getParentFragmentManager().setFragmentResultListener("sentTime", this, ((requestKey, result) -> {
            String timeIn = result.getString("setTime");
            switch (timeIn){
                case "12hr":
                    textClock = (TextClock) view.findViewById(R.id.MeetTextClock);
                    textClock.setFormat12Hour("hh:mm a");
                    break;
                case "24hr":
                    textClock = (TextClock) view.findViewById(R.id.MeetTextClock);
                    textClock.setFormat24Hour("kk:mm a");
                    break;
            }
        }));

        constraintLayout = view.findViewById(R.id.MeetHomeFrag);
        getParentFragmentManager().setFragmentResultListener("sentHomeBg", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String homeIn = result.getString("setHomeBg");
                switch (homeIn){
                    case "Pink":
                        constraintLayout.setBackgroundColor(Color.parseColor("#fab4f4"));
                        break;
                    case "white":
                        constraintLayout.setBackgroundColor(Color.parseColor("##b4e7fa"));
                        break;
                    case "red":
                        constraintLayout.setBackgroundColor(Color.parseColor("#000000"));
                        break;
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = view.findViewById(R.id.MeetHomeDate);
        textViewDate.setText(currentDate);

        writeBtn = view.findViewById(R.id.MeetHomeEnterBtn);
        readBtn = view.findViewById(R.id.MeetHomeReadBtn);
        editText = view.findViewById(R.id.MeetHomeEditText);
        readFileTextView = view.findViewById(R.id.MeetHomeReadTextView);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                writeToFile(getString(R.string.home_file_name),content);
                editText.setText("");
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = readFileFile(getString(R.string.home_file_name));
                readFileTextView.setText(content);
            }
        });
        return view;
    }

    private String readFileFile(String file){
        File path = getActivity().getApplicationContext().getFilesDir();
        File read = new File(path,file);
        byte[] content = new byte[(int)read.length()];
        try{
            FileInputStream stream = new FileInputStream(read);
            stream.read(content);
            Toast.makeText(getActivity().getApplicationContext(),getString(R.string.home_frag_toast_text_read) + file, Toast.LENGTH_SHORT).show();
            return new String(content);

        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
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

    private String getCurrentTime(){
        return new SimpleDateFormat(getString(R.string.date_home_frag), Locale.getDefault()).format(new Date());
    }
}