package com.example.woniyoon.assignment10.Eventbus;


import com.example.woniyoon.assignment10.Models.ResultEntity;

import java.util.ArrayList;

public class Events {

    public static class TopicToQuestionFragment {
        private String topic;

        public TopicToQuestionFragment(String topic) {
            this.topic = topic;
        }

        public String getTopic() {
            return topic;
        }
    }

    public static class RequestHistory {
        private int id;

        public RequestHistory(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }

    }

    public static class PassAnswer{

        private int position;
        private int questionId;
        private String answer;

        public PassAnswer(int position, int questionId, String answer){
            this.position = position;
            this.questionId = questionId;
            this.answer = answer;
        }

        public int getPosition(){
            return position;
        }

        public int getQuestionId(){
            return questionId;
        }

        public String getAnswer(){
            return answer;
        }
    }

    public static class ResultToFragment{
        private ResultEntity resultEntity;

        public ResultToFragment(ResultEntity resultEntity){
            this.resultEntity = resultEntity;
        }

        public ResultEntity getResultEntity(){
            return resultEntity;
        }
    }
}
