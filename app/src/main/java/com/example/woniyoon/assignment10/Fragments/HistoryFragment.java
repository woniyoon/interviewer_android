package com.example.woniyoon.assignment10.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.woniyoon.assignment10.Activities.Main2Activity;
import com.example.woniyoon.assignment10.Eventbus.Bus;
import com.example.woniyoon.assignment10.Eventbus.Events;
import com.example.woniyoon.assignment10.Models.RecordOfHistory;
import com.example.woniyoon.assignment10.Models.HistoryRecord;
import com.example.woniyoon.assignment10.R;
import com.example.woniyoon.assignment10.Retrofit.ApiUtils;
import com.example.woniyoon.assignment10.Retrofit.RetrofitService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    private static final String TAG = HistoryFragment.class.getSimpleName();
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter = new HistoryAdapter();

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        Bus.getBus().register(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new HistoryAdapter(getContext(), historyAdapter.recordOfHistory));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Bus.getBus().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getMessage(Events.RequestHistory event) {
        Log.d(TAG, "getMessage is called!!!!");

        RetrofitService mService = ApiUtils.getSOService();

        mService.getHistory(event.getId()).enqueue(new Callback<HistoryRecord>() {
            @Override
            public void onResponse(Call<HistoryRecord> call, Response<HistoryRecord> response) {
                if (response.body() == null) {
                    Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "getMessage onResponse");
                    recyclerView.setAdapter(new HistoryAdapter(getContext(), response.body().getRecordOfHistory()));
                }
            }

            @Override
            public void onFailure(Call<HistoryRecord> call, Throwable t) {

            }
        });
    }

    public static class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyVH> {

        private ArrayList<RecordOfHistory> recordOfHistory = new ArrayList<>();
        private Context mContext;

        public HistoryAdapter() {
        }

        public HistoryAdapter(Context context, ArrayList<RecordOfHistory> recordOfHistory) {
            this.recordOfHistory = recordOfHistory;
            mContext = context;
        }

        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_fragment_history, parent, false);

            return new MyVH(itemView);
        }

        @Override
        public void onBindViewHolder(MyVH holder, int position) {

            if (recordOfHistory != null) {
                holder.topic.setText(recordOfHistory.get(position).getTopic());
                holder.date.setText(recordOfHistory.get(position).getDate());
                holder.score.setText(recordOfHistory.get(position).getScore().toString());

                if (position % 2 == 1) {
                    holder.cardView.setBackgroundColor(mContext.getColor(R.color.colorAccent));
                } else if (position % 2 == 0) {
                    holder.cardView.setBackgroundColor(mContext.getColor(R.color.parchment));
                }
            }


        }

        @Override
        public int getItemCount() {
            return recordOfHistory.size();
        }


        public class MyVH extends RecyclerView.ViewHolder {

            final TextView topic, date, score;
            final CardView cardView;

            public MyVH(View itemView) {
                super(itemView);

                cardView = itemView.findViewById(R.id.cardview);
                topic = itemView.findViewById(R.id.history_topic);
                date = itemView.findViewById(R.id.history_date);
                score = itemView.findViewById(R.id.history_score);
            }
        }
    }
}
