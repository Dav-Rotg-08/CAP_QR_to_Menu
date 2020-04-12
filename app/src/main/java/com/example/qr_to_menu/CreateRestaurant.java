package com.example.qr_to_menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateRestaurant extends Activity {
    EditText resName;
    EditText dish;
    EditText price;
    FloatingActionButton addDish;
    RecyclerView list;
    Button confirmMenu;
    MenuObject newMenu;
    MenuAdapter adapter;
    ArrayList<String> temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_restaurant);
        resName = findViewById(R.id.resName);
        dish = findViewById(R.id.dish1);
        price = findViewById(R.id.price);
        addDish = findViewById(R.id.addDishes);
        list = findViewById(R.id.menuRV);
        newMenu = new MenuObject(resName.getText().toString());
        final int menuNumber = DBInflater.getInstance(this).addMenuObject(newMenu);
        confirmMenu = findViewById(R.id.confirmMenu);
        resName.addTextChangedListener(registerMenuTextWatcher);
        dish.addTextChangedListener(registerMenuTextWatcher);
        price.addTextChangedListener(registerMenuTextWatcher);
        final String name=getIntent().getStringExtra("SENT_TEXT");
        resName.setText(name);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        list.setLayoutManager(mLayoutManager);
        adapter = new MenuAdapter(this, this.getApplicationContext(), newMenu.getDishesPrices(), menuNumber) {
            @Override
            public void action(int numberOfMenu, int position) {
                Intent intent = new Intent(CreateRestaurant.this, DishUpdateActivity.class);
                intent.putExtra("MENU_NUMBER", numberOfMenu);
                intent.putExtra("DISH_NUMBER", position);
                intent.putExtra("EDITABLE", 0);
                startActivity(intent);
            }
        };
        list.setAdapter(adapter);
        final Activity activity=this;
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMenu.getDishesPrices().add(new Dish(dish.getText().toString(), Float.parseFloat(price.getText().toString()),temp));
                newMenu.setRestaurant(resName.getText().toString());
                DBInflater.getInstance(activity).updateInternal();
                //adapter.notifyDataSetChanged();
                confirmMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBInflater.getInstance(activity).updateMenuObject(newMenu);
                        Intent intent = new Intent(CreateRestaurant.this, MenuCall.class);
                        intent.putExtra("SENT_TEXT", newMenu.getRestaurant());
                        startActivity(intent);
                    }
                });
            }
        });
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
}

