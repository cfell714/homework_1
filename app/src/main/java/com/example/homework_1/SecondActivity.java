package com.example.homework_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class SecondActivity extends AppCompatActivity {
    private TextView textView_retrieve;
    private Button button_submit;
    private NestedScrollView scrollView_scroll;
    private LinearLayout linearlayout_second;

    private static final String api_url =  "http://madlibz.herokuapp.com/api/random?minlength=5&maxlength=25";
    private static AsyncHttpClient client = new AsyncHttpClient();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        button_submit = findViewById(R.id.button_submit);
        scrollView_scroll = findViewById(R.id.scrollView_scroll);
        textView_retrieve = findViewById(R.id.textView_retrieve);
        linearlayout_second = findViewById(R.id.linearlayout_second);

        Intent intent = getIntent();
        textView_retrieve.setText(intent.getStringExtra("title"));

        ArrayList<String> test = intent.getStringArrayListExtra("blanks");
        ArrayList<String> test2 = intent.getStringArrayListExtra("value");

        ArrayList<String> answers = new ArrayList<String>();

        int N = test.size();
        final TextView[] myTextViews = new TextView[N];
        final EditText[] myEditText = new EditText[N];

        for (int i = 0; i < N; i++) {
            String object = test.get(i);
            final TextView rowTextView = new TextView(this);

            rowTextView.setText(object);
            linearlayout_second.addView(rowTextView);

            myTextViews[i] = rowTextView;
            myTextViews[i].setTextSize(20);

            final EditText rowEditText = new EditText(this);
            linearlayout_second.addView(rowEditText);
            myEditText[i] = rowEditText;

        }

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(myEditText)) {
                    for(int k = 0; k < N; k++){
                        String stripping = myEditText[k].getText().toString();
                        //System.out.println(stripping);
                        answers.add(stripping);
                    }
                    launchNextActivity(v, test2, answers);
                }else {
                    notValid(v);
                }
            }
        });
    }

    private boolean validate(EditText[] fields){
        for(int i = 0; i < fields.length; i++){
            EditText currentField = fields[i];
            if(currentField.getText().toString().length() <= 0){
                return false;
            }
        }
        return true;
    }

    public void notValid(View view){
        Toast toast = Toast.makeText(this, "There is an open field", Toast.LENGTH_SHORT);
        toast.show();
    }


    public void launchNextActivity(View view, ArrayList<String> test2, ArrayList<String> answers){
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

        String newString = "";

        for (int i = 0; i < test2.size(); i++){
            String object = test2.get(i);
            String object2 = answers.get(i).trim();

            newString = newString + object;
            newString = newString + object2;
        }

        intent.putExtra("value", (String) newString);
        startActivity(intent);
        }

}
