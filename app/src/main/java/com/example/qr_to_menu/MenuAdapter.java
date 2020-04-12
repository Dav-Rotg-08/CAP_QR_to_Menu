package com.example.qr_to_menu;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements Filterable {
    private LayoutInflater mInflater;
    private Context context;
    private Activity activity;
    private ArrayList<Dish> list;
    private int menuNumber;
    private ArrayList<Dish> fullList;

    // data is passed into the constructor
    public MenuAdapter(Activity activity, Context context, ArrayList<Dish> list, int menuNumber) {
        this.context = context;
        this.activity = activity;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.menuNumber = menuNumber;
        fullList = new ArrayList<>(list);
    }

    // inflates the row layout from xml when needed
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.dish, parent, false);
        return new MenuAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, final int position) {
        Dish dish = list.get(position);
        holder.text.setText(dish.getName() + " Price: " + dish.getPrice());
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action(menuNumber, position);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return dishFilter;
    }

    public Filter getFilterPrefs(){
        return filterPrefs;
    }

    //Code for Search Bar Filter
    private Filter dishFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Dish> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(fullList);
            } else{
                String filterPattern = constraint.toString().trim();
                for(Dish dish : fullList){
                    if(dish.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(dish);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }





        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<Dish>)results.values);
            notifyDataSetChanged();
        }
    };

    //Code for Filtering based on preferences
    private Filter filterPrefs = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Dish> filteredList = new ArrayList<>();
            if(constraint.toString().equalsIgnoreCase("None")){
                filteredList.addAll(fullList);
            }else{
                String selectedPref = constraint.toString();

                for(Dish dish : fullList){
                    if(dish.getParameters().contains(selectedPref)){
                        filteredList.add(dish);
                    }
                }

            }
            FilterResults filteredPrefs = new FilterResults();
            filteredPrefs.values = filteredList;

            return filteredPrefs;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<Dish>)results.values);
            notifyDataSetChanged();
        }

    };



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.dishText);
        }
    }

    public abstract void action(int numberOfMenu, int position);
}