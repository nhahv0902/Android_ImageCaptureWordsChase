package com.nhahv.imagecapturewordschase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nhahv.imagecapturewordschase.R;

public class StartActivity extends AppCompatActivity {

    private Button mBtnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        initViews();
        addEvents();
    }

    private void initViews() {
        mBtnPlay = (Button) findViewById(R.id.btn_start);
    }

    private void addEvents() {
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });
    }

}
