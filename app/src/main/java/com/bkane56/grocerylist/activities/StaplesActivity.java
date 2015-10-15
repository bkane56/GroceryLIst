package com.bkane56.grocerylist.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bkane56.grocerylist.FragmentDrawer;
import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.adapter.StaplesListAdapter;
import com.bkane56.grocerylist.items.StaplesListItem;

import java.util.List;

public class StaplesActivity extends AppCompatActivity
        implements FragmentDrawer.FragmentDrawerListener, View.OnClickListener{


    private StaplesList mStaplesList;
    private StaplesListAdapter mStaplesAdapter;
    private RecyclerView staplesRecyclerView;
    private List<StaplesListItem> mStapleData;
    private GroceryList mGroceryList;

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

        mStaplesList = new StaplesList();
        staplesRecyclerView = (RecyclerView) findViewById(R.id.rv_staples_list);
        mStapleData = StaplesList.getStaplesList(this);
        mStaplesAdapter = new StaplesListAdapter(this, mStapleData);
        staplesRecyclerView.setAdapter(mStaplesAdapter);
        staplesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    {
    }
//                findViewById(R.id.add_all).setOnClickListener(this);
//
//                staplesListAdapter = new StaplesListAdapter(this, staplesData);
//                staplesRecyclerView.setAdapter(staplesListAdapter);
//                staplesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//                findViewById(R.id.finised).setOnClickListener(this);
//
//                ItemClickSupport.addTo(staplesRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//                    @Override
//                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                        String stapleItem = staplesData.get(position).getStaplesItem();
//                        GroceryListItem groceryListItem =new GroceryListItem(1, stapleItem);
//                        myGroceryList.addItem(groceryListItem);
//                        groceryListAdapter.notifyItemChanged(position);
//                    }
//                });

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

        }else {
            StaplesList.clearStaplesList(this);
            Toast.makeText(getApplicationContext(),"Staples List Cleared",
                    Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
