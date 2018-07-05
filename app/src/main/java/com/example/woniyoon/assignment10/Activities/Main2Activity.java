package com.example.woniyoon.assignment10.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.woniyoon.assignment10.Eventbus.Bus;
import com.example.woniyoon.assignment10.Eventbus.Events;
import com.example.woniyoon.assignment10.Fragments.HistoryFragment;
import com.example.woniyoon.assignment10.Fragments.QuestionFragment;
import com.example.woniyoon.assignment10.Fragments.ResultFragment;
import com.example.woniyoon.assignment10.Interfaces.UserStorage;
import com.example.woniyoon.assignment10.Models.AnswerCollectionEntity;
import com.example.woniyoon.assignment10.Models.AnswerEntity;
import com.example.woniyoon.assignment10.Models.ResultEntity;
import com.example.woniyoon.assignment10.R;
import com.example.woniyoon.assignment10.Retrofit.ApiUtils;
import com.example.woniyoon.assignment10.Retrofit.RetrofitService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    Button startButton, resultButton, historyButton, logoutButton;
    RadioGroup typeRadioButton, quizRadioButton;
    RadioButton javaButton, sqlButton;
    HashMap<Integer, AnswerEntity> answerStorage = new HashMap<>();

    @Override
    protected void onStart() {
        super.onStart();
        Bus.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Bus.getBus().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        startButton = findViewById(R.id.main2_start_button);
        resultButton = findViewById(R.id.main2_result_button);
        historyButton = findViewById(R.id.main2_history_button);
        logoutButton = findViewById(R.id.main2_logout_button);
        typeRadioButton = findViewById(R.id.main2_radioGroup);
        javaButton = findViewById(R.id.main2_java_button);
        sqlButton = findViewById(R.id.main2_sql_button);
        quizRadioButton = findViewById(R.id.question_radiogroup);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main2_container, QuestionFragment.newInstance(), "questionFrag").commit();

                int selectedValueID = typeRadioButton.getCheckedRadioButtonId();
                if (selectedValueID == javaButton.getId()) {
                    Events.TopicToQuestionFragment topicToQuestionFragment = new Events.TopicToQuestionFragment("java");
                    Bus.getBus().postSticky(topicToQuestionFragment);
                } else if (selectedValueID == sqlButton.getId()) {
                    Events.TopicToQuestionFragment topicToQuestionFragment = new Events.TopicToQuestionFragment("SQL");
                    Bus.getBus().postSticky(topicToQuestionFragment);
                } else {
                    Toast.makeText(Main2Activity.this, "you need to choose the type first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitService mService = ApiUtils.getSOService();
                String interviewId;

                if (typeRadioButton.getCheckedRadioButtonId() == javaButton.getId()) {
                    interviewId = "201";
                } else if (typeRadioButton.getCheckedRadioButtonId() == sqlButton.getId()) {
                    interviewId = "202";
                } else {
                    interviewId = "";
                }

                ArrayList<AnswerEntity> answerList = new ArrayList<>();

                for (int i = 0; i < answerStorage.size(); i++) {
                    answerList.add(new AnswerEntity(answerStorage.get(i).getQuestionID(), answerStorage.get(i).getAnswer()));
                }

                AnswerCollectionEntity answerCollectionEntity = new AnswerCollectionEntity(UserStorage.getUser(Main2Activity.this).getUserId(), interviewId, answerList);

                if (!interviewId.equals("201") && !interviewId.equals("202")) {
                    Toast.makeText(Main2Activity.this, "you need to take a test first!", Toast.LENGTH_SHORT).show();
                } else {

                    mService.markAnswers(answerCollectionEntity).enqueue(new Callback<ResultEntity>() {
                        @Override
                        public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                            if (response.isSuccessful()) {
                                Events.ResultToFragment event = new Events.ResultToFragment(response.body());
                                Bus.getBus().postSticky(event);
                                getSupportFragmentManager().beginTransaction().replace(R.id.main2_container, ResultFragment.newInstance(), "resultFrag").commit();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultEntity> call, Throwable t) {

                        }
                    });
                }
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main2_container, HistoryFragment.newInstance(), "historyFrag").commit();
                getSupportFragmentManager().executePendingTransactions();
                int userId = UserStorage.getUser(Main2Activity.this).getUserId();
                Events.RequestHistory event = new Events.RequestHistory(userId);
                Bus.getBus().postSticky(event);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserStorage.remove(Main2Activity.this);
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Events.PassAnswer passedAnswer) {
        answerStorage.put(passedAnswer.getPosition(), new AnswerEntity(passedAnswer.getQuestionId(), passedAnswer.getAnswer()));
    }

}
