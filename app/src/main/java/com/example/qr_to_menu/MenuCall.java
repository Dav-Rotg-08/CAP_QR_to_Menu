package com.example.qr_to_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class MenuCall extends AppCompatActivity {
    TextView restaurant_name;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_call);
        restaurant_name = findViewById(R.id.restaurant_name);
        list = (ListView) findViewById(R.id.dishesPrices);


        Intent intent = getIntent();
        String res_key = intent.getStringExtra("SENT_TEXT");
        MenuObject newRestaurant = MenuObject_HashTable.allMenus.get(res_key);
        //String res_name = newRestaurant.getRestaurant();
        restaurant_name.setText(res_key);

        String[] temp = {item1, item2, item3, item4, item5, item6, item7, item8};
        ArrayAdapter<String> Values = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,temp);
        list.setAdapter(Values);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MenuCall.this,"Food Item Selected: ",Toast.LENGTH_SHORT).show();
            }
        });




    }

        String item1 = dishesPricesToString("Whopper", 4.19);
        String item2 = dishesPricesToString("Double Whopper", 5.29);
        String item3 = dishesPricesToString("Whopper Jr.", 2.19);
        String item4 = dishesPricesToString("Original Chicken Sandwich", 4.09);
        String item5 = dishesPricesToString("Tendercrisp Chicken Sandwich", 4.99);
        String item6 = dishesPricesToString("Chicken Nuggets-Meal", 5.99);
        String item7 = dishesPricesToString("Chicken Fries-Meal", 5.59);
        String item8 = dishesPricesToString("Big Fish Sandwich-Meal", 6.39);



    public String dishesPricesToString(String a, double b){
        String combine = a + " Price: " + b;
        return combine;

    }
}
