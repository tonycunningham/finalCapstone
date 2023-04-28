package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.DeckCardsDto;
import com.techelevator.model.Flashcard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDao implements DeckDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Deck createDeck(Deck deck) {
        String sql = "INSERT INTO deck (deck_name, user_id) " +
                "VALUES (?, ?) RETURNING deck_id;";
        Integer newDeckId = jdbcTemplate.queryForObject(sql, Integer.class, deck.getDeckName(), deck.getUserId());
        deck.setDeckId(newDeckId);
        return deck;
    }
    @Override
    public Deck getDeckByDeckId(int deckId){
        String sql = "SELECT deck_id, deck_name, user_id FROM deck " +
                "WHERE deck_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        if(results.next()){
            return mapRowToDeck(results);
        }
        return null;
    }
    @Override
    public List<Deck> getDecksByUserId(int userId){
        List<Deck> decks = new ArrayList<>();
        String sql = "SELECT deck_id, deck_name, user_id FROM deck " +
                "WHERE user_id = ? " +
                "ORDER BY deck_id DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            decks.add(mapRowToDeck(results));
        }
        return decks;
    }
    @Override
    public void addFlashcardToDeckByCardId(int deckId, int cardId) {
        String sql = "INSERT INTO deck_flashcard (deck_id, card_id) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(sql, deckId, cardId);
    }
    @Override
    public void removeFlashcardFromDeckByCardId(int deckId, int cardId) {
        String sql = "DELETE FROM deck_flashcard " +
                "WHERE deck_id = ? AND card_id = ? " +
                "AND ctid = (SELECT ctid FROM deck_flashcard WHERE deck_id = ? AND card_id = ? LIMIT 1);";
        jdbcTemplate.update(sql, deckId, cardId, deckId, cardId);
    }

    @Override
    public void changeNameOfDeckByDeckId(String newName, int deckId) {
        String sql = "UPDATE deck " +
                "SET deck_name = ? " +
                "WHERE deck_id = ?;";
        jdbcTemplate.update(sql, newName, deckId);
    }

    @Override
    public DeckCardsDto getFlashcardsFromDeckByDeckId(int deckId) {
        List<Flashcard> flashCardsInDeck = new ArrayList<>();
        DeckCardsDto newDeck = new DeckCardsDto();
        String sql = "SELECT df.card_id, d.user_id, question, answer, tags, d.deck_name " +
                "FROM deck_flashcard AS df " +
                "JOIN flashcard AS f ON df.card_id = f.card_id " +
                "JOIN deck AS d ON df.deck_id = d.deck_id " +
                "WHERE df.deck_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        while (results.next()) {
            flashCardsInDeck.add(mapRowCardsToDeck(results));
            newDeck.setDeckName(results.getString("deck_name"));
        }
        newDeck.setDeckId(deckId);
        newDeck.setCardsInDeck(flashCardsInDeck.toArray(new Flashcard[0]));
        return newDeck;

    }
//    @Override
//    public List<DeckCardsDto> getListofDeckCardDtoFromUserId(int userId) {
//        List<DeckCardsDto> deckCardsDtos = new ArrayList<>();
//        String sql = "";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//        while (results.next()) {
//            deckCardsDtos.add()
//
//        }
//    }


    private Deck mapRowToDeck(SqlRowSet results){
        Deck deck = new Deck();
        deck.setDeckId(results.getInt("deck_id"));
        deck.setDeckName(results.getString("deck_name"));
        deck.setUserId(results.getInt("user_id"));
        return deck;
    }
    private Flashcard mapRowCardsToDeck(SqlRowSet results){
        Flashcard deck = new Flashcard();
        deck.setCardId(results.getInt("card_id"));
        deck.setUserId(results.getInt("user_id"));
        deck.setQuestion(results.getString("question"));
        deck.setAnswer(results.getString("answer"));
        deck.setTags(results.getString("tags"));

        return deck;
    }

}
