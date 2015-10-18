package com.bkane56.grocerylist.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bkane56.grocerylist.GroceryList;
import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.items.GroceryListItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ScanItemActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    TextView formatTxt;
    TextView contentTxt;
    private String url;
    private GroceryList mGroceryList;
    private StaplesList mStaplesLest;

    public ScanItemActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Explode explode = new Explode();
        explode.setDuration(1000);
        getWindow().setEnterTransition(explode);
        setContentView(R.layout.activity_scan_item);

        Button scanBtn = (Button)findViewById(R.id.scan_item);
        Button finBtn = (Button) findViewById(R.id.finised_scan);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        mGroceryList = new GroceryList(this);
        mStaplesLest = new StaplesList(this);

        scanBtn.setOnClickListener(this);
        finBtn.setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.scan_item){

            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        if(v.getId() == R.id.finised_scan){
            ActivityOptionsCompat compat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);

            startActivity(new Intent(this, ShowListActivity.class), compat.toBundle());

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult
                (requestCode, resultCode, intent);

        if (scanningResult != null) {
//           if code is not null pass procuct code to url
            setUrl(scanningResult.getContents());
//              call async lookup
            new FindProductName().execute("");
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class FindProductName extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            String item = "";

            try {

                // need http protocol
                Document doc = Jsoup.connect(getUrl()).get();
//                set error check info
                Element valid = doc.select("valid").first();
                Element reason = doc.select("reason").first();
//                handle null value for barcode
                if(valid.text().equals("false")){
                    item = reason.text();

                }else {
                    // get product name as element (title and name)
                    Element product = doc.select("itemname").first();
                    //        get the vale of itemname
                    item = product.text();
                    addItem(item);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return item;
        }
        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.scan_content);
            txt.setText(result + " added to list.");

        }
    }

    public String getUrl(){
        return url;
    }

    private void setUrl (String code) {
        url = "http://api.upcdatabase.org/xml/66f737fd6acd8c0ea5adb6caf891cec4/" + code;

    }
    private void addItem(String groceryItem){
        GroceryListItem mItem = new GroceryListItem(1, groceryItem);
        mGroceryList.addItem(mItem);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scan_item, menu);
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
            Toast.makeText(getApplicationContext(),"Grocery List Cleared",
                    Toast.LENGTH_SHORT).show();

        }else {
            mStaplesLest.clearStaplesList();
            Toast.makeText(getApplicationContext(),"Staples List Cleared",
                    Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
