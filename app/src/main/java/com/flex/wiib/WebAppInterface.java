package com.flex.wiib;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface{
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void openScanner(int LineaId,int EstacionId) {

        Toast.makeText(mContext, "Linea: "+LineaId+" estacion: "+EstacionId, Toast.LENGTH_LONG).show();
        Intent x = new Intent(mContext,Scanner.class);
        x.putExtra("LineaID", LineaId);
        x.putExtra("EstacionID", EstacionId);
        x.putExtra("empId", 0);
        x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.getApplicationContext().startActivity(x);
    }
}
