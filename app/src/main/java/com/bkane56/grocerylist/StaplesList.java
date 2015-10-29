package com.bkane56.grocerylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.view.View;

import com.bkane56.grocerylist.items.StaplesListItem;
import com.google.gson.Gson;
import com.google.zxing.common.StringUtils;

import org.jsoup.helper.StringUtil;

public class StaplesList implements View.OnClickListener{

    public static final String PREFS_NAME = "Staples_List";
    public static String STAPLE_ITEMS = "Staple_Items";
    private static Context context;
    private static StaplesList instance = null;


    public StaplesList(Context context){
        super();
        this.context = context;
    }
//    sets the header for the Json file to type of staple
    public void setStapleItems(String stapleType) {
        String[] mString = stapleType.split(" ");
        STAPLE_ITEMS = TextUtils.join("_", mString);
    }

    // This methods are used for maintaining itemList.
    public void saveStaplesList(List<StaplesListItem> itemList) {
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

    public ArrayList<StaplesListItem> clearStaplesList(){

        ArrayList<StaplesListItem> mList = new ArrayList<>();
        saveStaplesList(mList);
        return mList;
    }

    public void addItem(StaplesListItem product) {
        List<StaplesListItem> favorites = getStaplesList();
        if (favorites == null)
            favorites = new ArrayList<StaplesListItem>();
        favorites.add(product);
        saveStaplesList(favorites);
    }

    public void removeItem(int position){
        ArrayList<StaplesListItem> favorites = getStaplesList();
        if (favorites != null) {
            favorites.remove(position);
            saveStaplesList(favorites);
        }
    }

    public void removeItem(StaplesListItem product) {
        ArrayList<StaplesListItem> favorites = getStaplesList();
        if (favorites != null) {
            favorites.remove(product);
            saveStaplesList(favorites);
        }
    }

    public int getSize (){
        ArrayList<StaplesListItem> favorites = getStaplesList();
        return favorites.size();
    }

    public void updateItem(int position, StaplesListItem item){
        ArrayList<StaplesListItem> favorites = getStaplesList();
        favorites.set(position, item);
        saveStaplesList(favorites);
    }

    public boolean contains (String name){
        boolean isInList = false;
        ArrayList<StaplesListItem> favorites = getStaplesList();
        for (StaplesListItem gItem : favorites){
            if (gItem.getStaplesItem().equals(name)){
                isInList = true;
            }
        }
        return isInList;
    }

    public String getTypeFromList(String name){
        String type = null;
        ArrayList<StaplesListItem> favorites = getStaplesList();
        for (StaplesListItem gItem : favorites){
            if (gItem.getStaplesItem().equals(name)){
                type = gItem.getStapleType();
            }
        }
        return type;
    }

    public ArrayList<StaplesListItem> getStaplesList() {
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

    @Override
    public void onClick(View v) {

    }

}

