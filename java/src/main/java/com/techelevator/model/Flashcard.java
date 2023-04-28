package com.techelevator.model;

public class Flashcard {
    private int cardId;
    private String question;
    private String answer;
    private String tags;
    private int userId;

    public Flashcard(int cardId, String question, String answer, String tags, int userId) {
        this.cardId = cardId;
        this.question = question;
        this.answer = answer;
        this.tags = tags;
        this.userId = userId;
    }

    public Flashcard() {

    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
