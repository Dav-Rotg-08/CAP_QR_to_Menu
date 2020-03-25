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
import java.util.Objects;
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
        Hashtable<String, MenuObject> allMenus = new Hashtable<String, MenuObject>();
        ArrayList<String> items = new ArrayList<String>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        MenuObject bk = new MenuObject("BURGER_KING", items);
        allMenus.put("BURGER_KING", bk);
        ArrayList<String> debug = new ArrayList<String>();
        debug.add("NO MENU");
        debug.add("NO MENU");
        debug.add("NO MENU");
        debug.add("NO MENU");
        debug.add("NO MENU");
        MenuObject newRestaurant = new MenuObject("null", debug);
        allMenus.put("CHIPOTLE", newRestaurant);

        Intent intent = getIntent();
        if(intent.getParcelableExtra("NEW_MENU") != null) {
            MenuObject newMenu = getIntent().getExtras().getParcelable("NEW_MENU");
            allMenus.put(newMenu.getRestaurant(), newMenu);

        }
        else{
            Toast.makeText(MenuCall.this,"Did Not Receive NEW Menu", Toast.LENGTH_SHORT).show();

        }
        if(intent.getStringExtra("SENT_TEXT") != null){
            String res_key = intent.getStringExtra("SENT_TEXT");
            MenuObject found = allMenus.get(res_key);
            newRestaurant.setRestaurant(found.getRestaurant());
            newRestaurant.setDishesPrices(found.getDishesPrices());
        }
        String res_name = newRestaurant.getRestaurant();
        restaurant_name.setText(res_name);
        //restaurant_name.setText(res_key);
        //String[] items = {item1, item2, item3, item4, item5, item6, item7, item8};
        ArrayList<String> menuList = newRestaurant.getDishesPrices();
        ArrayAdapter<String> Values = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menuList);
        list.setAdapter(Values);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MenuCall.this,"Food Item Selected: ",Toast.LENGTH_SHORT).show();
            }
        });


        ArrayList<String> bk_list = new ArrayList<String>();
        bk_list.add(item1);
        bk_list.add(item2);
        bk_list.add(item3);
        bk_list.add(item4);
        bk_list.add(item5);
        bk_list.add(item6);
        bk_list.add(item7);
        bk_list.add(item8);

    }

        String item1 = dishesPricesToString("Whopper", 4.19);
        String item2 = dishesPricesToString("Double Whopper", 5.29);
        String item3 = dishesPricesToString("Whopper Jr.", 2.19);
        String item4 = dishesPricesToString("Original Chicken Sandwich", 4.09);
        String item5 = dishesPricesToString("Tendercrisp Chicken Sandwich", 4.99);
        String item6 = dishesPricesToString("Chicken Nuggets-Meal", 5.99);
        String item7 = dishesPricesToString("Chicken Fries-Meal", 5.59);
        String item8 = dishesPricesToString("Big Fish Sandwich-Meal", 6.39);

        ArrayList<String> bk_list = new ArrayList<String>();








    public String dishesPricesToString(String a, double b){
        String combine = a + " Price: " + b;
        return combine;

    }
}
