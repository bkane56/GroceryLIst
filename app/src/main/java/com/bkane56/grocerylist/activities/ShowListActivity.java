package com.bkane56.grocerylist.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.Helpers.ItemClickSupport;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.adapter.GroceryListAdapter;
import com.bkane56.grocerylist.adapter.StaplesListAdapter;
import com.bkane56.grocerylist.items.GroceryListItem;
import com.bkane56.grocerylist.items.StaplesListItem;

import java.util.ArrayList;
import java.util.List;

import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;

public class ShowListActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarDrawerToggle mDrawerToggle;
    private int qty = 1;
    private GroceryListItem mItem;
    private static GroceryListAdapter groceryListAdapter = null;
    private View containerView;
    private static List<GroceryListItem> groceryData;
    private GroceryList myGroceryList;
    private Toolbar myToolbar;
    private StaplesList mStaplesList;
    StaplesListAdapter staplesListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_list);

        TransitionInflater inflater = TransitionInflater.from(this);
        Transition transition = inflater.inflateTransition(R.transition.transition_explode_fade);
        transition.setDuration(1000);
        getWindow().setReenterTransition(transition);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);

        myGroceryList = new GroceryList(this);

        mStaplesList = new StaplesList(this);

        groceryData = myGroceryList.getGroceryList();

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        final RecyclerView groceryRecyclerView = (RecyclerView) findViewById(R.id.rv_grocery_list);
        groceryListAdapter = new GroceryListAdapter(this, groceryData);
        groceryRecyclerView.setAdapter(groceryListAdapter);
        groceryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.add_new).setOnClickListener(this);
        findViewById(R.id.scan_item).setOnClickListener(this);
        findViewById(R.id.staples).setOnClickListener(this);
//        findViewById(R.id.add_all).setOnClickListener(this);
//        findViewById(R.id.finised).setOnClickListener(this);
////
//        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
//                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//
//                    @Override
//                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                        //Remove swiped item from list and notify the RecyclerView
//                        groceryListAdapter.delete(viewHolder.getAdapterPosition());
//                        groceryListAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//                    }
//                };
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        ItemClickSupport.addTo(groceryRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                GroceryListItem groceryItem = groceryData.get(position);
                int qty = Integer.parseInt(groceryItem.getQuantity());
                groceryItem.setQuantity(++qty);
                myGroceryList.updateItem(position, new GroceryListItem(qty, groceryItem.getGroceryItem()));
                groceryListAdapter.notifyItemChanged(position);
            }
        });

        ItemClickSupport.addTo(groceryRecyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                GroceryListItem groceryItem = groceryData.get(position);
                String itneName = groceryItem.getGroceryItem();
                confirmDelete(itneName, groceryItem, position).show();

                return true;
            }
        });



        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                groceryRecyclerView,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        int id = groceryRecyclerView.getChildAdapterPosition(view);
                        groceryListAdapter.delete(id);
                        groceryListAdapter.notifyDataSetChanged();

                        Toast.makeText(getBaseContext(), String.format("Delete item %d",id), Toast.LENGTH_LONG).show();
                    }
                })
                .setIsVertical(false)
                .setItemTouchCallback(
                        new SwipeDismissRecyclerViewTouchListener.OnItemTouchCallBack() {
                            @Override
                            public void onTouch(int index) {
//                                showDialog(String.format("Click item %d", index));
                            }
                        })
                .create();

        groceryRecyclerView.setOnTouchListener(listener);

    }

    private List<GroceryListItem> retrieveGroceryList(View view){

        List<GroceryListItem> myList = new GroceryList(this).getGroceryList();
        if(myList == null){
            myList = new ArrayList<GroceryListItem>();

        }
        return myList;
    }

    private void addItemToGroceryList(View view, GroceryListItem item){
        myGroceryList.addItem(item);
    }

    public static GroceryListAdapter getGroceryListAdapter() {
        return groceryListAdapter;
    }

    @Override
     public void onClick(View v) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        final List<StaplesListItem> staplesData = mStaplesList.getStaplesList();

        switch (v.getId()) {
            case R.id.staples:

////                ViewGroupUtils.replaceView(findViewById(R.id.show_list_layout), findViewById(R.id.staples_list));
//                setContentView(R.layout.staples_layout);
//
                startActivity(new Intent(this, StaplesActivity.class), compat.toBundle());
                break;
            case R.id.scan_item:
                startActivity(new Intent(this, ScanItemActivity.class), compat.toBundle());
                break;
            case R.id.add_new:
                startActivity(new Intent(this, AddItemsToListActivity.class), compat.toBundle());
                break;
            case R.id.finised:
                startActivity(new Intent(this, ShowListActivity.class), compat.toBundle());
                break;
            case R.id.add_all:
                addAllStaples();
            default:
                break;
        }
    }
    private void addAllStaples() {

        List<StaplesListItem> mStapleList = mStaplesList.getStaplesList();

        for(StaplesListItem stapleItem : mStapleList){
            GroceryListItem groceryListItem =new GroceryListItem(1, stapleItem.getStaplesItem());
            myGroceryList.addItem(groceryListItem);
            groceryListAdapter.notifyItemInserted(myGroceryList.getSize());

        }
    }

    public void startScan(View v){

//        ActivityOptionsCompat compat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "shared_item");
//            startActivity(new Intent(this, ScanItemActivity.class), compat.toBundle());
        startActivity(new Intent(this, ScanItemActivity.class));
    }

    private AlertDialog confirmDelete (String itemName, final GroceryListItem groceryListItem, final int position) {
        AlertDialog removeItemDialog =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Remove " + itemName)
                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
//                        GroceryList.removeItem(getApplicationContext(), groceryListItem);
                        groceryListAdapter.delete(position);
                        groceryListAdapter.notifyItemChanged(position);
                        myGroceryList.removeItem(position);

                        dialog.dismiss();
                    }

                })
                .setNeutralButton("Subtract 1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GroceryListItem groceryItem = groceryData.get(position);
                        int qty = Integer.parseInt(groceryItem.getQuantity());
                        if (qty > 1) {
                            groceryItem.setQuantity(--qty);
                            myGroceryList.updateItem(position, new GroceryListItem(qty, groceryItem.getGroceryItem()));
                            groceryListAdapter.notifyItemChanged(position);
                        }
                        dialog.dismiss();
                    }
                })

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return removeItemDialog;

    }

    public static void refreshData(List<GroceryListItem> newData){
        groceryData = newData;
        getGroceryListAdapter().notifyDataSetChanged();
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
        }else if(id == R.id.clear_groceries){
            int listLength = groceryData.size();
            for(int i = listLength -1; i >= 0; i--) {
                groceryData.remove(i);
                groceryListAdapter.notifyItemRemoved(i);
            }
            Toast.makeText(getApplicationContext(), "Grocery List Cleared",
                    Toast.LENGTH_SHORT).show();
        }else {
            mStaplesList.clearStaplesList();
            Toast.makeText(getApplicationContext(),"Staples List Cleared",
                    Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
