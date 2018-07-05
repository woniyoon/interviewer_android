package com.example.woniyoon.assignment10.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class HistoryRecord {

    @SerializedName("recordOfHistory")
    @Expose
    private ArrayList<RecordOfHistory> recordOfHistory = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public HistoryRecord() {
    }

    /**
     *
     * @param recordOfHistory
     */
    public HistoryRecord(ArrayList<RecordOfHistory> recordOfHistory) {
        super();
        this.recordOfHistory = recordOfHistory;
    }

    public ArrayList<RecordOfHistory> getRecordOfHistory() {
        return recordOfHistory;
    }

    public void setRecordOfHistory(ArrayList<RecordOfHistory> recordOfHistory) {
        this.recordOfHistory = recordOfHistory;
    }

}