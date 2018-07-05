package com.example.woniyoon.assignment10.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.woniyoon.assignment10.Eventbus.Bus;
import com.example.woniyoon.assignment10.Eventbus.Events;
import com.example.woniyoon.assignment10.Models.AnswerCollectionEntity;
import com.example.woniyoon.assignment10.Models.AnswerEntity;
import com.example.woniyoon.assignment10.Models.InterviewEntity;
import com.example.woniyoon.assignment10.Models.QuestionEntity;
import com.example.woniyoon.assignment10.R;
import com.example.woniyoon.assignment10.Retrofit.ApiUtils;
import com.example.woniyoon.assignment10.Retrofit.RetrofitService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionFragment extends Fragment {

    QuestionAdapter questionAdapter = new QuestionAdapter();
    RecyclerView recyclerView;
    RetrofitService mService;


    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        Bus.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Bus.getBus().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.questions_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new QuestionAdapter(getContext(), questionAdapter.receivedTest));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Events.TopicToQuestionFragment receivedTopic) {

        mService = ApiUtils.getSOService();

        mService.getInterview(receivedTopic.getTopic()).enqueue(new Callback<InterviewEntity>() {
            @Override
            public void onResponse(Call<InterviewEntity> call, Response<InterviewEntity> response) {

                if (response.body() == null) {
                    Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();
                } else {
                    questionAdapter.setInterviewEntity(response.body());
                    recyclerView.setAdapter(new QuestionAdapter(getContext(), response.body().getQuestionEntities()));
                }
            }

            @Override
            public void onFailure(Call<InterviewEntity> call, Throwable t) {

            }
        });
    }

    public static class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyVH> {

        public InterviewEntity getInterviewEntity() {
            return interviewEntity;
        }

        public void setInterviewEntity(InterviewEntity interviewEntity) {
            this.interviewEntity = interviewEntity;
        }

        private InterviewEntity interviewEntity;
        private ArrayList<QuestionEntity> receivedTest = new ArrayList<>();
        private Context mContext;

        public QuestionAdapter() {
        }

        public QuestionAdapter(Context context, ArrayList<QuestionEntity> posts) {
            receivedTest = posts;
            mContext = context;
        }

        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_fragment_question, parent, false);

            return new MyVH(itemView);
        }

        @Override
        public void onBindViewHolder(MyVH holder, final int position) {


            holder.description.setText(receivedTest.get(position).getDescription());
            holder.difficulty.setText(String.valueOf(receivedTest.get(position).getDifficultyLevel()));
            holder.option1.setText(receivedTest.get(position).getOption1());
            holder.option2.setText(receivedTest.get(position).getOption2());
            holder.option3.setText(receivedTest.get(position).getOption3());
            holder.option4.setText(receivedTest.get(position).getOption4());


            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    Events.PassAnswer event;

                    switch (i) {
                        case R.id.option1:
                            event = new Events.PassAnswer(position, receivedTest.get(position).getQuestionID(), "A");
                            Bus.getBus().postSticky(event);
                            break;
                        case R.id.option2:
                            event = new Events.PassAnswer(position, receivedTest.get(position).getQuestionID(), "B");
                            Bus.getBus().postSticky(event);
                            break;
                        case R.id.option3:
                            event = new Events.PassAnswer(position, receivedTest.get(position).getQuestionID(), "C");
                            Bus.getBus().postSticky(event);
                            break;
                        case R.id.option4:
                            event = new Events.PassAnswer(position, receivedTest.get(position).getQuestionID(), "D");
                            Bus.getBus().postSticky(event);
                            break;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return receivedTest.size();
        }


        public class MyVH extends RecyclerView.ViewHolder {

            final TextView description, difficulty;
            final RadioGroup radioGroup;
            final RadioButton option1, option2, option3, option4;
            final CardView cardView;


            public MyVH(View itemView) {
                super(itemView);

                description = itemView.findViewById(R.id.description);
                difficulty = itemView.findViewById(R.id.difficultyLevel);
                radioGroup = itemView.findViewById(R.id.question_radiogroup);
                option1 = itemView.findViewById(R.id.option1);
                option2 = itemView.findViewById(R.id.option2);
                option3 = itemView.findViewById(R.id.option3);
                option4 = itemView.findViewById(R.id.option4);
                cardView = itemView.findViewById(R.id.question_cardview);
            }
        }
    }
}
