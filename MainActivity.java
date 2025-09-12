package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList = new ArrayList<>();
    // Note: Properly speaking, everything should NOT be in onCreate, probably?
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create all other widgets
        Button addButton = findViewById(R.id.buttonAdd);
        Button delButton = findViewById(R.id.buttonDel);
        Button confirm = findViewById(R.id.confirm_button);
        EditText addText = findViewById(R.id.add_city);
        TextView status = findViewById(R.id.status);    // for testing purposes

        addText.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);
        delButton.setEnabled(false);

        // create the interactable listView
        cityList = findViewById(R.id.city_list);
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Add City Actions
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                status.setText("Enter City");
                addButton.setEnabled(false);
                addText.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);

                confirm.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        // add string to arrayList and update ListView
                        dataList.add(addText.getText().toString());
                        cityList.setAdapter(cityAdapter);

                        addText.getText().clear();
                        addText.setVisibility(View.GONE);
                        confirm.setVisibility(View.GONE);
                        status.setText("Added City");
                        addButton.setEnabled(true);
                    }
                 });
            }
        });

        // Delete City Actions
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                String city = "City Selected: \n" + dataList.get(position);
                status.setText(city);
                delButton.setEnabled(true);

                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // remove string from Arraylist and update ListView
                        dataList.remove(position);
                        cityList.setAdapter(cityAdapter);
                        status.setText("Deleted");
                        delButton.setEnabled(false);

                    }
                });
            }
        });
    }
}