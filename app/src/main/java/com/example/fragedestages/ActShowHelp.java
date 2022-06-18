package com.example.fragedestages;


/*
 * ActShowHelp.java
 * Android-Anwendungsentwicklung
 */


import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

/**
 * Activity zur Anzeige der HTML-Onlinehilfe.
 * Oberfläche: layout/lay_show_help.xml
 *
 * @author Wolfgang Lang
 * @version 2019-10-27
 *
 */
public class ActShowHelp extends Activity {

    private static final String MIMETYPE = "text/html";
    private static final String ENCODING = "UTF-8";

    static final boolean DBG = true;
    static final String TAG = "ActShowHelp";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        final String MNAME = "onCreate()";
        if( DBG ) Log.i( TAG, MNAME + "entering..." );

        super.onCreate( savedInstanceState );

        setContentView( R.layout.lay_show_help1 );

        final WebView view = findViewById( R.id.webview );
        view.getSettings().setJavaScriptEnabled( true );

        /*
         * Die Hilfe lässt sich auch von einem Server laden mit: view.loadUrl(
         * "http://www.hwr-berlin.de/..." );
         *
         * Ggf. lassen sich auch unterschiedliche HTML-Seiten aufrufen. In diesem
         * Fall wird die id für openRawResource() im Intent-Bundle übergeben.
         * Beispiel: final Bundle extras = getIntent().getExtras(); if( (extras !=
         * null) && (extras.containsKey( KEY_MY_HELP )) ) int id = extras.getInt(
         * KEY_MY_HELP );
         */

        InputStream is = getResources().openRawResource( R.raw.contact);

        try {
            if( is.available() > 0 ) {
                final byte[] bytes = new byte[is.available()];
                is.read( bytes );
                view.loadDataWithBaseURL( null, new String( bytes ), MIMETYPE,
                        ENCODING, null );
                /*
                 * Alternativ geht auch: view.loadData( new String( bytes ), MIMETYPE,
                 * ENCODING );
                 */
            }
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }

        view.bringToFront();
        if (DBG) Log.d(TAG, MNAME + "...exiting");
    }

}

