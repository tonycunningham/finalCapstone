import axios from 'axios';

export default {
    getFlashcardByTag(tag){
        return axios.get(`/Flashcards/${tag}`)
    },

    getFlashcardById(cardId){
        return axios.get(`/Flashcard/${cardId}`)
    },

    createFlashcard(flashcard){
        return axios.post(`/Flashcard`, flashcard)
    },

    editFlashcardById(cardId, flashcard){
        return axios.put(`/Flashcard/${cardId}`, flashcard)
    },

    deleteFlashcardById(cardId){
        return axios.delete(`/Flashcard/${cardId}`)
    },
    getAllFlashcardsFromUserId(){
        return axios.get(`/Flashcards`)
    }
}
