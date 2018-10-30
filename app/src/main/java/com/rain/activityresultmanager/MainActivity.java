package com.rain.activityresultmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rain.activityresultmanager.activity_result_manager.ActivityResultManager;
import com.rain.activityresultmanager.activity_result_manager.IResultCallback;

/**
 * 专门处理startActivityForResult的demo
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityResultManager resultManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 必须在activity生命周期方法中初始化
//        resultManager = new ActivityResultManager(MainActivity.this);
        findViewById(R.id.btn_start_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                new ActivityResultManager(MainActivity.this)
                        .startForResult(SecondActivity.class, 0, new IResultCallback() {
                            @Override
                            public void getResultCallback(int requestCode, int resultCode, Intent data) {
                                String s = data.getStringExtra("data");
                                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}
