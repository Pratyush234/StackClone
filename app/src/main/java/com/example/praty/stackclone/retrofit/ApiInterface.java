package com.example.praty.stackclone.retrofit;

import com.example.praty.stackclone.model.QuestionResponse;
import com.example.praty.stackclone.model.TagResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//interface for the GET API calls
public interface ApiInterface {

    @GET("2.2/tags?order=desc&sort=popular&site=stackoverflow")
    Call<TagResponse> getTags();

    @GET("2.2/questions")
    Call<QuestionResponse> getQuestions(@Query("order") String order, @Query("sort") String hot, @Query("tagged") String tag, @Query("site") String site);


}
