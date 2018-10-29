package com.rain.activityresultmanager.activity_result_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

/**
 * Author:rain
 * Date:2018/10/29 10:53
 * Description:
 * 用于处理activity onActivityResult结果的fragment
 * 注意默认的result_code 为 RESULT_OK
 */
public class BindFragment extends Fragment {
    private static final String TAG = "BindFragment";
    private FragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        super.onCreate(savedInstanceState);
    }

    public static BindFragment newInstance() {
        Bundle args = new Bundle();
        BindFragment fragment = new BindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ActivityResultManager.mRequestCode) {
                if (ActivityResultManager.mResultCallback != null) {
                    ActivityResultManager.mResultCallback.getResultCallback(data);
                }
            }
        } else {
            Toast.makeText(mActivity, "用户取消", Toast.LENGTH_SHORT).show();
        }
//        removeFragment();
    }

    private void removeFragment() {
        getFragmentManager().beginTransaction().remove(this);
    }
}
