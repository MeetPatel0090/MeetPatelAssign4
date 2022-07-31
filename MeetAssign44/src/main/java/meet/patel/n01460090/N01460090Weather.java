package meet.patel.n01460090;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class N01460090Weather extends Fragment {

    Spinner spinner;
    String Toronto = "Toronto", NewDelhi = "New Delhi", Sydney = "Sydney", London = "London", Paris = "Paris";
    String Canada = "Canada", India = "India", Australia = "Australia", UK = "United Kingdom", France = "France";
    TextView Country, Name, Latitude, Longitude, Humidity, Description;

    private final String api = "9662ce8dd47b0fb078e29c318ebf7c31";
    private final String url = "https://api.openweathermap.org/data/2.5/weather";

    public N01460090Weather() {
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
       View view = inflater.inflate(R.layout.n01460090_weather, container, false);

        Country = (TextView) view.findViewById(R.id.MeetWeatherCountryTextView2);
        Name = (TextView) view.findViewById(R.id.MeetWeatherCityTextView2);
        Latitude = (TextView) view.findViewById(R.id.MeetWeatherLatitudeTextView2);
        Longitude = (TextView) view.findViewById(R.id.MeetWeatherLongitudeTextView2);
        Humidity = (TextView) view.findViewById(R.id.MeetWeatherHumidityTextView2);
        Description = (TextView) view.findViewById(R.id.MeetWeatherDescriptionTextView2);

        spinner = (Spinner) view.findViewById(R.id.MeetWeatherSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getWeatherDetails(Toronto,Canada);
                        break;
                    case 1:
                        getWeatherDetails(London,UK);
                        break;
                    case 2:
                        getWeatherDetails(NewDelhi,India);
                        break;
                    case 3:
                        getWeatherDetails(Sydney,Australia);
                        break;
                    case 4:
                        getWeatherDetails(Paris,France);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       return view;
    }

    private void getWeatherDetails(String city, String country){
        String tempUrl = url + "?q=" + city + "," + country + "&appid=" + api;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String output = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                    String countryName = jsonObjectSys.getString("country");
                    String cityName = jsonObject.getString("name");
                    JSONObject jsonObjectCoord = jsonObject.getJSONObject("coord");
                    double longitude = jsonObjectCoord.getDouble("lon");
                    double latitude = jsonObjectCoord.getDouble("lat");
                    String convLon = Double.toString(longitude);
                    String convLat = Double.toString(latitude);
                    String convHum = Integer.toString(humidity);

                    Country.setText(countryName);
                    Name.setText(cityName);
                    Latitude.setText(convLat);
                    Longitude.setText(convLon);
                    Humidity.setText(convHum + "°C");
                    Description.setText(description);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), getString(R.string.weather_toast_error), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}