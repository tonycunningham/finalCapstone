package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class DeckCardsDto {


    private int deckId;
    private Flashcard[] cardsInDeck;
    private String deckName;

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public Flashcard[] getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(Flashcard[] cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }
}
