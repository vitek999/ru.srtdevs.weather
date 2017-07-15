package ru.srtdevs.weather;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Виктор on 14.07.2017.
 *
 * use: GetWeather().execute("City name")
 *      при вызове .get() возвращается JSONObject содержащий
 *      temp(double) и description(String)
 *
 */

public class GetWeather extends AsyncTask<String,Void,JSONObject> {

    private final String Apikey = "0336fafc5128270fc1d1c40615db4df4";

    private final String TAG = "GetWeather";

    @Override
    protected JSONObject doInBackground(String... strings) {
        //создаём JSON обьект с temp и double. (пока так, лучше не придумал XD )
            JSONObject jsonReturn = new JSONObject();

        try{
            //получаем данные от сервера
            //в strings[0] находится название города.
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + strings[0] + "&appid=" + Apikey + "&lang=ru&units=metric";
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
            /*
            * парсим ответ сервера
            * пример ответа:
            *{"coord":{"lon":44.8,"lat":50.32},"weather":[{"id":802,"main":"Clouds","description":"слегка облачно","icon":"03d"}],"base":"stations","main":{"temp":30.02,"pressure":1005.15,"humidity":29,"temp_min":30.02,"temp_max":30.02,"sea_level":1026.58,"grnd_level":1005.15},"wind":{"speed":5.92,"deg":143.501},"clouds":{"all":36},"dt":1500106246,"sys":{"message":0.0031,"country":"RU","sunrise":1500080834,"sunset":1500138343},"id":543633,"name":"Kotovo","cod":200}
            *
            */
            JSONObject json = new JSONObject(response.toString());
            //парсим description
            JSONArray weather = json.getJSONArray("weather");
            Log.i(TAG, "weather: " + weather.get(0));
            String description = (String) weather.getJSONObject(0).get("description");
            Log.i(TAG, "description: " + description);
            //парсим temp
            JSONObject main = json.getJSONObject("main");
            Log.i(TAG, "main: " + main.toString());
            Double temp = (double) main.get("temp");
            Log.i(TAG, "temp: " + temp);

            //заполняем JSON обьект с temp и double. (пока так, лучше не придумал XD )
            jsonReturn.put("temp", temp).put("description", description);

        }catch (Throwable cause){
            cause.printStackTrace();
        }
        Log.i(TAG, "jsonReturn: " + jsonReturn);
        //возвращаем jsonReturn
        return jsonReturn;
    }
}
