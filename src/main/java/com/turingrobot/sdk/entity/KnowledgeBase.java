package com.turingrobot.sdk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 知识库
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class KnowledgeBase implements Serializable {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private Date time;
    private String question;
    private String answer;
    @JsonIgnore
    private String labelId;

    public KnowledgeBase() {
    }

    public KnowledgeBase(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public KnowledgeBase(String question, String answer, String id) {
        this.question = question;
        this.answer = answer;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @JsonProperty("label_id")
    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
}
