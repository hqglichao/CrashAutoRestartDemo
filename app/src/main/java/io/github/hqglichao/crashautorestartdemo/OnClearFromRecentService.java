package io.github.hqglichao.crashautorestartdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author beyond
 * @date 19-1-3
 */
public class OnClearFromRecentService extends Service {
    private RemoteBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new RemoteBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.VERSION1, "Service onUnbind.");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.VERSION1, "Service Started");
        Toast.makeText(this, "on start command", Toast.LENGTH_LONG).show();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.VERSION1, "Service Destroyed");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e(Constants.VERSION1, "END");
        Toast.makeText(this, "on task removed", Toast.LENGTH_LONG).show();
        //Code here
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        stopSelf();
    }
}
