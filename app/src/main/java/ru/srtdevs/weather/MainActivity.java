package ru.srtdevs.weather;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Виктор on 14.07.2017.
 */

public class MainActivity extends AppCompatActivity {

    ArrayList<String> citys = new ArrayList();
    ArrayAdapter<String> adapter;
    ListView cityList;

    private final String TAG = "MainActivity";

    GetWeather getWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citys.add("Test 1");
        citys.add("Test 2");

        cityList = (ListView) findViewById(R.id.city_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, citys);
        cityList.setAdapter(adapter);
    }

    public void getWeatherInfo(String city){
        //use: GetWeather().execute("City name")
        //start test...
        getWeather = new GetWeather();
        getWeather.execute(city);
        try {
            JSONObject result = getWeather.get();
            Log.i(TAG, "result: " + result);
        }catch (Throwable cause){
            cause.printStackTrace();
        }
        //end test...
    }

    public void addCity(View v){
        EditText cityEdittext = (EditText) findViewById(R.id.city_name);
        String city = cityEdittext.getText().toString();
        if(!city.isEmpty() && !citys.contains(city)){
            adapter.add(city);
            cityEdittext.setText("");
            adapter.notifyDataSetChanged();
        }
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
                // TODO: 21.07.2017 сделать активити с настройками
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



