package com.rather.quiz.Quiz;


public class Question {
    private String question;
    private String[] answers;
    private int indexOfRightAnswer;
    private String prompt;

    public Question(String question, String[] answers, int indexOfRightAnswer) {
        this.question = question;
        this.answers = answers;
        this.indexOfRightAnswer = indexOfRightAnswer;
    }

    public Question(String question, String[] answers, int indexOfRightAnswer, String prompt) {
        this.question = question;
        this.answers = answers;
        this.indexOfRightAnswer = indexOfRightAnswer;
        this.prompt = prompt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getIndexOfRightAnswer() {
        return indexOfRightAnswer;
    }

    public void setIndexOfRightAnswer(int indexOfRightAnswer) {
        this.indexOfRightAnswer = indexOfRightAnswer;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
