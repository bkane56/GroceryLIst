package com.bkane56.grocerylist.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.adapter.GroceryListAdapter;
import com.bkane56.grocerylist.items.GroceryListItem;
import com.bkane56.grocerylist.items.StaplesListItem;


public class AddItemsToListActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEditText;
    private int qty = 1;
    private Context context;
    private GroceryList myGrocreyList;
    private GroceryListAdapter mGroceryListAdapter;
    private StaplesList mStaplesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = context;
        myGrocreyList = new GroceryList(this);
        mGroceryListAdapter = ShowListActivity.getGroceryListAdapter();
        mStaplesList = new StaplesList(this);

        getWindow().setEnterTransition(TransitionInflater.from(this)
                .inflateTransition(R.transition.transition_explode_fade));

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

        myGrocreyList.addItem(getProduct());
        mEditText.setText("");
    }


    public void addToBothLists(View v){

        GroceryListItem groceryListItem = getProduct();
        String mItem = groceryListItem.getGroceryItem();
        StaplesListItem staplesListItem = new StaplesListItem(mItem);

        myGrocreyList.addItem(getProduct());
        mStaplesList.addItem(staplesListItem);
        mEditText.setText("");;

    }
    private GroceryListItem getProduct (){
        return new GroceryListItem(qty, mEditText.getText().toString());

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
