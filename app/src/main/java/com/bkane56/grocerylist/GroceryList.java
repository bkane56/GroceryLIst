package com.bkane56.grocerylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;

import com.bkane56.grocerylist.items.GroceryListItem;
import com.google.gson.Gson;

public class GroceryList implements View.OnClickListener{

    public static final String PREFS_NAME = "Grocery_List";
    public static final String GROCERY_ITEMS = "Items List";
    private Context context;
    private GroceryList instance = null;

    public GroceryList(Context context){
        super();
        this.context = context;
    }

    // This four methods are used for maintaining itemList.
    public void saveGroceryList(List<GroceryListItem> itemList) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(itemList);

        editor.putString(GROCERY_ITEMS, jsonFavorites);

        editor.commit();
    }

    public ArrayList<GroceryListItem> clearGroceryList(){

        ArrayList<GroceryListItem> mList = new ArrayList<>();
        saveGroceryList(mList);
        return mList;
    }

    public void addItem(GroceryListItem product) {
        List<GroceryListItem> favorites = getGroceryList();
        if (favorites == null)
            favorites = new ArrayList<GroceryListItem>();
        favorites.add(product);
        saveGroceryList(favorites);
    }

    public void removeItem(int position){
        ArrayList<GroceryListItem> favorites = getGroceryList();
        if (favorites != null) {
            favorites.remove(position);
            saveGroceryList(favorites);
        }
    }

    public void removeItem(GroceryListItem product) {
        ArrayList<GroceryListItem> favorites = getGroceryList();
        if (favorites != null) {
            favorites.remove(product);
            saveGroceryList(favorites);
        }
    }

    public ArrayList<GroceryListItem> getGroceryList() {
        SharedPreferences settings;
        List<GroceryListItem> items;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(GROCERY_ITEMS)) {
            String jsonFavorites = settings.getString(GROCERY_ITEMS, null);
            Gson gson = new Gson();
            GroceryListItem[] myItems = gson.fromJson(jsonFavorites,
                    GroceryListItem[].class);

            items = Arrays.asList(myItems);
            items = new ArrayList<GroceryListItem>(items);
        } else
            return new ArrayList<GroceryListItem>();

        return (ArrayList<GroceryListItem>) items;
    }

    @Override
    public void onClick(View v) {

    }

}

