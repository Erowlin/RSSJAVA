package com.example.jonathan.rss;

import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.os.Bundle;

/**
 * Created by Jonathan on 16-Nov-14.
 */
public class ItemView extends Activity {
    private WebView view;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.item_view);
        Bundle bundle = this.getIntent().getExtras();
        String postContent = bundle.getString("content");
        this.setTitle(bundle.getString("title"));

        view = (WebView)this.findViewById(R.id.web);
        view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        view.loadData(postContent, "text/html; charset=utf-8","utf-8");
    }
}
