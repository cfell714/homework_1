package com.example.homework_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private TextView textView_testing;
    private Button button_goHome;
    private NestedScrollView scrollView_scroll2;
    private LinearLayout linearlayout_3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        button_goHome = findViewById(R.id.button_goHome);
        textView_testing = findViewById(R.id.textView_testing);
        scrollView_scroll2 = findViewById(R.id.scrollView_scroll2);
        linearlayout_3 = findViewById(R.id.linearlayout_3);

        Intent intent = getIntent();
        textView_testing.setText(intent.getStringExtra("value"));

        button_goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity(v);
            }
        });

    }

    public void launchNextActivity(View view){
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
