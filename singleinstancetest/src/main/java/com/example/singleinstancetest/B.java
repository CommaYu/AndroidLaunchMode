package com.example.singleinstancetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/12/10.
 */

public class B extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onClick: " + "当前Activity=" + this.toString() + "\n" + "当前Task=" + this.getTaskId());
    }

    public void Main(View v) {
        startActivity(new Intent(B.this, A.class)
        );
    }

    public void A(View v) {
        startActivity(new Intent(B.this, B.class)
        );
    }

    public void B(View v) {
        startActivity(new Intent(B.this, MainActivity.class)
        );
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }
}
