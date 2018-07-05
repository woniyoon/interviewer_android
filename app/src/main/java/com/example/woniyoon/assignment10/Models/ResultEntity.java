package com.example.woniyoon.assignment10.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ResultEntity {

    @SerializedName("interviewID")
    @Expose
    private String interviewID;
    @SerializedName("userID")
    @Expose
    private int userID;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("correct")
    @Expose
    private int correct;
    @SerializedName("wrong")
    @Expose
    private int wrong;
    @SerializedName("skipped")
    @Expose
    private int skipped;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("date")
    @Expose
    private long date;

    public ResultEntity(String interviewID, int userID, int score, int correct, int wrong, int skipped, int total, long date) {
        this.interviewID = interviewID;
        this.userID = userID;
        this.score = score;
        this.correct = correct;
        this.wrong = wrong;
        this.skipped = skipped;
        this.total = total;
        this.date = date;
    }

    public String getInterviewId() {
        return interviewID;
    }

    public void setInterviewId(String interviewID) {
        this.interviewID = interviewID;
    }

    public int getUserid() {
        return userID;
    }

    public void setUserid(int userID) {
        this.userID = userID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public int getTotalNoOfAnswers() {
        return total;
    }

    public void setTotalNoOfAnswers(int total) {
        this.total = total;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
