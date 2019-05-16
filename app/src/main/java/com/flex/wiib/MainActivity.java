package com.flex.wiib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView browser;
    int empId,LineaID,EstacionID;
    String url = "http://jzmnt026:8001/WIIB/Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browser=findViewById(R.id.webView);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");


        if(getIntent().getExtras() != null){
            empId= getIntent().getExtras().getInt("empId");
            LineaID= getIntent().getExtras().getInt("LineaID");
            EstacionID= getIntent().getExtras().getInt("EstacionID");
        }

        if(empId >0){
            url = "10.121.64.48/wiib/hojatrabajo/?LineaID="+LineaID+"&EstacionID="+EstacionID+"&empId="+empId;
        }

        browser.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        // Cargamos la web


        browser.loadUrl(url);
    }
}
