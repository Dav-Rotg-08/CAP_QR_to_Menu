package com.example.qr_to_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    Button confirmMenu;

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
        confirmMenu = (Button) findViewById(R.id.confirmMenu);

        resName.addTextChangedListener(registerMenuTextWatcher);
        dish.addTextChangedListener(registerMenuTextWatcher);
        price.addTextChangedListener(registerMenuTextWatcher);


        onButtonClick();



    }

    private TextWatcher registerMenuTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String resInput = resName.getText().toString();
            String dishInput = dish.getText().toString();
            String priceInput = price.getText().toString();

            addDish.setClickable(!resInput.isEmpty() && !dishInput.isEmpty() && !priceInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void onButtonClick(){
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dishName = dish.getText().toString();
                String dishPrice = price.getText().toString();
                String newItem = dishName + " Price: " + dishPrice;
                menu.add(newItem);
                adapter.notifyDataSetChanged();
                final MenuObject newMenu = new MenuObject(resName.getText().toString(),menu);
                confirmMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CreateRestaurant.this, MenuCall.class);
                        intent.putExtra("NEW MENU", newMenu );

                        startActivity(intent);
                    }
                });
            }
        });
    }


    }

