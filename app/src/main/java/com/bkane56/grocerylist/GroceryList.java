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


    public GroceryList() {
        super();
    }

    // This four methods are used for maintaining itemList.
    public static void saveGroceryList(Context context, List<GroceryListItem> itemList) {
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

    public static ArrayList<GroceryListItem> clearGroceryList(Context context){

        ArrayList<GroceryListItem> mList = new ArrayList<>();
        saveGroceryList(context, mList);
        return mList;
    }

    public static void addItem(Context context, GroceryListItem product) {
        List<GroceryListItem> favorites = getGroceryList(context);
        if (favorites == null)
            favorites = new ArrayList<GroceryListItem>();
        favorites.add(product);
        saveGroceryList(context, favorites);
    }

    public static void removeItem(Context context, GroceryListItem product) {
        ArrayList<GroceryListItem> favorites = getGroceryList(context);
        if (favorites != null) {
            favorites.remove(product);
            saveGroceryList(context, favorites);
        }
    }

    public static ArrayList<GroceryListItem> getGroceryList(Context context) {
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

