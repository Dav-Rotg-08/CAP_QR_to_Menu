package com.example.qr_to_menu;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MenuObject implements Parcelable {
    private ArrayList<Dish> dishesPrices;
    private String restaurant;

    MenuObject(String restaurant) {
        this.restaurant = restaurant;
        this.dishesPrices = new ArrayList<>();
    }

    private MenuObject(Parcel in) {
        dishesPrices = in.createTypedArrayList(Dish.CREATOR);
        restaurant = in.readString();
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

    String getRestaurant() {
        return restaurant;
    }

    void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    ArrayList<Dish> getDishesPrices() {
        return dishesPrices;
    }

    void setDishesPrices(ArrayList<Dish> dishesPrices) {
        this.dishesPrices = dishesPrices;
    }

    public String dishesPricesToString(String a, double b) {
        return a + b;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(dishesPrices);
        parcel.writeString(restaurant);
    }
}
