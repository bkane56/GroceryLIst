package com.bkane56.grocerylist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bkane56.grocerylist.FragmentDrawer;
import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.adapter.GroceryListAdapter;
import com.bkane56.grocerylist.adapter.StaplesListAdapter;
import com.bkane56.grocerylist.items.GroceryListItem;
import com.bkane56.grocerylist.items.StaplesListItem;

import java.util.List;

public class StaplesActivity extends AppCompatActivity
        implements FragmentDrawer.FragmentDrawerListener, View.OnClickListener{


    private StaplesList mStaplesList;
    private StaplesListAdapter mStaplesAdapter;
    private RecyclerView staplesRecyclerView;
    private List<StaplesListItem> mStapleData;
    private GroceryList mGroceryList;
    private GroceryListAdapter mGroceryListAdapter;
    private List<GroceryListItem> mGroceryData;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staples);

        TransitionInflater inflater = TransitionInflater.from(this);
        Transition transition = inflater.inflateTransition(R.transition.transition_explode_fade);
        transition.setDuration(1000);
        getWindow().setReenterTransition(transition);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);

        mGroceryList = new GroceryList(this);
        mGroceryData = mGroceryList.getGroceryList();
        mGroceryListAdapter = new GroceryListAdapter(this, mGroceryData);

        mStaplesList = new StaplesList();
        staplesRecyclerView = (RecyclerView) findViewById(R.id.rv_staples_list);
        mStapleData = StaplesList.getStaplesList(this);
        mStaplesAdapter = new StaplesListAdapter(this, mStapleData);
        staplesRecyclerView.setAdapter(mStaplesAdapter);
        staplesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCheckBox = (CheckBox) findViewById(R.id.cb_isChecked);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroceryListItem mGroceryItem;
                String tempData = "";
                mCheckBox = (CheckBox) findViewById(R.id.cb_isChecked);

                for(StaplesListItem staple : mStapleData){

                    if(staple.isChecked()){
                        mGroceryItem = new GroceryListItem(1, staple.getStaplesItem());
                        mGroceryList.addItem(mGroceryItem);
                        mGroceryListAdapter.notifyItemInserted(mGroceryList.getSize());
                        tempData = tempData + "\n" + staple.getStaplesItem();

                    }
                }
                Toast.makeText(StaplesActivity.this, "Added:\n" + tempData + "\n\n To Grocery List",
                        Toast.LENGTH_LONG).show();
                ActivityOptionsCompat compat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(StaplesActivity.this, null);
                startActivity(new Intent(StaplesActivity.this, ShowListActivity.class), compat.toBundle());
            }
        });
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }else if(id == R.id.clear_groceries){
            mGroceryList.clearGroceryList();
            Toast.makeText(getApplicationContext(), "Grocery List Cleared",
                    Toast.LENGTH_SHORT).show();
            mGroceryListAdapter.notifyItemRangeRemoved(0, mGroceryListAdapter.getItemCount() -1);

        }else {
            StaplesList.clearStaplesList(this);
            Toast.makeText(getApplicationContext(),"Staples List Cleared",
                    Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
