package com.example.qr_to_menu;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class DishUpdateActivity extends AppCompatActivity {
    CheckBox[] cbArray;
    int dishNumber;
    int editable;
    int menuNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_update);
        menuNumber = getIntent().getIntExtra("MENU_NUMBER", -1);
        dishNumber = getIntent().getIntExtra("DISH_NUMBER", -1);
        editable = getIntent().getIntExtra("EDITABLE", -1);
        cbArray = new CheckBox[]{findViewById(R.id.checkBox1), findViewById(R.id.checkBox2),
                findViewById(R.id.checkBox3), findViewById(R.id.checkBox4),
                findViewById(R.id.checkBox5), findViewById(R.id.checkBox6),
                findViewById(R.id.checkBox7), findViewById(R.id.checkBox8)};
        if (editable < 1) {
            Dish dish = DBInflater.getInstance(this).getMenuObject(menuNumber).getDishesPrices().get(dishNumber);
            for (int i = 0; i < 8; i++) {
             //   cbArray[i].setChecked(dish.getParameters());
            }
            Button button = findViewById(R.id.button);
            button.setVisibility(View.VISIBLE);
        }
    }

    public void updateDish(View view) {
        if (editable == 1) {
            Dish dish = DBInflater.getInstance(this).getMenuObject(menuNumber).getDishesPrices().get(dishNumber);
            for (int i = 0; i < 8; i++) {
              // dish.getParameters().get(i) = cbArray[i].isChecked();
            }
            DBInflater.getInstance(this).updateInternal();
        }
    }
}
