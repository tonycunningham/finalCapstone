package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.DeckCardsDto;

import java.util.List;

public interface DeckDao {

    Deck createDeck(Deck deck);
    Deck getDeckByDeckId(int deckId);
    List<Deck> getDecksByUserId(int userId);
    void addFlashcardToDeckByCardId(int deckId, int cardId);
    void removeFlashcardFromDeckByCardId(int deckId, int cardId);
    void changeNameOfDeckByDeckId(String newName, int deckId);
    DeckCardsDto getFlashcardsFromDeckByDeckId (int deckId);


    //void addFlashcardToDeckByTag(int deckId, int cardId, String tag);


}
