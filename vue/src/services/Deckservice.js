import axios from 'axios';

export default {
    createDeck(deck){
        return axios.post('/Deck', deck)
    },

    // getDeckByDeckID(deckId){
    //     return axios.get(`/Deck/${deckId}`)
    // },
    
    getFlashcardsFromDeckByDeckId(deckId){
        return axios.get(`/Deck/${deckId}`)
    },

    getDecksByUserId(userId){
        return axios.get(`/Decks/${userId}`)
    },

    removeFlashcardFromDeckByCardId(dataObject) {
        return axios.delete('/Deckremove', dataObject)
    },

    changeNameOfDeckByDeckId(deckDto){
        return axios.put('/Deckrename', deckDto)
    },

    getDeckByDeckId(deckId){
        return axios.get(`/Deckname/${deckId}`)
    },

    addFlashcardToDeckByCardId(deckDto) {
        return axios.post(`/Deckadd`, deckDto)
    }
}