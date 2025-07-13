package com.example.medcialassistants;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class tiyuWeb extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private String[] backupUrls = {
        "https://www.who.int/zh",
        "https://www.nhc.gov.cn/",
        "https://www.cdc.gov/",
        "https://www.mayoclinic.org/",
        "https://www.webmd.com/"
    };
    private int currentUrlIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiyu_web);
        
        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.progress_bar);
        
        setupWebView();
        loadWebsite();
    }
    
    private void setupWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
            
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
            
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
                
                // 尝试加载下一个备用网站
                if (currentUrlIndex < backupUrls.length - 1) {
                    currentUrlIndex++;
                    Toast.makeText(tiyuWeb.this, "正在尝试备用网站...", Toast.LENGTH_SHORT).show();
                    loadWebsite();
                } else {
                    Toast.makeText(tiyuWeb.this, "所有网站都无法访问，请检查网络连接", Toast.LENGTH_LONG).show();
                }
            }
        });
        
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
    }
    
    private void loadWebsite() {
        String url = backupUrls[currentUrlIndex];
        webView.loadUrl(url);
    }
    
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}