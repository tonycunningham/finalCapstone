package com.techelevator.dao;


import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFlashcardDao implements FlashcardDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFlashcardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Flashcard createFlashCard(Flashcard newCard) {
        String sql = "INSERT INTO flashcard (card_id, question, answer, tags, user_id) " +
                "VALUES (default, ?, ?, ?, ?) RETURNING card_id;";
        Integer newCardId = jdbcTemplate.queryForObject(sql, Integer.class, newCard.getQuestion(), newCard.getAnswer(), newCard.getTags(), newCard.getUserId());

        newCard.setCardId(newCardId);

        return newCard;
    }

    @Override
    public Flashcard getFlashCardById(int cardId) {
        Flashcard flashcard = null;
        String sql = "SELECT card_id, question, answer, tags, user_id " +
                "FROM flashcard " +
                "WHERE card_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardId);
        if (results.next()) {
            flashcard = mapRowToFlashcard(results);
        }
        return flashcard;
    }

    @Override
    public void editFlashCardById(Flashcard flashcard) {
        String sql = "UPDATE flashcard " +
                "SET question = ?, answer = ?, tags = ? " +
                "WHERE card_id = ?;";
        jdbcTemplate.update(sql, flashcard.getQuestion(), flashcard.getAnswer(), flashcard.getTags(), flashcard.getCardId());
    }

    @Override
    public List<Flashcard> getFlashCardByTag(String tag) {
        List<Flashcard> flashcards = new ArrayList<>();
        String sql = "SELECT card_id, question, answer, tags, user_id " +
                "FROM flashcard " +
                "WHERE tags ILIKE '%' || LOWER( ? ) || '%';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tag);
        while (results.next()) {
            flashcards.add(mapRowToFlashcard(results));
        }
        return flashcards;
    }

    @Override
    public int getCardsIdByTag(String tag) {
        int newId = 0;
        List<Flashcard> flashcards =  getFlashCardByTag(tag);
        for (int i = 0; i < flashcards.size(); i++) {
            if(flashcards.get(i).getCardId() ==newId){
            }
        }
        return newId;

    }

    @Override
    public void deleteFlashCardById(int cardId) {
        String sql = "DELETE " +
                "FROM flashcard " +
                "WHERE card_id = ?;";
        jdbcTemplate.update(sql, cardId);
    }
    @Override
    public List<Flashcard> getAllFlashcardsFromUserId(int userId) {
        List<Flashcard> flashcards = new ArrayList<>();
        String sql = "SELECT card_id, question, answer, tags, user_id " +
                "FROM flashcard " +
                "WHERE user_id = ? " +
                "ORDER BY card_id DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            flashcards.add(mapRowToFlashcard(results));
        }
        return flashcards;
    }

//TODO: this needs changed in the controller first

//    @Override
//    public List<Flashcard> getAllFlashcardByUserId(int userId) {
//        List<Flashcard> flashcards = new ArrayList<>();
//        String sql = "SELECT f.card_id, f.question, f.answer, f.tags, f.username " +
//                "FROM flashcard AS f " +
//                "JOIN Users AS u ON u.username = f.username " +
//                "WHERE u.user_id = ?;";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//        while (results.next()){
//            flashcards.add(mapRowToFlashcard(results));
//        }
//        return flashcards;
//    }


    private Flashcard mapRowToFlashcard(SqlRowSet results) {
        Flashcard flashcard = new Flashcard();
        flashcard.setCardId(results.getInt("card_id"));
        flashcard.setQuestion(results.getString("question"));
        flashcard.setAnswer(results.getString("answer"));
        flashcard.setTags(results.getString("tags"));
        flashcard.setUserId(results.getInt("user_id"));
        return flashcard;
    }
}
