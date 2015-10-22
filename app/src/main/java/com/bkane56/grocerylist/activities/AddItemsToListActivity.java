package com.bkane56.grocerylist.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.adapter.GroceryListAdapter;
import com.bkane56.grocerylist.items.GroceryListItem;
import com.bkane56.grocerylist.items.StaplesListItem;
import com.bkane56.grocerylist.items.staples.BakeryItem;
import com.bkane56.grocerylist.items.staples.CannedItem;
import com.bkane56.grocerylist.items.staples.CleaningItem;
import com.bkane56.grocerylist.items.staples.DairyItem;
import com.bkane56.grocerylist.items.staples.MeatItem;
import com.bkane56.grocerylist.items.staples.PersonalCareItem;
import com.bkane56.grocerylist.items.staples.ProduceItem;

import java.util.ArrayList;
import java.util.List;


public class AddItemsToListActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEditText;
    private int qty = 1;
    private Context context;
    private GroceryList myGrocreyList;
    private GroceryListAdapter mGroceryListAdapter;
    private StaplesList mStaplesList;
    private ListView listView;
    private String mStapleType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = context;

        setTransition();

        setupStaplesDialog();

        myGrocreyList = new GroceryList(this);
        mGroceryListAdapter = ShowListActivity.getGroceryListAdapter();
        mStaplesList = new StaplesList(this);


        setContentView(R.layout.activity_add_items_to_list);
        mEditText = (EditText) findViewById(R.id.et_add_item);
        findViewById(R.id.add1).setOnClickListener(this);
        findViewById(R.id.subtract1).setOnClickListener(this);
        findViewById(R.id.btn_add_finish).setOnClickListener(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void addToList(View v) {
        if (!mEditText.getText().toString().equals("")) {
            myGrocreyList.addItem(getProduct());
            mGroceryListAdapter.notifyItemInserted(mGroceryListAdapter.getItemCount());
            mEditText.setText("");
        } else{
            Toast.makeText(this, "You Did Not Enter an Item!",Toast.LENGTH_SHORT).show();
        }

    }

    public void addToBothLists(View v){

        if(!mEditText.getText().toString().equals("")) {
            showStapleDialog(v);

        } else {
            Toast.makeText(this, "You Did Not Enter an Item!", Toast.LENGTH_SHORT).show();
        }
    }
    private GroceryListItem getProduct (){
        return new GroceryListItem(qty, mEditText.getText().toString());
    }

    private StaplesListItem getCorrectItem(String stapleType){
        StaplesListItem switchItem = null;
        switch(stapleType){
            case "Bakery":
                switchItem = new BakeryItem();
                break;
            case "Canned":
                switchItem = new CannedItem();
                break;
            case "Cleaning":
                switchItem = new CleaningItem();
                break;
            case "Dairy":
                switchItem = new DairyItem();
                break;
            case "Meat":
                switchItem = new MeatItem();
                break;
            case "Personal":
                switchItem = new PersonalCareItem();
                break;
            case "Produce":
                switchItem = new ProduceItem();
                break;
            default:
                break;
        }
        return switchItem;
    }


    private void animateText(){

        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1250);
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        in.setFillAfter(true);

    }

    public void setTransition(){

        TransitionInflater inflater = TransitionInflater.from(this);
        Transition transition = inflater.inflateTransition(R.transition.transition_explode_fade);
        Transition slideLeft = inflater.inflateTransition(R.transition.slide_left);
        transition.setDuration(750);
        getWindow().setExitTransition(slideLeft);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);
    }

    public void setupStaplesDialog(){

        listView = new ListView(this);
        // Add data to the ListView
        String[] items = this.getResources().getStringArray(R.array.staples_drawer_lables);;
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.list_item, R.id.txtitem, items);
        listView.setAdapter(adapter);
        // Perform action when an item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                ViewGroup vg = (ViewGroup) view;
                TextView txt = (TextView) vg.findViewById(R.id.txtitem);
                List<StaplesListItem>  mStapleData = mStaplesList.getStaplesList();
                String newStapleType = txt.getText().toString();
                String[] mString = newStapleType.split(" ");
                String mType = mString[0];

                GroceryListItem groceryListItem = getProduct();
                String mItem = groceryListItem.getGroceryItem();
                StaplesListItem staplesListItem = new StaplesListItem(mItem);

                staplesListItem.setStapleType(mType);
                myGrocreyList.addItem(getProduct());
                List<String> stapleNameList = new ArrayList<String>();
                for(StaplesListItem item : mStapleData) {
                    stapleNameList.add(item.getStaplesItem());
                }

                if(stapleNameList.contains(mItem)) {
                    Toast.makeText(AddItemsToListActivity.this, mItem + " Is Already In  " +
                                    mType + " List",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddItemsToListActivity.this, "Added " + mItem + " To " +
                                    mType + " List",
                            Toast.LENGTH_SHORT).show();
                    mStaplesList.addItem(staplesListItem);
                }
                mEditText.setText("");
                vg.removeView(view);

            }
        });
    }
    private void addNewItemsToBoth(GroceryListItem gItem, StaplesListItem sItem){
        myGrocreyList.addItem(getProduct());
        mStaplesList.addItem(sItem);
        mEditText.setText("");
    }

    public void showStapleDialog(View view){

        AlertDialog.Builder builder=new
                AlertDialog.Builder(AddItemsToListActivity.this);
        builder.setTitle("Tap To Add Staples To That List");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Basic Items", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                GroceryListItem groceryListItem = getProduct();
                if(groceryListItem != null) {
                    String mItem = groceryListItem.getGroceryItem();
                    StaplesListItem staplesListItem = new StaplesListItem(mItem);
                    myGrocreyList.addItem(groceryListItem);
                    List<StaplesListItem>  mStapleData = mStaplesList.getStaplesList();
                    if(!mStapleData.contains(staplesListItem)) {
                        mStaplesList.addItem(staplesListItem);
                        dialog.dismiss();
                    }
                }
            }
        });
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
      public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add1:
                qty++;
                break;
            case R.id.subtract1:
                qty--;
                break;
            case R.id.btn_add_finish:
                ActivityOptionsCompat compat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
                startActivity(new Intent(this, ShowListActivity.class), compat.toBundle());
                break;
            default:
                break;
        }

        if (qty < 1) {
            qty = 1;
        }
        ((TextView) findViewById(R.id.amount)).setText(Integer.toString(qty));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_items_to_list, menu);
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
