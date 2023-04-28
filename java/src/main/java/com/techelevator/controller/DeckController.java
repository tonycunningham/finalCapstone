package com.techelevator.controller;

import com.techelevator.dao.DeckDao;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Deck;
import com.techelevator.model.DeckCardsDto;
import com.techelevator.model.DeckDto;
import com.techelevator.model.Flashcard;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class DeckController {

    private final DeckDao deckDao;
    private final UserDao userDao;

    public DeckController(DeckDao deckDao, FlashcardDao flashcardDao, UserDao userDao) {
        this.deckDao = deckDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/Deck", method = RequestMethod.POST)
    public void createDeck(@Valid @RequestBody Deck deck){
        if(deck == null || deck.getDeckName().equals("") || deck.getDeckName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deck name must not be empty.");
        }
        deckDao.createDeck(deck);
    }
    @RequestMapping(path = "/Deckname/{deckId}", method = RequestMethod.GET)
    public Deck getDeckByDeckId(@Valid @RequestBody int deckId){
        if(deckId < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck does not exist");
        }
        return deckDao.getDeckByDeckId(deckId);
    }
    @RequestMapping(path = "/Deck/{deckId}", method = RequestMethod.GET)
    public DeckCardsDto getFlashcardsFromDeckByDeckId(@Valid @PathVariable int deckId){
        if(deckId < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck does not exist");
        }
        return deckDao.getFlashcardsFromDeckByDeckId(deckId);
    }
    @RequestMapping(path = "/Decks/{userId}", method = RequestMethod.GET)
    public List<Deck> getDecksByUserId(@Valid @PathVariable int userId){
        List<Deck> deckList = deckDao.getDecksByUserId(userId);
        if (deckList.size() < 1){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User has no decks");
        }
        return deckList;
    }
    @RequestMapping(path = "/Deckadd", method = RequestMethod.POST)
    public void addFlashcardToDeckByCardId(@Valid @RequestBody DeckDto deckDto){
        if(deckDto.getDeckId() < 1 || deckDto.getCardId() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck and or Card does not exist");
        }
        deckDao.addFlashcardToDeckByCardId(deckDto.getDeckId(), deckDto.getCardId());
    }
    @RequestMapping(path = "/Deckremove", method = RequestMethod.DELETE)
    public void removeFlashcardFromDeckByCardId(@Valid @RequestBody DeckDto deckDto){
        if(deckDto.getDeckId() < 1 || deckDto.getCardId() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck and or Card does not exist");
        }
        deckDao.removeFlashcardFromDeckByCardId(deckDto.getDeckId(), deckDto.getCardId());
    }
    @RequestMapping(path = "/Deckrename", method = RequestMethod.PUT)
    public void changeNameOfDeckByDeckId(@Valid @RequestBody Deck deck, Principal principal){
        int currentUserId = userDao.findIdByUsername(principal.getName());
        Deck actualDeck = deckDao.getDeckByDeckId(deck.getDeckId());
        int deckNameLength = deck.getDeckName().length();
        if(deck.getDeckId() < 1 || actualDeck.getUserId() != currentUserId || deckNameLength > 15) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck does not exist or does not belong to you");
        }
        deckDao.changeNameOfDeckByDeckId(deck.getDeckName(), deck.getDeckId());
    }
}
