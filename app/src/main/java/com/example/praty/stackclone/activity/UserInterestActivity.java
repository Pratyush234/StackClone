package com.example.praty.stackclone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.praty.stackclone.R;
import com.example.praty.stackclone.recyclerhelper.ItemClickListener;
import com.example.praty.stackclone.recyclerhelper.TagAdapter;
import com.example.praty.stackclone.recyclerhelper.CustomItemDecorator;
import com.example.praty.stackclone.model.TagResponse;
import com.example.praty.stackclone.model.Tags;
import com.example.praty.stackclone.retrofit.ApiClient;
import com.example.praty.stackclone.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInterestActivity extends AppCompatActivity {
    private static final String TAG = "UserInterestActivity";
    RecyclerView mRecyclerView;
    TagAdapter mAdapter;

    //UI components
    Button mTag1,mTag2,mTag3,mTag4;
    ImageButton mNext;
    ArrayList<String> mTagNames=new ArrayList<>();



    int ctr=0;
    List<Tags> mTags=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interest);

        mTag1=(Button) findViewById(R.id.tag1);
        mTag2=(Button) findViewById(R.id.tag2);
        mTag3=(Button) findViewById(R.id.tag3);
        mTag4=(Button) findViewById(R.id.tag4);
        mNext=(ImageButton) findViewById(R.id.next_button);

        mTagNames.add("Android");
        mTagNames.add("Android");
        mTagNames.add("Android");
        mTagNames.add("Android");

        mRecyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);

        mTag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTag1.setText("");
            }
        });

        mTag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTag2.setText("");
            }
        });

        mTag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTag3.setText("");
            }
        });

        mTag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTag4.setText("");
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b=new Bundle();
                b.putStringArrayList("tags",mTagNames);
                Intent intent= new Intent(UserInterestActivity.this, QuestionListActivity.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });


        getTagsInTheList();
    }

    //uses the https://api.stackexchange.com/docs/tags to make API calls and fetching tags in a recyclerview
    private void getTagsInTheList() {

        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        CustomItemDecorator customItemDecorator =new CustomItemDecorator(10);
        mRecyclerView.addItemDecoration(customItemDecorator);
        ApiInterface apiService= ApiClient.getclient().create(ApiInterface.class);
        Call<TagResponse> call=apiService.getTags();
        call.enqueue(new Callback<TagResponse>() {
            @Override
            public void onResponse(Call<TagResponse> call, Response<TagResponse> response) {
                TagResponse tagResponse=response.body();
                Log.d(TAG, "onResponse: called: body:"+tagResponse);

                if(tagResponse!=null){
                    mTags=tagResponse.getItems();
                    Log.d(TAG, "onResponse: No. of tags="+mTags.size());
                    mAdapter=new TagAdapter(mTags, new ItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
//                            Toast.makeText(UserInterestActivity.this, "item clicked",Toast.LENGTH_SHORT).show();

                            if(ctr==0) {
                                mTag1.setText(mTags.get(position).getName());
                                mTagNames.add(0, mTags.get(position).getName());
                            }
                            else if(ctr==1){
                                mTag2.setText(mTags.get(position).getName());
                                mTagNames.add(1, mTags.get(position).getName());
                            }

                            else if(ctr==2) {
                                mTag3.setText(mTags.get(position).getName());
                                mTagNames.add(2, mTags.get(position).getName());
                            }

                            else {
                                mTag4.setText(mTags.get(position).getName());
                                mTagNames.add(3, mTags.get(position).getName());
                            }


                            if(ctr!=3)
                                    ctr++;
                            else ctr=0;


                        }
                    });
                    mRecyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<TagResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: error"+t.toString() );

            }
        });
    }
}
