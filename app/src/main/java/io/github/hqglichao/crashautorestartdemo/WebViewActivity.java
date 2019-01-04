package io.github.hqglichao.crashautorestartdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

/**
 * @author beyond
 * @date 19-1-3
 */
public class WebViewActivity extends Activity {
    private final String WEB_URL = "https://www.baidu.com";
    private LinearLayout llWebLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        llWebLayout = findViewById(R.id.llWebLayout);
        initWebView();
    }

    @Override
    public void finishAndRemoveTask() {
        super.finishAndRemoveTask();
    }

    public void onClick(View view) {
        throw null;
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "ddd");
//        sendIntent.setType("text/plain");
//
//        if (sendIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(sendIntent);
//        }
    }

    private void initWebView() {
        WebView wvWeb = new WebView(this);
        llWebLayout.addView(wvWeb);
        WebView.setWebContentsDebuggingEnabled(true);
        wvWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        wvWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        wvWeb.loadUrl(WEB_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
