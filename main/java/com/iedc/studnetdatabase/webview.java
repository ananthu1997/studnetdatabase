package com.iedc.studnetdatabase;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.webkit.WebView;

import android.webkit.WebViewClient;

import android.widget.Button;
import android.widget.Toast;


public class webview extends AppCompatActivity {

    WebView w1;Button b1,b2;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);

        w1=(WebView)findViewById(R.id.web);

        w1.setWebViewClient(new WebViewClient());

        w1.loadUrl("http://www.google.com");

        b1=(Button)findViewById(R.id.b1);

        b2=(Button)findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                w1.loadUrl("http://www.google.com");

            }



        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                onBackPressed();

            }

        });



    }

    @Override

    public boolean onCreateOptionsMenu( Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.credits:
                Toast.makeText(webview.this, "Created by ANANTHU S AJAYAN G,R3", Toast.LENGTH_LONG).show();
                return true;
            case R.id.website:
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }
}
