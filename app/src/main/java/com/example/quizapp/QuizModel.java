package com.example.quizapp;

public class QuizModel {

    private int mQuestions;
    private boolean mAnswer;

    public QuizModel(int mQuestions, boolean mAnswer) {
        this.mQuestions = mQuestions;
        this.mAnswer = mAnswer;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public int getmQuestions() {
        return mQuestions;
    }

    public void setmQuestions(int mQuestions) {
        this.mQuestions = mQuestions;
    }
}
