package com.example.praty.stackclone.activity;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.praty.stackclone.R;
import com.example.praty.stackclone.model.QuestionsTitle;
import com.example.praty.stackclone.persistence.QuestionRepository;
import com.example.praty.stackclone.recyclerhelper.CustomItemDecorator;
import com.example.praty.stackclone.recyclerhelper.ItemClickListener;
import com.example.praty.stackclone.recyclerhelper.QuestionAdapter;
import com.example.praty.stackclone.model.QuestionResponse;
import com.example.praty.stackclone.model.Questions;
import com.example.praty.stackclone.retrofit.ApiClient;
import com.example.praty.stackclone.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "QuestionListActivity";

    RecyclerView mRecycler;
    QuestionAdapter mAdapter;
    private List<Questions> mQuestions;
    private List<QuestionsTitle> mTitles=new ArrayList<>();
    ArrayList<String> mTagNames=new ArrayList<>();
    NavigationView mNavigationView;
    QuestionRepository mRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mRecycler=(RecyclerView) findViewById(R.id.question_recycler);
        mRepository=new QuestionRepository(this);

        //Initializing the recycler view
        initRecyclerView();
        //setting up the recyclerview when this activity is first created
        setupRecyclerView("android");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
        Bundle b=getIntent().getExtras();
        //checking for the tags passed from UserInterestActivity
        if(b!=null) {
            mTagNames = b.getStringArrayList("tags");
            //if tags are passed, we programmatically change the titles of the menu items
            setupMenuTitles();
        }
    }


    //method to programmatically change the titles of the menu items if tagnames are passed from UserInterestActivity
    private void setupMenuTitles() {
        Menu menu=mNavigationView.getMenu();

        MenuItem item1=menu.findItem(R.id.tag1_1);
        String tagName=mTagNames.get(0);
        String capitalTag=tagName.toUpperCase().charAt(0)+ tagName.substring(1);
        item1.setTitle(capitalTag);

        MenuItem item2=menu.findItem(R.id.tag2_2);
        tagName=mTagNames.get(1);
        capitalTag=tagName.toUpperCase().charAt(0)+ tagName.substring(1);
        item2.setTitle(capitalTag);

        MenuItem item3=menu.findItem(R.id.tag3_3);
        tagName=mTagNames.get(2);
        capitalTag=tagName.toUpperCase().charAt(0)+ tagName.substring(1);
        item3.setTitle(capitalTag);

        MenuItem item4=menu.findItem(R.id.tag4_4);
        tagName=mTagNames.get(3);
        capitalTag=tagName.toUpperCase().charAt(0)+ tagName.substring(1);
        item4.setTitle(capitalTag);
    }

    //making the API call to https://api.stackexchange.com/docs/questions#order=desc&sort=hot&tagged=android&filter=default&site=stackoverflow&run=true
    //if there's internet, we setup the recyclerview with the question titles we get from the JSON response
    //we cache the latest loaded data using persistence for offline use
    //if no internet, we load the saved offline questions
    private void setupRecyclerView(final String tag) {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected) {
            ApiInterface apiService = ApiClient.getclient().create(ApiInterface.class);
            Call<QuestionResponse> call = apiService.getQuestions("desc", "hot", tag, "stackoverflow");
            call.enqueue(new Callback<QuestionResponse>() {
                @Override
                public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                    QuestionResponse questionResponse = response.body();
                    Log.d(TAG, "onResponse: response:" + questionResponse);

                    if (questionResponse != null) {
                        mRepository.deleteTask();
                        mQuestions = questionResponse.getItems();

                        if(mTitles.size()>0)
                            mTitles.clear();
                        for(int i=0;i<mQuestions.size();i++){
                            QuestionsTitle title=new QuestionsTitle(mQuestions.get(i).getTitle());
                            mTitles.add(title);
                            mRepository.insertTask(title);
                        }


                        Log.d(TAG, "onResponse: no. of questions:" + mQuestions.size());
                        mAdapter = new QuestionAdapter(mTitles, tag, new ItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                Intent intent = new Intent(QuestionListActivity.this, WebActivity1.class);
                                intent.putExtra("url", mQuestions.get(position).getLink());
                                startActivity(intent);
                            }
                        });
                        mRecycler.setAdapter(mAdapter);

                    }


                }

                @Override
                public void onFailure(Call<QuestionResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: error:" + t.toString());

                }
            });

        }

        else{
            fetchOfflineData();
        }
    }

    //if no internet, we load the offline saved data in the recyclerview
    private void fetchOfflineData() {
       mRepository.retrieveNotesTask().observe(this, new Observer<List<QuestionsTitle>>() {
           @Override
           public void onChanged(@Nullable List<QuestionsTitle> questionsTitles) {
               if(mTitles.size()>0)
                   mTitles.clear();

               if(questionsTitles!=null){
                   mTitles.addAll(questionsTitles);
               }

               mAdapter=new QuestionAdapter(mTitles, "offline", new ItemClickListener() {
                   @Override
                   public void onItemClick(View v, int position) {
                       Toast.makeText(QuestionListActivity.this,"No internet",Toast.LENGTH_SHORT).show();
                   }
               });

               mRecycler.setAdapter(mAdapter);
           }
       });

    }


    private void initRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);

        CustomItemDecorator customItemDecorator =new CustomItemDecorator(10);
        mRecycler.addItemDecoration(customItemDecorator);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question_list, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.tag1_1) {
            setupRecyclerView(mTagNames.get(0));
        } else if (id == R.id.tag2_2) {
            setupRecyclerView(mTagNames.get(1));
        } else if (id == R.id.tag3_3) {
            setupRecyclerView(mTagNames.get(2));
        } else if (id == R.id.tag4_4) {
            setupRecyclerView(mTagNames.get(3));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
