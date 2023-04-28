<template>
  <div class="flashcard-delete-container">
    <form @submit.prevent="deleteFlashcard()" class="flashcard-delete-form-container">
      <div  class="flashcard-delete-form-group">
        <label for="deckname" class="flashcard-delete-form-label" style="color: white;">Delete Flashcard</label>
      </div>
      <div>
        <input placeholder="Enter Card ID" v-model="cardId" type="text" id="cardId" class="flashcard-delete-form-input" />
      </div>
      <button type="submit" class="flashcard-delete-form-button" >Delete</button>
    </form>
    <!-- <div v-if="cardId">
      <p>Flashcard deleted successfully</p>
      <p class="result-message">{{ successMessage }}</p>
    </div>
    <div v-if="errorMessage">
      <p class="result-message">{{ errorMessage }}</p>
    </div> -->
  </div>
</template>

<script>
import Flashcardservice from "@/services/Flashcardservice.js";

export default {
  data() {
    return {
        cardId: null,
        userId: this.$store.state.user.id,
      successMessage: "",
      errorMessage: "",
    };
  },
  methods: {
    deleteFlashcard() {
      if (confirm("This will remove the flashcard from any deck it is in.")) {
        Flashcardservice.deleteFlashcardById(this.cardId)
          .then(() => {
            // this.successMessage = `Flashcard was deleted successfully`;
            this.$store.commit('REMOVE_FLASHCARD_FROM_USER_DECK', this.cardId)
            this.cardId = "";
        })
        .catch((error) => {
          console.error("Failed to create Deck", error);
          // this.errorMessage = "Failed to delete flashcard, please try again.";
        });
      }
    },
  },
};
</script>

<style>
.flashcard-delete-container {
  font-family: Arial, Helvetica, sans-serif;
   background-color: #9C27B0;
  margin-top: 2px;
  border-right: 2px solid white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding-bottom: 20px;
  padding-top: 0px;
  border-top: 0px solid white;
}

.flashcard-delete-form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  margin-top: 1rem;
}

.flashcard-delete-form-group {
  display: flex;
  align-items: center;
  margin-bottom: -0.3rem;
}

.flashcard-delete-form-label {
  font-weight: bold;
  margin-right: 0.5rem;
  font-size: 20px;;
}

.flashcard-delete-form-input {
  max-width: 200px;
  flex-grow: 1;
  padding: 0.5rem;
  padding-top: -40px;
  margin-bottom: -8px;
  border-radius: 5px;
  text-align: center;
}

.white-text {
  color: white;
}

.flashcard-delete-form-button {
  padding: 0.5rem;
  background-color: white;
  color: black;
  cursor: pointer;
  width: 100px;
  display: inline-block;
  margin-left: 0rem;
  border-radius: 5px;
  font-weight: bold;
}

.result-message {
  text-align: center;
  margin-top: 0.5rem;
  font-size: 1rem;
}

.flashcard-delete-form-button:hover {
  background-color: rgba(223, 222, 222);
}

.flashcard-delete-form-button:focus {
  outline: none;
}

.result-message {
  text-align: center;
  margin-top: 0.5rem;
  font-size: 1rem;
}

</style>