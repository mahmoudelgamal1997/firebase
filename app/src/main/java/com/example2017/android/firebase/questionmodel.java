package com.example2017.android.firebase;

/**
 * Created by M7moud on 19-Aug-18.
 */
public class questionmodel {

    String question;
    String answer;

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

    public questionmodel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
