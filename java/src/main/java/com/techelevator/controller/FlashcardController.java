package com.techelevator.controller;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Flashcard;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class FlashcardController {

    private final FlashcardDao flashcardDao;
    private final UserDao userDao;


    public FlashcardController(FlashcardDao flashcardDao, UserDao userDao) {
        this.flashcardDao = flashcardDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/Flashcard/{cardId}", method = RequestMethod.GET)
    public Flashcard getFlashcardById(@PathVariable int cardId){
        Flashcard flashcard = flashcardDao.getFlashCardById(cardId);
        if(flashcard == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found.");
        } else {
            return flashcard;
        }
    }

    @RequestMapping(path = "/Flashcards/{tag}", method = RequestMethod.GET)
    public List<Flashcard> getFlashcardByTag(@PathVariable String tag){
        List<Flashcard> flashcardList = flashcardDao.getFlashCardByTag(tag);
        if(flashcardList == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No flashcards found with tag.");
        } else {
            return flashcardList;
        }
    }

//    @RequestMapping(path = "/Flashcards/{cardId}", method = RequestMethod.GET)
//    public int  getCardIdByTag(@Valid @RequestBody String tag, @PathVariable int cardId){
//        int selectedCardId = flashcardDao.getCardsIdByTag(tag);
//        if(selectedCardId <= 0){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No flashcards found with that Id.");
//        } else {
//            return selectedCardId;
//        }
//
//    }


    @RequestMapping(path = "/Flashcard", method = RequestMethod.POST)
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard){
        return flashcardDao.createFlashCard(flashcard);
    }

    @RequestMapping(path = "/Flashcard/{cardId}", method = RequestMethod.PUT)
    public void editFlashcardById(@Valid @RequestBody Flashcard flashcard, @PathVariable int cardId, Principal principal){
        Flashcard actualFlashcard = flashcardDao.getFlashCardById(cardId);
        int currentUserId = userDao.findIdByUsername(principal.getName());
        if(currentUserId != actualFlashcard.getUserId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied.");
        } else {
            flashcardDao.editFlashCardById(flashcard);
        }
    }

    @RequestMapping(path = "/Flashcard/{cardId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlashcardById(@Valid @PathVariable int cardId, Principal principal){
        Flashcard actualFlashcard = flashcardDao.getFlashCardById(cardId);
        int currentUserId = userDao.findIdByUsername(principal.getName());
        if(currentUserId != actualFlashcard.getUserId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You may not delete this card.");
        } else {
            flashcardDao.deleteFlashCardById(cardId);
        }
    }
    //    @RequestMapping(path = "Flashcard/User/{userId}", method = RequestMethod.GET)
//    public List<Flashcard> getAllFlashcardsFromUserId(@Valid @PathVariable int userId, Principal principal) {
//        List<Flashcard> flashcardList = flashcardDao.getAllFlashcardsFromUserId(userId);
//        if(flashcardList == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found.");
//        } else {
//            return flashcardList;
//        }
//    }
    @RequestMapping(path = "/Flashcards", method = RequestMethod.GET)
    public List<Flashcard> getAllFlashcardsFromUserId(Principal principal) {
        List<Flashcard> flashcardList = flashcardDao.getAllFlashcardsFromUserId(userDao.findIdByUsername(principal.getName()));
        if(flashcardList == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found.");
        } else {
            return flashcardList;
        }
    }
}
