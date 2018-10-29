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
        resultManager = new ActivityResultManager(MainActivity.this);
        findViewById(R.id.btn_start_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                resultManager.setResultCallbackListenter(new IResultCallback() {
                    @Override
                    public void getResultCallback(Intent data) {
                        String s = data.getStringExtra("data");
                        Toast.makeText(MainActivity.this, "结果：" + s, Toast.LENGTH_SHORT).show();
                    }
                })
                        .startActivityForResult(intent, 01)
                ;
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (resultCode == RESULT_OK) {
//            if (requestCode == ActivityResultManager.mRequestCode) {
//                String s = data.getStringExtra("data");
//                Log.e(TAG, "onActivityResult: " + s);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
