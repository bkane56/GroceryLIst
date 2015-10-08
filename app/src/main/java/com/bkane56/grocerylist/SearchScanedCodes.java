package com.bkane56.grocerylist;


import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bkane56.grocerylist.Helpers.VolleyHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchScanedCodes {

    public static String getCode(String code){

        return findCode(code);

    }

    private static String findCode (String code) {
//
//        String url = "http://api.upcdatabase.org/json/66f737fd6acd8c0ea5adb6caf891cec4/" + code;
//        String product;
//        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);
//
//        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        tvResult.setText("Response: " + response.toString());
//                        String textResult = "";
//                        try {
//                            JSONArray arrProducts = response.getJSONArray("products");
//                            for(int i=0; i<arrProducts.length(); i++){
//                                JSONObject productItem = (JSONObject)arrProducts.get(i);
//                                textResult += "Name: " + productItem.getString("name") + "\n";
//                                textResult += "Description: " + productItem.getString("description") + "\n";
//                                textResult += "Price: $" + productItem.getString("price") + "\n\n";
//                            }
//                            tvResult.setText(textResult);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if(error != null) Log.e("MainActivity", error.getMessage());
//
//                    }
//                });
//
//
//    }


//    private static String convertStreamToString(InputStream is) {
//    /*
//     * To convert the InputStream to String we use the BufferedReader.readLine()
//     * method. We iterate until the BufferedReader return null which means
//     * there's no more data to read. Each line will appended to a StringBuilder
//     * and returned as String.
//     */
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
        return null;
    }

}
