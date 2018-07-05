package com.example.woniyoon.assignment10.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AnswerCollectionEntity {

    @SerializedName("userID")
    @Expose
    private int userID;
    @SerializedName("interviewID")
    @Expose
    private String interviewID;
    @SerializedName("givenAnswers")
    @Expose
    private ArrayList<AnswerEntity> givenAnswers = new ArrayList<>();


    public AnswerCollectionEntity(){}

    public AnswerCollectionEntity(int userId, String interviewId, ArrayList<AnswerEntity> answerEntityArrayList) {
        this.userID = userId;
        this.interviewID = interviewId;
        this.givenAnswers = answerEntityArrayList;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(String interviewID) {
        this.interviewID = interviewID;
    }

    public ArrayList<AnswerEntity> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(ArrayList<AnswerEntity> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }
}
