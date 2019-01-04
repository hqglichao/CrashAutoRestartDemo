package io.github.hqglichao.crashautorestartdemo;

import android.content.Intent;
import android.view.View;

/**
 * @author beyond
 * @date 18-12-17
 */
public class MainViewModel {
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), WebViewActivity.class);
        view.getContext().startActivity(intent);
    }
}
