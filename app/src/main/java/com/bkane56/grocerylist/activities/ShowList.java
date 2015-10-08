package com.bkane56.grocerylist.activities;

import android.content.Context;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.adapter.GroceryListAdapter;
import com.bkane56.grocerylist.adapter.NavigationDrawerAdapter;
import com.bkane56.grocerylist.items.GroceryListItem;

import java.util.ArrayList;
import java.util.List;

public class ShowList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private GroceryListAdapter adapter;
    private View containerView;
    private static String[] titles = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Explode explode = new Explode();
        explode.setDuration(1000);
        getWindow().setEnterTransition(explode);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_list);

        recyclerView = (RecyclerView) findViewById(R.id.rv_grocery_list);
        adapter = new GroceryListAdapter(this, GroceryList.getGroceryList(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }
    private List<GroceryListItem> retrieveGroceryList(){

        List<GroceryListItem> myList = GroceryList.getGroceryList(this);
        if(myList == null){
            myList = new ArrayList<GroceryListItem>();

        }
        return myList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
