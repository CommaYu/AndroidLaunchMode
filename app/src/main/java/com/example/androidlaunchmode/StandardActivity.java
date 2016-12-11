package com.example.androidlaunchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 概述：
 * 总是自以为是的觉得懂了，但是到头了还是模模糊糊，模模糊糊跟没懂没啥区别
 * Activity都在Task栈中
 *
 * 重点：
 *      4中模式相互联系，也有区别。弄懂onNewIntent这个方法。
 *
 * 壹.standard:目标Act存在或不存在，在不在栈顶，都会重新创建一个新的Act（新的Act是指hashCode不一样的。）
 *      特点：有多个不同的hashCode的Act
 *      onNewIntent:貌似没看到啥时候会复用，啥时候用这个方法？未知。
 * 贰.singleTop:目标Act不存在，会新创建一个Act；目标Act存在，1.不在栈顶（仍会重新创建，而不是复用，跟standard模式相同） 2.在栈顶（不会重新创建，程序停留在当前目标Act界面）（跟standard的唯一区别）
 *      特点：有多个不同的hashCode的Act，跟standard唯一区别是：不在栈顶，重新创建新的Act；在栈顶就不会新建，会停在当前的standard界面
 *      【singleTop模式Act在栈顶 ? 在栈顶，停留在当前Act不新建 : 不在栈顶，重新创建，不复用】
 *      onNewIntent:复用的时候，会调用onNewIntent方法
 * 叁.singleTask:目标Act不存在，新建一个Act；目标Act存在，1.如果目标Act在栈顶，那么和singleTop一样 2.不在栈顶，系统会将Task栈中该Act之上的Act全部移除Task栈，但是Act下面的不会移除
 *      特点：仅有一个hashCode相同的Act，跟standard和singleTop有共同也有不同，唯一和singleTop不同的就是他只要创建了，就永远复用那一个，然后把在他上面的挤出去。（复用的时候，不会运行onCreate）
 *      【singleTask模式Act在栈顶 ? 在栈顶，直接复用，不新建 : 不在栈顶，直接复用，不新建，挤出它之上的Act，下面的不挤出】
 *      【注意onNewIntent方法，当复用的时候，不会运行onCreate，只会运行onNewIntent，记得setIntent(intent);】
 *      onNewIntent:复用的时候，会调用onNewIntent方法
 *      （注意：系统可能因为随时杀掉后台运行的Activity，如果发生这个情况，只会调用onCreate方法，不会调用onNewIntent。好的解决办法就是在onCreate和onNewIntent调用同一个处理方法）
 * 肆.singleInstance:重新创建了一个唯一的Task，一个唯一的Act
 *      特点：不同的singleInstance模式的Act，有不同的Task，有且只有一个实例（跟singleTask的唯一区别，就是在不同的Task栈
 *      【singleInstance模式Act存在 ? 复用该Task下的Act : 重新创建Task创建Act】
 *      onNewIntent:复用的时候，也同样是调用onNewIntent方法。跟singleTask一毛一样。
 *
 *      在sAndroidManifest.xml中设置android:exported="true"，这个就可以被其他应用调用了。貌似短信程序用的应该是这个（各种其他app都调用这个Act，然后都是一个，而且不会重新启动，付款界面也可以一样）
 *      别的app如何启动本app的SingleInstanceActivity类呢？隐式调用。startActivity(new Intent().setAction("app.SingleInstanceActivity"));
 *      (广播中android5.0，是不可以隐式调用的)
 *
 * 总结：
 *  1.发现一个bug，当我们extends Activity的时候，设置成singleTask和singleInstance的时候ActionBar不显示，只有继承APCompatibleActivity才可以
 *  2.singleTask和singleInstance复用的时候，都会调用onNewIntent方法
 *
 *  面试题：
 *  1.复用的时候会调用哪个方法？
 */
public class StandardActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    String s1 = new String("test");
    String s2 = "123shi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: s1=" + (s1 = "s1改变了"));
        Log.d(TAG, "onCreate: s2=" + (s2 = "s2改变了"));

        Log.d(TAG, "onClick: " + "当前Activity=" + this.toString() + "\n" + "当前Task=" + this.getTaskId());
        String s = "当前Activity=" + this.toString() + "\n" + "当前Task=" + this.getTaskId();
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(s);

        findViewById(R.id.btn_standard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StandardActivity.this, StandardActivity.class));
            }
        });

        findViewById(R.id.btn_singleTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StandardActivity.this, SingleTopActivity.class));
            }
        });

        findViewById(R.id.btn_singleTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StandardActivity.this,SingleTaskActivity.class));
            }
        });

        findViewById(R.id.btn_singleInstance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StandardActivity.this,SingleInstanceActivity.class));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }
}
