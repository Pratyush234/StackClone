package com.example.praty.stackclone.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.praty.stackclone.R;

public class WebActivity extends AppCompatActivity {
    private static final String TAG = "WebActivity";

    private String mAccessToken=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        final ProgressBar progressBar=(ProgressBar) findViewById(R.id.webProgress);
        progressBar.setVisibility(View.VISIBLE);
        WebView webView=(WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString("Chrome/56.0.0.0 Mobile");

        //api call for logging in to stackexchange
        webView.loadUrl("https://stackoverflow.com/oauth/dialog?client_id=15042&scope=no_expiry&redirect_uri=https://stackexchange.com/oauth/login_success");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            //this method will be called when the url is redirected to https://stackexchange.com/oauth/login_success
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "shouldOverrideUrlLoading: called");
                if (url.contains("#access_token")) {
                    return true;
                }
                return false;
            }

            //this method is called when the redirected page finishes loading
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onPageFinished: url:" + url);
                if (url.contains("#access_token")) {
                   mAccessToken= url.replace("https://stackexchange.com/oauth/login_success#access_token=", "");
                }
                Log.d(TAG, "onPageFinished: access token:"+mAccessToken);

                //if the access token is not null, we are navigation the user to the UserInterestActivity
                if(mAccessToken!=null) {
                    Toast.makeText(WebActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    setupSharedPreferences();
                    Intent intent=new Intent(WebActivity.this, UserInterestActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });
    }

    //as we are using scope=no_expiry, we won't have to login to stackexchange again, so we are saving a boolean value
    //locally to check whether the sign in process has been completed or not(used in SplashActivity)
    private void setupSharedPreferences() {
        SharedPreferences prefs= getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putBoolean("activity_executed",true);
        editor.apply();
    }
}
