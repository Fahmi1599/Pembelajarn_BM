package com.skiripsi.pembelajaranbm.ui;

public class QuestionBS {

    private String question;
    private String option1;
    private String option2;
    private int answerNr;

    QuestionBS(){

    }

    public QuestionBS(String question, String option1, String option2, int answerNr) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.answerNr = answerNr;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
}
