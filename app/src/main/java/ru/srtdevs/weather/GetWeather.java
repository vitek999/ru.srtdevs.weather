package ru.srtdevs.weather;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Виктор on 14.07.2017.
 *
 * use: GetWeather().execute("City name")
 *
 */

public class GetWeather extends AsyncTask<String,Void,Void> {

    final String Apikey = "0336fafc5128270fc1d1c40615db4df4";

    final String TAG = "GetWeather";

    @Override
    protected Void doInBackground(String... strings) {
        try{
            //в strings[0] находится название города.
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + strings[0] + "&appid=" + Apikey + "&lang=ru";
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            Log.i(TAG, "response: " + response.toString());
        }catch (Throwable cause){
            cause.printStackTrace();
        }
        return null;
    }
}
