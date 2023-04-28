import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    currentdeckid: 0,
    currentdeckname: "",
    listofuserdecks: [],
    currentflashcards: [],
    currentusersflashcards: [],
    currentDeckFlashcards: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_DECK_ID(state, deckId) {
      state.currentdeckid = deckId;
    },
    SET_DECK_NAME(state, deckName) {
      state.currentdeckname = deckName;
    },
    SET_FLASHCARDS(state, flashcards){
      state.currentflashcards = flashcards;
    },
    SET_LIST_OF_USERS_DECKS(state, listofusersdeck){
      state.listofuserdecks = listofusersdeck;
    },
    UPDATE_DECK_NAME(state, deckId){
      state.listofuserdecks.find(deck => {
        return deck.deckId == deckId
      }).deckName = state.currentdeckname
    },
    SET_FLASHCARDS_BY_USERID(state, listofflashcards){
      state.currentusersflashcards = listofflashcards;
    },
    SET_DECK_FLASHCARDS(state, flashcards){
      state.currentDeckFlashcards = flashcards;
    },
    REMOVE_FLASHCARD_FROM_DECK(state, cardId){
      state.currentDeckFlashcards = state.currentDeckFlashcards.filter(card => {
        return card.cardId != cardId;
      })
    },
    REMOVE_FLASHCARD_FROM_USER_DECK(state, cardId){
      state.currentusersflashcards = state.currentusersflashcards.filter(card => {
        return card.cardId != cardId;
      })
    },
    ADD_FLASHCARD_TO_LIST(state, flashcard){
      state.currentusersflashcards.push(flashcard);
    },
    UPDATE_FLASHCARD(state, flashcard){
      let i = state.currentusersflashcards.findIndex(card => {
        return card.cardId == flashcard.cardId;
      }) 
      // eslint-disable-next-line no-unused-vars
      state.currentusersflashcards[i].question = flashcard.question;
      state.currentusersflashcards[i].answer = flashcard.answer;
      state.currentusersflashcards[i].tags = flashcard.tags;
    }
  },
  
})
