package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class feespayment extends AppCompatActivity {
    WebView webView;
    ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feespayment);
        pg=(ProgressBar)findViewById(R.id.pg);
        pg.setVisibility(View.VISIBLE);
        webView =(WebView)findViewById(R.id.wb);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.loadUrl("https://easycollege.in/saiengg/college/webpayindex.aspx");
        vd();
    }
    public void vd(){
        pg.setVisibility(View.GONE);
    }
}
