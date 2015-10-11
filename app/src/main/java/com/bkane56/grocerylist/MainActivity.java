package com.bkane56.grocerylist;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bkane56.grocerylist.activities.AddItemsToListActivity;
import com.bkane56.grocerylist.activities.ScanItemActivity;
import com.bkane56.grocerylist.activities.ShowListActivity;

public class MainActivity extends AppCompatActivity
        implements FragmentDrawer.FragmentDrawerListener, View.OnClickListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private int elevation;
    View image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= 21) {
//            getWindow().setSharedElementExitTransition(TransitionInflater.from(this)
//                    .inflateTransition(R.transition.shared_element_transition));
//            TransitionInflater inflater = TransitionInflater.from(this);
//            Transition transition = inflater.inflateTransition(R.transition.transition_explode_fade);
//            transition.setDuration(1500);
//            getWindow().setReenterTransition(transition);

            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transition_explode_fade);
            transition.setDuration(1500);
            getWindow().setReenterTransition(transition);
            getWindow().setEnterTransition(transition);
            getWindow().setReenterTransition(transition);

        }

        setContentView(R.layout.activity_main);

        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.scan).setOnClickListener(this);
        findViewById(R.id.show).setOnClickListener(this);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);


    }



    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                startActivity(new Intent(this, ShowListActivity.class), compat.toBundle());
            case 1:
                startActivity(new Intent(this, ScanItemActivity.class), compat.toBundle());
            case 2:
                startActivity(new Intent(this, AddItemsToListActivity.class), compat.toBundle());
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.replacable_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public void runAddItems(View v){

        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "shared_item");
        startActivity(new Intent(this, AddItemsToListActivity.class), compat.toBundle());
    }

    public void runScanItem(View v){
        startActivity(new Intent(this, ScanItemActivity.class));
    }



    @Override
    public void onClick(View v) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);

        switch (v.getId()) {

            case R.id.show:
                startActivity(new Intent(this, ShowListActivity.class), compat.toBundle());
                break;
            case R.id.scan:
                startActivity(new Intent(this, ScanItemActivity.class), compat.toBundle());
                break;
            case R.id.add:
                startActivity(new Intent(this, AddItemsToListActivity.class), compat.toBundle());
                break;
            default:
                break;
        }
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
            GroceryList.clearGroceryList(this);
            Toast.makeText(getApplicationContext(),"Grocery List Cleared",
                    Toast.LENGTH_SHORT).show();

        }else {
            StaplesList.clearStaplesList(this);
            Toast.makeText(getApplicationContext(),"Staples List Cleared",
                    Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}