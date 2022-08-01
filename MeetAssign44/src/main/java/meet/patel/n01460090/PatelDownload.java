// Meet Patel N01460090 Section:- RNB

package meet.patel.n01460090;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class PatelDownload extends Fragment {


    ListView listView;
    private static final String razerURL = "https://cdn.pngsumo.com/razer-insider-forum-razer-deathadder-chroma-sdk-razer-png-purple-312_312.png";
    private static final String appleURL = "https://s4.wallpapermoderna.com/wallpaper/8507/759/apple-logo-rainbow-3835-hd-download-technology-3835-preview.jpeg";
    private static final String twitchURL = "https://images.wallpapersden.com/image/download/twitch-4k-logo_bGpuamaUmZqaraWkpJRnbmhnrWduaGc.jpg";
    private static final int PERMISSION_STORAGE_CODE = 1000;
    ProgressBar progressBar;
    ImageView imageView = null;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.patel_download, container, false);

        imageView = (ImageView) view.findViewById(R.id.MeetDownloadImageView);
        progressBar = (ProgressBar) view.findViewById(R.id.MeetDownloadProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        TextView textView = view.findViewById(R.id.MeetDownloadTextViewSharedPref);
        sharedPreferences = getActivity().getSharedPreferences("Name", Context.MODE_PRIVATE);
        String dataFromHome = sharedPreferences.getString("Name","");
        textView.setText(dataFromHome);

        listView = (ListView) view.findViewById(R.id.MeetDownloadListView);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getString(R.string.listview_razer));
        arrayList.add(getString(R.string.listview_apple));
        arrayList.add(getString(R.string.listview_twitch));

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED){
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            getActivity().requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                        }
                        else
                        {
                            startDownload(razerURL);
                            AsyncTaskDown asyncTaskDown = new AsyncTaskDown();
                            asyncTaskDown.execute(razerURL);
                        }
                    }
                    else
                    {
                        startDownload(razerURL);
                        AsyncTaskDown asyncTaskDown = new AsyncTaskDown();
                        asyncTaskDown.execute(razerURL);
                    }
                }
                else if (position == 1)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED){
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            getActivity().requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                        }
                        else
                        {
                            startDownload(appleURL);
                            AsyncTaskDown asyncTaskDown = new AsyncTaskDown();
                            asyncTaskDown.execute(appleURL);
                        }
                    }
                    else
                    {
                        startDownload(appleURL);
                        AsyncTaskDown asyncTaskDown = new AsyncTaskDown();
                        asyncTaskDown.execute(appleURL);
                    }
                }
                else if (position == 2)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED){
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            getActivity().requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                        }
                        else
                        {
                            startDownload(twitchURL);
                            AsyncTaskDown asyncTaskDown = new AsyncTaskDown();
                            asyncTaskDown.execute(twitchURL);
                        }
                    }
                    else
                    {
                        startDownload(twitchURL);
                        AsyncTaskDown asyncTaskDown = new AsyncTaskDown();
                        asyncTaskDown.execute(twitchURL);
                    }
                }
            }
        });
        return view;
    }

    private void startDownload(String url_file){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url_file));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(getString(R.string.download_title));
        request.setDescription(getString(R.string.download_desc));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());

        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(), getString(R.string.download_granted), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), getString(R.string.download_denied), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private class AsyncTaskDown extends AsyncTask<String, Void, Bitmap> {
        @Override

        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... strings){
            Bitmap bitmap = null;
            try{
                Thread.sleep(5000);
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                int response = httpURLConnection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            if (imageView != null){
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setImageBitmap(bitmap);
            }else{
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}