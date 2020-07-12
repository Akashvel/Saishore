package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class aboutcollege extends AppCompatActivity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutcollege);
        webview =(WebView)findViewById(R.id.webview);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl("https://www.google.com/maps/@12.9601631,80.0574411,3a,82.2y,310.85h,82.45t/data=!3m7!1e1!3m5!1sAF1QipOWm3RGBOmQ1jZ_qQ8zQ16qtUexxpohWb5KFKnf!2e10!3e12!7i13312!8i6656");
    }
}
