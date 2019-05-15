package com.flex.wiib;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void openScanner(int LineaId,int EstacionId) {

        Toast.makeText(mContext, "Linea: "+LineaId+" estacion: "+EstacionId, Toast.LENGTH_LONG).show();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        mContext.startActivity(intent);
    }
}
