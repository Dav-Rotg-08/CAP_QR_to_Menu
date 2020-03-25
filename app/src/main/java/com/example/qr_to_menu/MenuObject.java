package com.example.qr_to_menu;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class MenuObject implements Parcelable {
    private ArrayList<String> dishesPrices;
    private String restaurant;


    public MenuObject(String restaurant, ArrayList<String> dishesPrices){
        this.restaurant = restaurant;
        this.dishesPrices = dishesPrices;

    }

    protected MenuObject(Parcel in) {
        dishesPrices = in.createStringArrayList();
        restaurant = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(dishesPrices);
        dest.writeString(restaurant);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MenuObject> CREATOR = new Creator<MenuObject>() {
        @Override
        public MenuObject createFromParcel(Parcel in) {
            return new MenuObject(in);
        }

        @Override
        public MenuObject[] newArray(int size) {
            return new MenuObject[size];
        }
    };

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
