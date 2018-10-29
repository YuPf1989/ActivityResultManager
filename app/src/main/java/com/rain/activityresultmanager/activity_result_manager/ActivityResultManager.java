package com.rain.activityresultmanager.activity_result_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Author:rain
 * Date:2018/10/29 10:45
 * Description:
 * 专门用于处理startActivityForResult的工具类
 */
public class ActivityResultManager {
    private  FragmentActivity mActivity;
    private FragmentManager fragmentManager;
    private BindFragment bindFragment;
    private static final String BINDFRAGMENT  = "BINDFRAGMENT";
    public static int mRequestCode;
    public static IResultCallback mResultCallback;

    public ActivityResultManager(FragmentActivity activity) {
        mActivity = activity;
        bindFragment();
    }

    public ActivityResultManager startActivityForResult(Intent intent,int requestCode) {
        mRequestCode = requestCode;
        // 注意必须用fragment调用，才能在fragment中的onActivityResult中获取到值
        bindFragment.startActivityForResult(intent,requestCode);
        return this;
    }

    public ActivityResultManager startActivityForResult(Intent intent, int requestCode, Bundle bundle) {
        mRequestCode = requestCode;
        bindFragment.startActivityForResult(intent,requestCode,bundle);
        return this;
    }

    private void bindFragment() {
        fragmentManager = mActivity.getSupportFragmentManager();
        bindFragment = BindFragment.newInstance();
        fragmentManager.beginTransaction().add(bindFragment,BINDFRAGMENT).commit();
    }

    public ActivityResultManager setResultCallbackListenter(IResultCallback resultCallback) {
        mResultCallback = resultCallback;
        return this;
    }
}
