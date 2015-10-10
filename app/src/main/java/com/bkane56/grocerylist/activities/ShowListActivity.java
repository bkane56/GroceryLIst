package com.bkane56.grocerylist.activities;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.Helpers.ViewGroupUtils;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.adapter.GroceryListAdapter;
import com.bkane56.grocerylist.items.GroceryListItem;

import java.util.ArrayList;
import java.util.List;

public class ShowListActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarDrawerToggle mDrawerToggle;
    private int qty = 1;
    private GroceryListItem mItem;

    private View containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Explode explode = new Explode();
        explode.setDuration(1250);
        getWindow().setEnterTransition(explode);

        setContentView(R.layout.activity_show_list);

        TransitionInflater inflater = TransitionInflater.from(this);
        Transition transition = inflater.inflateTransition(R.transition.transition_explode_fade);
        transition.setDuration(1500);
        getWindow().setReenterTransition(transition);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);


        RecyclerView groceryRecyclerView = (RecyclerView) findViewById(R.id.rv_grocery_list);
        GroceryListAdapter groceryListAdapter = new GroceryListAdapter(this, GroceryList.getGroceryList(this));
        groceryRecyclerView.setAdapter(groceryListAdapter);
        groceryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.add1).setOnClickListener(this);
        findViewById(R.id.subtract1).setOnClickListener(this);
        findViewById(R.id.add_new).setOnClickListener(this);
        findViewById(R.id.scan_item).setOnClickListener(this);
        findViewById(R.id.staples).setOnClickListener(this);
//        findViewById(R.id.finised).setOnClickListener(this);

        groceryRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

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
     public void onClick(View v) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);

        switch (v.getId()) {
            case R.id.staples:

//                ViewGroupUtils.replaceView(findViewById(R.id.show_list_layout), findViewById(R.id.staples_list));
//                RecyclerView staplesRecyclerView = (RecyclerView) findViewById(R.id.rv_staples_list);
//                GroceryListAdapter staplesListAdapter = new GroceryListAdapter(this, StaplesList.getStaplesList(this));
//                staplesRecyclerView.setAdapter(staplesListAdapter);
//                staplesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//                findViewById(R.id.finised).setOnClickListener(this);
//
//
//                staplesRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//                    @Override
//                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                    }
//
//                    @Override
//                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//                    }
//                });
                break;


            case R.id.scan_item:
                startActivity(new Intent(this, ScanItemActivity.class), compat.toBundle());
                break;
            case R.id.add_new:
                startActivity(new Intent(this, AddItemsToListActivity.class), compat.toBundle());
                break;
            case R.id.finised:
                setContentView(R.layout.activity_show_list);
                break;
            default:
                break;
        }
    }

    public void startScan(View v){

//        ActivityOptionsCompat compat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "shared_item");
//            startActivity(new Intent(this, ScanItemActivity.class), compat.toBundle());
        startActivity(new Intent(this, ScanItemActivity.class));


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
