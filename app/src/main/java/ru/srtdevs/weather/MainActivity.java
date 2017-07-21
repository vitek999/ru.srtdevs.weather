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
import android.widget.Toast;

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

        citys.add("Moscow");

        cityList = (ListView) findViewById(R.id.city_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, citys);
        cityList.setAdapter(adapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedCity = adapter.getItem(position);
                //use: GetWeather().execute("City name")
                //start test...
                getWeather = new GetWeather();
                getWeather.execute(selectedCity);
                try {
                    JSONObject result = getWeather.get();
                    Log.i(TAG, "result: " + result);
                    double temp = (double) result.get("temp");
                    String desc = (String) result.get("description");
                    Toast.makeText(getApplicationContext(),"в городе " + selectedCity + " " + desc + " температура: " + temp + " градусов", Toast.LENGTH_SHORT).show();
                }catch (Throwable cause){
                    cause.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Что-то пошло не так..", Toast.LENGTH_SHORT).show();
                }
                //end test...
            }
        });

    }

    public void addCity(View v){
        EditText cityEditText = (EditText) findViewById(R.id.city_name);
        String city = cityEditText.getText().toString();
        if(!city.isEmpty() && !citys.contains(city)){
            adapter.add(city);
            cityEditText.setText("");
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



