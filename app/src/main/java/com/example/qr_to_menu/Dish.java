package com.example.qr_to_menu;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Dish implements Parcelable {
    private String name;
    private double price;
    private ArrayList<String> parameters;

    public Dish(String name, double price, ArrayList<String> parameters) {
        this.name = name;
        this.price = price;
        this.parameters = parameters;
    }

    private Dish(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        parameters = in.createStringArrayList();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeStringList(parameters);
    }
}
