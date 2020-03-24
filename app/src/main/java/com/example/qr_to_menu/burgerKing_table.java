package com.example.qr_to_menu;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class burgerKing_table {
    public static ArrayList<String> bk_list = new ArrayList<>();

    public burgerKing_table() {
        String item1 = dishesPricesToString("Whopper", 4.19);
        String item2 = dishesPricesToString("Double Whopper", 5.29);
        String item3 = dishesPricesToString("Whopper Jr.", 2.19);
        String item4 = dishesPricesToString("Original Chicken Sandwich", 4.09);
        String item5 = dishesPricesToString("Tendercrisp Chicken Sandwich", 4.99);
        String item6 = dishesPricesToString("Chicken Nuggets-Meal", 5.99);
        String item7 = dishesPricesToString("Chicken Fries-Meal", 5.59);
        String item8 = dishesPricesToString("Big Fish Sandwich-Meal", 6.39);

        bk_list.add(item1);
        bk_list.add(item2);
        bk_list.add(item3);
        bk_list.add(item4);
        bk_list.add(item5);
        bk_list.add(item6);
        bk_list.add(item7);
        bk_list.add(item8);
    }



    public String dishesPricesToString(String a, double b){
        String combine = a + " Price: " + b;
        return combine;

    }






}
