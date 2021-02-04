package com.example.homework_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button button_main_next;
    private List<String> all_blanks;
    private List<String> all_values;

    private static final String api_url =  "http://madlibz.herokuapp.com/api/random?minlength=5&maxlength=25";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_main_next = findViewById(R.id.button_main_next);

        button_main_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                launchNextActivity(v);
            }
        });

    }
    public void launchNextActivity(View view){
        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                try {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    JSONObject json = new JSONObject(new String(responseBody));
                    intent.putExtra("title", json.getString("title"));

                    all_blanks = new ArrayList<String>();
                    all_values = new ArrayList<String>();

                    JSONArray arr1 = json.getJSONArray("blanks");
                    JSONArray arr2 = json.getJSONArray("value");

                    for (int i = 0; i < arr1.length(); i++){
                        String object = arr1.getString(i);
                        String object2 = arr2.getString(i);

                        all_values.add(object2);
                        all_blanks.add(object);
                    }

                    intent.putStringArrayListExtra("blanks", (ArrayList<String>) all_blanks);
                    intent.putStringArrayListExtra("value", (ArrayList<String>) all_values);

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));
            }
        });
    }
}