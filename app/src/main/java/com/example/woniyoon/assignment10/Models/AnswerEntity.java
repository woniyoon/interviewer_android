package com.example.woniyoon.assignment10.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerEntity {

    @SerializedName("questionID")
    @Expose
    private int questionID;
    @SerializedName("answer")
    @Expose
    private String answer;

    public AnswerEntity(){}

    public AnswerEntity(int questionId, String answer) {
        this.questionID = questionId;
        this.answer = answer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
