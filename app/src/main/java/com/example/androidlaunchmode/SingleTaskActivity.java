package com.example.androidlaunchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/10.
 */

public class SingleTaskActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

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
                startActivity(new Intent(SingleTaskActivity.this, StandardActivity.class));
            }
        });

        findViewById(R.id.btn_singleTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleTaskActivity.this, SingleTopActivity.class));
            }
        });

        findViewById(R.id.btn_singleTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleTaskActivity.this, SingleTaskActivity.class));
            }
        });

        findViewById(R.id.btn_singleInstance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleTaskActivity.this, SingleInstanceActivity.class));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 系统可能因为随时杀掉后台运行的Activity，如果发生这个情况，只会调用onCreate方法，不会调用onNewIntent。好的解决办法就是在onCreate和onNewIntent调用同一个处理方法
        Log.d(TAG, "onNewIntent: 当Task栈里面存在这个singleTask模式的Act的时候，且该Act没有被系统kill的时候（如果被kill了，只会调用onCreate不调用onNewIntent），就不会运行onCreate，而是直接发给onNewIntent");
        // 复制给Activity新的intent，否则得到的都是旧的Intent
        setIntent(intent);
    }
}
