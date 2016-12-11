package com.example.singleinstancetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onClick: " + "当前Activity=" + this.toString() + "\n" + "当前Task=" + this.getTaskId());
    }

    public void Main(View v) {
        startActivity(new Intent(MainActivity.this, A.class));
    }

    public void A(View v) {
        startActivity(new Intent(MainActivity.this, B.class));
    }

    public void B(View v) {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    public void XXOO(View v) {
        startActivity(new Intent().setAction("app.SingleInstanceActivity"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }

}
