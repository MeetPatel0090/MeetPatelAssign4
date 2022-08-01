// Meet Patel N01460090 Section:- RNB

package meet.patel.n01460090;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MeetFileContent extends Fragment {

    Button readBtn, deleteBtn;
    TextView readTextView;

    public MeetFileContent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.meet_file, container, false);

        readTextView = view.findViewById(R.id.MeetFileContentTextView);
        readBtn = view.findViewById(R.id.MeetFileContentReadFileBtn);
        deleteBtn = view.findViewById(R.id.MeetFileContentDeleteBtn);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile(readTextView);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(readTextView);
            }
        });

        return view;
    }

    public void readFile(View v){
        FileInputStream fileInputStream = null;
        readTextView = getActivity().findViewById(R.id.MeetFileContentTextView);

        File file = new File(getString(R.string.file_content_path_name));
        String empty = "";

        try{
            fileInputStream = getActivity().openFileInput("meet.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String string;

            while ((string = bufferedReader.readLine()) != null) {
                stringBuilder.append(string).append("\n");
            }
            bufferedReader.close();
            readTextView.setText(stringBuilder.toString());
            if(readTextView.getText().toString().trim().length() == 0){
                readTextView.setText(R.string.file_content_no_content);
            }
        }catch (FileNotFoundException e){
            readTextView.setText(R.string.file_content_no_content);
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {

            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (IOException e){

                    e.printStackTrace();
                }
            }
        }
    }
    public void deleteFile(View v){
        File file = new File(getActivity().getFilesDir(), getString(R.string.file_content_child_file_name));
        boolean deleted = file.delete();
        readTextView.setText("File Deleted!!!");
        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.file_content_file_deleted) + file, Toast.LENGTH_SHORT).show();
    }

}