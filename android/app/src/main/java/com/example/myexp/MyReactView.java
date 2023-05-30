package com.example.myexp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.hermes.reactexecutor.HermesExecutor;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.*;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MyReactView extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_react_view);
        SoLoader.init(this, false);
        mReactRootView = new ReactRootView(this);
        List<ReactPackage> packages =  new PackageList(getApplication()).getPackages();

        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
                .addPackages(packages)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .setJavaScriptExecutorFactory( new HermesExecutorFactory())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "MyExp", null);

        setContentView(mReactRootView);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted
                }
            }
        }
        mReactInstanceManager.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }


}