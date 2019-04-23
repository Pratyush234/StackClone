package com.example.praty.stackclone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.praty.stackclone.R;

public class WebActivity1 extends AppCompatActivity {

    ImageButton mShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1);

        mShare=(ImageButton) findViewById(R.id.shareLink);
        final ProgressBar progressBar=(ProgressBar) findViewById(R.id.questionProgress);
        progressBar.setVisibility(View.VISIBLE);
        WebView webView=(WebView) findViewById(R.id.question_web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        //load the url of the post link in a webView
        webView.loadUrl(getIntent().getStringExtra("url"));

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);

            }
        });

        //Intent to share the post link to other applications on the phone
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Link of the post");
                share.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("url"));

                startActivity(Intent.createChooser(share, "Post link!"));
            }
        });

    }
}
