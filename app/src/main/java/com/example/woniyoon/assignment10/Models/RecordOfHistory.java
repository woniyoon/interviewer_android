package com.example.woniyoon.assignment10.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordOfHistory {

    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("score")
    @Expose
    private Integer score;

    /**
     * No args constructor for use in serialization
     *
     */
    public RecordOfHistory() {
    }

    /**
     *
     * @param topic
     * @param score
     * @param date
     */
    public RecordOfHistory(String topic, String date, Integer score) {
        super();
        this.topic = topic;
        this.date = date;
        this.score = score;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}