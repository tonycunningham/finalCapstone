package com.techelevator.dao;

import com.techelevator.model.Flashcard;

import java.util.List;

public interface FlashcardDao {


    Flashcard createFlashCard(Flashcard newCard);

    Flashcard getFlashCardById( int id );

    void editFlashCardById( Flashcard flashcard );

    List<Flashcard> getFlashCardByTag( String tag );

    void deleteFlashCardById ( int id );

    int getCardsIdByTag(String tag );

    List<Flashcard> getAllFlashcardsFromUserId(int userId);

    //TODO fix this method in the controller first:

    //   List<Flashcard> getAllFlashcardByUserId (int userId);

}
