package ru.srtdevs.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Виктор on 14.07.2017.
 */

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    GetWeather getWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //use: GetWeather().execute("City name")
        //start test...
        getWeather = new GetWeather();
        getWeather.execute("kotovo");
        try {
            JSONObject result = getWeather.get();
            Log.i(TAG, "result: " + result);
        }catch (Throwable cause){
            cause.printStackTrace();
        }
        //end test...


    }
}
