<template>
  <div class="flashcard-form-container">
    <h1 class="flashcard-form-title">Create Flashcard</h1>
    <form
      @submit.prevent="createFlashcard()"
      class="flashcard-form-create-form-container"
    >
      <div class="flashcard-form-group-create-card">
        <label for="question" class="flashcard-form-label">Question:</label>
      </div>
      <div>
        <input
        placeholder="Enter Question"
          v-model="flashcard.question"
          type="text"
          id="question"
          class="flashcard-form-input"
        />
      </div>
      <div class="flashcard-form-group-create-card">
        <label for="answer" class="flashcard-form-label">Answer:</label>
      </div>
      <div>
        <input
        placeholder="Enter Answer"
          v-model="flashcard.answer"
          type="text"
          id="answer"
          class="flashcard-form-input"
        />
      </div>
      <div class="flashcard-form-group-create-card">
        <label for="tags" class="flashcard-form-label">Tags:</label>
      </div>
      <div>
        <input
        placeholder="Enter Tags"
          v-model="flashcard.tags"
          type="text"
          id="tags"
          class="flashcard-form-input"
        />
      </div>
      <div class="flashcard-form-create-button">
        <button type="submit" class="flashcard-form-button">Create</button>
      </div>
    </form>
  </div>
</template>

<script>
import Flashcardservice from "@/services/Flashcardservice.js";

export default {
  data() {
    return {
      flashcard: {
        question: "",
        answer: "",
        tags: "",
        cardId: "",
        userId: this.$store.state.user.id,
      }
    };
  },
  methods: {
    createFlashcard() {
      Flashcardservice.createFlashcard(this.flashcard)
        .then((response) => {
          this.flashcard.cardId = response.data.cardId;

          this.$store.commit("ADD_FLASHCARD_TO_LIST", response.data);
          this.flashcard.question = "";
          this.flashcard.answer = "";
          this.flashcard.tags = "";
          this.flashcard.cardId = null;
        })
        .catch((error) => {
          console.error("Failed to create flashcard", error);
        });
    },
  },
};
</script>

<style scoped>
.flashcard-form-container {
  font-family: Arial, Helvetica, sans-serif;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #9C27B0;
  margin-top: 2px;
  margin-right: 2px;
}

.flashcard-form-title {
  font-size: 2em;
  color: white;
  text-align: center;
  border-bottom: 2px solid white;
  margin-bottom: 20px;
  padding-bottom: 20px;
  width: 100%;
  box-sizing: border-box;
  color: white;
  font-size: 24px;
  font-family: Arial, Helvetica, sans-serif;
}

.flashcard-form-create-form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 26px;
}

.flashcard-form-group-create-card {
  margin-bottom: 10px; /* spaces the boxes */
}

.flashcard-form-label {
  color: white;
  font-weight: bold;
  font-size: 23px;
}

.flashcard-form-input {
  padding: 10px;
  margin-top: 5px;
  width: 30vh;
  box-sizing: border-box;
  margin-bottom: 20px;
  border-radius: 5px;
  text-align: center;
}

.flashcard-form-create-button {
  margin-top: 20px;
}

.flashcard-form-button {
  padding: 12px;
  background-color: white;
  color: black;
  font-weight: bold;
  cursor: pointer;
  border-radius: 5px;
  width: 150px;
}
.flashcard-form-button:hover {
  background-color: rgba(223, 222, 222);
}

.flashcard-form-success {
  margin-top: 20px;
  color: white;
}

.flashcard-form-error {
  margin-top: 20px;
  color: red;
}
</style>