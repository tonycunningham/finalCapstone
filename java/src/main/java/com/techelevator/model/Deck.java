package com.techelevator.model;

import java.util.List;

public class Deck {

    private int deckId;
    private int userId;
    private String deckName;

    public Deck(int deckId, int userId, String deckName) {
        this.deckId = deckId;
        this.userId = userId;
        this.deckName = deckName;
    }
    public Deck(){
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
}
