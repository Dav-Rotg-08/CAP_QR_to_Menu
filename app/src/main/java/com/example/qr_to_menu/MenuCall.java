package com.example.qr_to_menu;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuCall extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView restaurant_name;
    RecyclerView list;
    MenuAdapter adapter;

    Spinner filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_call);

        //Setting up the Spinner which is the drop down menu for filtering menu
        filter = findViewById(R.id.filter);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this,
                R.array.Filters, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(filterAdapter);
        filter.setOnItemSelectedListener(this);




        restaurant_name = findViewById(R.id.restaurant_name);
        list = findViewById(R.id.dishesList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        list.setLayoutManager(mLayoutManager);
        ArrayList<Dish> menuList = new ArrayList<>();
        MenuObject menu;
        String resName = getIntent().getStringExtra("SENT_TEXT");
        int menuNumber = DBInflater.getInstance(this).getMenuObject(resName);
                if (menuNumber > -1) {
            menu = DBInflater.getInstance(this).getMenuObject(menuNumber);
            restaurant_name.setText(menu.getRestaurant());
            menuList = menu.getDishesPrices();
        } else {
            Toast.makeText(MenuCall.this, "Did Not Receive NEW Menu", Toast.LENGTH_SHORT).show();
        }
                adapter = new MenuAdapter(this, this.getApplicationContext(), menuList, menuNumber) {
            @Override
            public void action(int numberOfMenu, int position) {
                Intent intent = new Intent(MenuCall.this, UpdatePreferences.class);
                intent.putExtra("MENU_NUMBER", numberOfMenu);
                intent.putExtra("DISH_NUMBER", position);
                intent.putExtra("EDITABLE", 0);
                startActivity(intent);

            }
        };
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selection = parent.getItemAtPosition(position).toString();
        adapter.getFilterPrefs().filter(selection);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
