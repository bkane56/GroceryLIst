package com.bkane56.grocerylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.bkane56.grocerylist.items.StaplesListItem;
import com.google.gson.Gson;

public class StaplesList {

    public static final String PREFS_NAME = "Staples_List";
    public static final String STAPLE_ITEMS = "Staple_Items";

    public StaplesList() {
        super();
    }

    // This four methods are used for maintaining itemList.
    public static void saveStaplesList(Context context, List<StaplesListItem> itemList) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(itemList);

        editor.putString(STAPLE_ITEMS, jsonFavorites);

        editor.commit();
    }

    public static ArrayList<StaplesListItem> clearStaplesList(Context context){

        ArrayList<StaplesListItem> mList = new ArrayList<>();
        saveStaplesList(context, mList);
        return mList;
    }

    public static void addItem(Context context, StaplesListItem product) {

        List<StaplesListItem> favorites = getStaplesList(context);
        if (favorites == null)
            favorites = new ArrayList<StaplesListItem>();
        favorites.add(product);
        saveStaplesList(context, favorites);
    }

    public static void removeItem(Context context, StaplesListItem product) {
        ArrayList<StaplesListItem> favorites = getStaplesList(context);
        if (favorites != null) {
            favorites.remove(product);
            saveStaplesList(context, favorites);
        }
    }

    public static ArrayList<StaplesListItem> getStaplesList(Context context) {
        SharedPreferences settings;
        List<StaplesListItem> items;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(STAPLE_ITEMS)) {
            String jsonFavorites = settings.getString(STAPLE_ITEMS, null);
            Gson gson = new Gson();
            StaplesListItem[] myItems = gson.fromJson(jsonFavorites,
                    StaplesListItem[].class);

            items = Arrays.asList(myItems);
            items = new ArrayList<StaplesListItem>(items);
        } else
            return new ArrayList<StaplesListItem>();

        return (ArrayList<StaplesListItem>) items;
    }
}
