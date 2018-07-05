package com.example.woniyoon.assignment10.Retrofit;

import com.example.woniyoon.assignment10.Models.AnswerCollectionEntity;
import com.example.woniyoon.assignment10.Models.HistoryRecord;
import com.example.woniyoon.assignment10.Models.InterviewEntity;
import com.example.woniyoon.assignment10.Models.LoginEntity;
import com.example.woniyoon.assignment10.Models.ProfileEntity;
import com.example.woniyoon.assignment10.Models.ResultEntity;
import com.example.woniyoon.assignment10.Models.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {


    @POST("login")
    Call<UserEntity> getLogin(@Body LoginEntity loginEntity);

    @POST("users")
    Call<UserEntity> postUser(@Body ProfileEntity profileEntity);

    @GET("topics/{type}")
    Call<InterviewEntity> getInterview(@Path("type") String type);

    @POST("history")
    Call<ResultEntity> markAnswers(@Body AnswerCollectionEntity answerCollectionEntity);

    @GET("history/user/{userID}")
    Call<HistoryRecord> getHistory(@Path("userID") int userID);

}
