package com.bkane56.grocerylist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.bkane56.grocerylist.R;

import java.util.concurrent.TimeUnit;

public class AddItemsToListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this)
                .inflateTransition(R.transition.shared_element_transition));
//        Slide slide = new Slide();
//        slide.setDuration(1000);
//        getWindow().setEnterTransition(slide);

        setContentView(R.layout.activity_add_items_to_list);
        animateText();

    }

    private void animateText(){

        TextView mText = (TextView) findViewById(R.id.imagine);

        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        mText.startAnimation(in);
        mText.setVisibility(View.VISIBLE);
        in.setFillAfter(true);

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