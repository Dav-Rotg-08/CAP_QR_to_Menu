package com.example.qr_to_menu;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class MenuObject {
    private ArrayList<String> dishesPrices;
    private String restaurant;


    public MenuObject(String restaurant, ArrayList<String> dishesPrices){
        this.restaurant = restaurant;
        this.dishesPrices = dishesPrices;

    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<String> getDishesPrices(){
        return dishesPrices;
    }

    public void setDishesPrices(ArrayList<String> dishesPrices){
        this.dishesPrices = dishesPrices;
    }

    public String dishesPricesToString(String a, double b){
        String combine = a + b;
        return combine;

    }

}
