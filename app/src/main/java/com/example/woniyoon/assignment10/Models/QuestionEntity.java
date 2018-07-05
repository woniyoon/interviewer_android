package com.example.woniyoon.assignment10.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionEntity {

    @SerializedName("difficultyLevel")
    @Expose
    private Integer difficultyLevel;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("option1")
    @Expose
    private String option1;
    @SerializedName("option2")
    @Expose
    private String option2;
    @SerializedName("option3")
    @Expose
    private String option3;
    @SerializedName("option4")
    @Expose
    private String option4;
    @SerializedName("questionID")
    @Expose
    private Integer questionID;

    /**
     * No args constructor for use in serialization
     */
    public QuestionEntity() {
    }

    /**
     * @param option1
     * @param option2
     * @param option3
     * @param option4
     * @param questionID
     * @param description
     * @param difficultyLevel
     */
    public QuestionEntity(Integer difficultyLevel, String description, String option1, String option2, String option3, String option4, Integer questionID) {
        super();
        this.difficultyLevel = difficultyLevel;
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.questionID = questionID;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }
}
