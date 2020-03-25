package com.example.qr_to_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateRestaurant extends AppCompatActivity {
    EditText resName;
    EditText dish;
    EditText price;
    FloatingActionButton addDish;
    ListView list;
    ArrayList<String> menu;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_restaurant);

        resName = (EditText) findViewById(R.id.resName);
        dish = (EditText) findViewById(R.id.dish1);
        price = (EditText) findViewById(R.id.price);
        addDish = (FloatingActionButton) findViewById(R.id.addDishes);
        list = (ListView) findViewById(R.id.menu);
        menu = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(CreateRestaurant.this, android.R.layout.simple_list_item_1, menu);
        list.setAdapter(adapter);

        onButtonClick();

    }

    public void onButtonClick(){
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dishName = dish.getText().toString();
                String dishPrice = price.getText().toString();
                String newItem = dishName + " Price: " + dishPrice;
                menu.add(newItem);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
