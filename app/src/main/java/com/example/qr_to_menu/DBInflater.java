package com.example.qr_to_menu;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBInflater {
    private static DBInflater db;
    private static SharedPreferences sharedPreferences;
    private ArrayList<MenuObject> data;

    private DBInflater(Activity activity) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String dataS = sharedPreferences.getString("Data", "@@@");
        if (dataS.equals("@@@")) {
            data = new ArrayList<>();
            ArrayList<String> n1 = new ArrayList<>();
            ArrayList<String> n2 = new ArrayList<>();
            ArrayList<String> n3 = new ArrayList<>();
            ArrayList<String> n4 = new ArrayList<>();
            ArrayList<String> n5 = new ArrayList<>();
            ArrayList<String> n6 = new ArrayList<>();
            ArrayList<String> n7 = new ArrayList<>();
            ArrayList<String> n8 = new ArrayList<>();

            n1.add("Sugar");
            n1.add("Gluten");

            n2.add("Sugar");
            n2.add("Gluten");

            n3.add("Sugar");
            n3.add("Gluten");

            n4.add("Sugar");
            n4.add("Gluten");
            n4.add("Halal");

            n5.add("Sugar");
            n5.add("Gluten");
            n5.add("Halal");
            n5.add("Spicy");

            n6.add("Sugar");
            n6.add("Gluten");
            n6.add("Halal");
            n6.add("Spicy");

            n7.add("Sugar");
            n7.add("Gluten");
            n7.add("Halal");
            n7.add("Spicy");

            n8.add("Vegan");
            n8.add("Sugar");
            n8.add("SeaFood");





            MenuObject mo = new MenuObject("BURGER_KING");
            mo.getDishesPrices().add(new Dish("Whopper", 4.19,n1));
            mo.getDishesPrices().add(new Dish("Double Whopper", 5.29, n2));
            mo.getDishesPrices().add(new Dish("Whopper Jr.", 2.19, n3));
            mo.getDishesPrices().add(new Dish("Original Chicken Sandwich", 4.09, n4));
            mo.getDishesPrices().add(new Dish("Tendercrisp Chicken Sandwich", 4.99, n5));
            mo.getDishesPrices().add(new Dish("Chicken Nuggets-Meal", 5.99, n6));
            mo.getDishesPrices().add(new Dish("Chicken Fries-Meal", 5.59, n7));
            mo.getDishesPrices().add(new Dish("Big Fish Sandwich-Meal", 6.39, n8));
            data.add(mo);
        } else {
            data = gson.fromJson(dataS, new TypeToken<ArrayList<MenuObject>>() {
            }.getType());
        }
    }

    public int addMenuObject(MenuObject menu) {
        int imo = -1;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getRestaurant().equals(menu)) {
                imo = i;
            }
        }
        if (imo == -1) {
            data.add(menu);
            return data.size() - 1;
        } else {
            data.remove(imo);
            data.set(imo, menu);
            return imo;
        }
    }

    public int getMenuObject(String name) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getRestaurant().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void updateMenuObject(MenuObject menu) {
        int imo = -1;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getRestaurant().equals(menu)) {
                imo = i;
            }
        }
        if (imo == -1) {
            data.add(menu);
        } else {
            data.remove(imo);
            data.set(imo, menu);
        }
        updateInternal();
    }

    void updateInternal() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String usersS = gson.toJson(data);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Data", usersS);
        editor.commit();
    }

    public static DBInflater getInstance(Activity activity) {
        if (db == null) {
            db = new DBInflater(activity);
        }
        return db;
    }

    public MenuObject getMenuObject(int menuNumber) {
        return data.get(menuNumber);
    }
}
