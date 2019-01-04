package io.github.hqglichao.crashautorestartdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.github.hqglichao.crashautorestartdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding = null;
    private HandleServiceConnection handleServiceConnection;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setMainViewModel(new MainViewModel());
        startService();
    }

    private void startService() {
        serviceIntent = new Intent(this, OnClearFromRecentService.class);
        startService(serviceIntent);
        bindService();
    }

    private void unBindService() {
        unbindService(handleServiceConnection);
        handleServiceConnection = null;
    }

    private void bindService() {
        handleServiceConnection = new HandleServiceConnection();
        bindService(serviceIntent, handleServiceConnection, BIND_AUTO_CREATE);
    }

    private class HandleServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                Log.d(Constants.VERSION1, "onServiceConnected");
                service.linkToDeath(new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        Log.e(Constants.VERSION1, "remote died.");
                        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                        startActivity(intent);
                        unBindService();
                        bindService();
                    }
                }, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(Constants.VERSION1, "onServiceDisconnected");
        }

        @Override
        public void onNullBinding(ComponentName name) {
            Log.e(Constants.VERSION1, "onNullBinding.");
        }

        @Override
        public void onBindingDied(ComponentName name) {
            Log.e(Constants.VERSION1, "onBindingDied.");
        }
    }
}
