package com.example.praty.stackclone.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.praty.stackclone.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);

        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        //navigation to LoginActivity/UserInterestActivity if there is internet
        if(isConnected) {
            if (pref.getBoolean("activity_executed", true)) {
                Intent intent = new Intent(this, UserInterestActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }

        //navigation to QuestionListActivity otherwise where the latest data is cached offline using persistence
        else{
            if(pref.getBoolean("activity_executed", true)){
                Intent intent=new Intent(this, QuestionListActivity.class);
                startActivity(intent);
                finish();
            }
            else{

               android.widget.Toast.makeText(this,"No internet",android.widget.Toast.LENGTH_SHORT).show();
               finish();
               }
        }
    }
}
