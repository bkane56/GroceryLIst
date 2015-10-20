package com.bkane56.grocerylist.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
            mEditText.setText("");
        } else{
            Toast.makeText(this, "You Did Not Enter an Item!",Toast.LENGTH_SHORT).show();
        }

    }

    public void addToBothLists(View v){

        StaplesListItem staplesListItem = null;
        if(!mEditText.getText().toString().equals("")) {
            showStapleDialog(v);
            GroceryListItem groceryListItem = getProduct();
            String mItem = groceryListItem.getGroceryItem();
            if(mStapleType == null) {
                staplesListItem = new StaplesListItem(mItem);
            }else {
//                staplesListItem = getCorrectItem(mStapleType);
                switch(mStapleType){
                    case "Bakery":
                        staplesListItem = new BakeryItem(mItem);
                        break;
                    case "Canned":
                        staplesListItem = new CannedItem(mItem);
                        break;
                    case "Cleaning":
                        staplesListItem = new CleaningItem(mItem);
                        break;
                    case "Dairy":
                        staplesListItem = new DairyItem(mItem);
                        break;
                    case "Meat":
                        staplesListItem = new MeatItem(mItem);
                        break;
                    case "Personal":
                        staplesListItem = new PersonalCareItem(mItem);
                        break;
                    case "Produce":
                        staplesListItem = new ProduceItem(mItem);
                        break;
                    default:
                        break;
                }
            }
            myGrocreyList.addItem(getProduct());
            mStaplesList.addItem(staplesListItem);
            mEditText.setText("");
//            mStapleType = "";

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
        transition.setDuration(750);
        getWindow().setReenterTransition(transition);
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
                mStapleType = txt.getText().toString().replace(" items", "");

                Toast.makeText(AddItemsToListActivity.this, "Added Item To " +
                                mStapleType + " List",
                    Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showStapleDialog(View view){

        AlertDialog.Builder builder=new
                AlertDialog.Builder(AddItemsToListActivity.this);
        builder.setTitle("Tap To Add Staples To That List");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", null);
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
