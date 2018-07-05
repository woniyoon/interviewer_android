package com.example.woniyoon.assignment10.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.woniyoon.assignment10.Eventbus.Bus;
import com.example.woniyoon.assignment10.Eventbus.Events;
import com.example.woniyoon.assignment10.Models.ResultEntity;
import com.example.woniyoon.assignment10.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultFragment extends Fragment {

    TextView date, score, correct, wrong, skipped;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = view.findViewById(R.id.result_date);
        score = view.findViewById(R.id.result_score);
        correct = view.findViewById(R.id.result_correct);
        wrong = view.findViewById(R.id.result_wrong);
        skipped = view.findViewById(R.id.result_skipped);

    }

    @Override
    public void onStart() {
        Bus.getBus().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        Bus.getBus().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Events.ResultToFragment result) {
        setValues(result.getResultEntity());
    }

    public void setValues(ResultEntity resultEntity){

        Date receivedDate = new Date(resultEntity.getDate());
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD", Locale.CANADA);
//        String convertedDate = sdf.format(receivedDate);
        date.setText(receivedDate.toString());
        score.setText(String.valueOf(resultEntity.getScore()));
        correct.setText(String.valueOf(resultEntity.getCorrect()));
        wrong.setText(String.valueOf(resultEntity.getWrong()));
        skipped.setText(String.valueOf(resultEntity.getSkipped()));
    }
}
