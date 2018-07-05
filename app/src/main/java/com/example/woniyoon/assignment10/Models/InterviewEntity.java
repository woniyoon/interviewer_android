package com.example.woniyoon.assignment10.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InterviewEntity {

    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("questionEntities")
    @Expose
    private ArrayList<QuestionEntity> questionEntities = null;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("interviewID")
    @Expose
    private String interviewID;

    /**
     * No args constructor for use in serialization
     *
     */
    public InterviewEntity() {
    }

    /**
     *
     * @param topic
     * @param duration
     * @param questionEntities
     * @param interviewID
     */
    public InterviewEntity(Integer duration, ArrayList<QuestionEntity> questionEntities, String topic, String interviewID) {
        super();
        this.duration = duration;
        this.questionEntities = questionEntities;
        this.topic = topic;
        this.interviewID = interviewID;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ArrayList<QuestionEntity> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(ArrayList<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(String interviewID) {
        this.interviewID = interviewID;
    }
}
