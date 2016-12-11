package com.example.androidlaunchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SingleTopActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onClick: " + "当前Activity=" + this.toString() + "\n" + "当前Task=" + this.getTaskId());
        String s = "当前Activity=" + this.toString() + "\n" + "当前Task=" + this.getTaskId();
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(s);

        findViewById(R.id.btn_standard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleTopActivity.this, StandardActivity.class));
            }
        });

        findViewById(R.id.btn_singleTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleTopActivity.this, SingleTopActivity.class));
            }
        });

        findViewById(R.id.btn_singleTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleTopActivity.this, SingleTaskActivity.class));
            }
        });

        findViewById(R.id.btn_singleInstance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleTopActivity.this, SingleInstanceActivity.class));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }
}
