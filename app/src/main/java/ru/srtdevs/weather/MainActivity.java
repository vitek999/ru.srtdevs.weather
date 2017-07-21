package ru.srtdevs.weather;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //Обработка нажатий в меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.about_app:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("About")
                        .setMessage("SmartRomTeam");
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
