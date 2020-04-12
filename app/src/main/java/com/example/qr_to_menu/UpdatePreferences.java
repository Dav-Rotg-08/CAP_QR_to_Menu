package com.example.qr_to_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class UpdatePreferences extends AppCompatActivity  {
    private CheckBox mVegan, mSpicy, mSugar, mGluten, mHalal, mCashrut, mAlcohol, mSeaFood;
    private Button confirm;
    private ArrayList<String> mResult;
    int dishNumber;
    int editable;
    int menuNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_preferences);

        menuNumber = getIntent().getIntExtra("MENU_NUMBER", -1);
        dishNumber = getIntent().getIntExtra("DISH_NUMBER", -1);
        editable = getIntent().getIntExtra("EDITABLE", -1);

        mVegan = findViewById(R.id.vegan);
        mSpicy = findViewById(R.id.spicy);
        mSugar = findViewById(R.id.sugar);
        mGluten = findViewById(R.id.gluten);
        mHalal = findViewById(R.id.halal);
        mCashrut = findViewById(R.id.cashrut);
        mAlcohol = findViewById(R.id.alcohol);
        mSeaFood = findViewById(R.id.seafood);

        confirm = findViewById(R.id.setPrefs);
        mResult = new ArrayList<String>();

        mVegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mVegan.isChecked())
                    mResult.add("Vegan");
                else
                    mResult.remove("Vegan");
            }
        });

        mSpicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSpicy.isChecked())
                    mResult.add("Spicy");
                else
                    mResult.remove("Spicy");
            }
        });

        mSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSugar.isChecked())
                    mResult.add("Sugar");
                else
                    mResult.remove("Sugar");
            }
        });

        mGluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mGluten.isChecked())
                    mResult.add("Gluten");
                else
                    mResult.remove("Gluten");
            }
        });

        mHalal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHalal.isChecked())
                    mResult.add("Halal");
                else
                    mResult.remove("Halal");
            }
        });

        mCashrut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCashrut.isChecked())
                    mResult.add("Cashrut");
                else
                    mResult.remove("Cashrut");
            }
        });

        mAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAlcohol.isChecked())
                    mResult.add("Alcohol");
                else
                    mResult.remove("Alcohol");
            }
        });

        mSeaFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSeaFood.isChecked())
                    mResult.add("Sea Food");
                else
                    mResult.remove("Sea Food");
            }
        });


        if (editable < 1) {
            Dish dish = DBInflater.getInstance(this).getMenuObject(menuNumber).getDishesPrices().get(dishNumber);
            dish.setParameters(mResult);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatePrefs();
                }
            });
        }
    }


    public void updatePrefs(){
        Dish dish = DBInflater.getInstance(this).getMenuObject(menuNumber).getDishesPrices().get(dishNumber);
        dish.setParameters(mResult);
        DBInflater.getInstance(this).updateInternal();
    }
    /*
    public void updateDish(View view) {
        if (editable == 1) {
            Dish dish = DBInflater.getInstance(this).getMenuObject(menuNumber).getDishesPrices().get(dishNumber);
            for (int i = 0; i < 8; i++) {
                dish.getParameters()[i] = cbArray[i].isChecked();
            }
            DBInflater.getInstance(this).updateInternal();
        }
    }

     */

}
