<template>
  <div class="flashcard-edit-container">
    <h1 class="flashcard-edit-title">Edit Flashcard</h1>
    <div class="flashcard-edit-form">
      <div class="flashcard-edit-input-group">
        <label class="flashcard-edit-label" for="questionInput"
          >New Question:</label
        >
        <input
        placeholder="Enter Question"
          type="text"
          id="questionInput"
          class="flashcard-edit-input"
          v-model="flashcard.question"
          required
        />
      </div>
      <div class="flashcard-edit-input-group">
        <label class="flashcard-edit-label" for="answerInput"
          >New Answer:</label
        >
        <input
        placeholder="Enter Answer"
          type="text"
          id="answerInput"
          class="flashcard-edit-input"
          v-model="flashcard.answer"
          required
        />
      </div>
      <div class="flashcard-edit-input-group">
        <label class="flashcard-edit-label" for="tagsInput"
          >New Tags:</label
        >
        <input
        placeholder="Enter Tags"
          type="text"
          id="tagsInput"
          class="flashcard-edit-input"
          v-model="flashcard.tags"
          required
        />
      </div>
      <div class="flashcard-edit-input-group">
        <label class="flashcard-edit-label" for="cardIdInput"
          >Card ID:</label
        >
        <input
        placeholder="Enter Card ID"
          type="text"
          id="cardIdInput"
          class="flashcard-edit-input"
          v-model="flashcard.cardId"
          required
        />
      </div>
      <div class="flashcard-edit-button-group">
        <button class="flashcard-edit-button" @click="editFlashcardById()">
          Save
        </button>
        <button class="flashcard-edit-button" @click="cancelEdit()">
          Cancel
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Flashcardservice from "@/services/Flashcardservice.js";
export default {
  data() {
    return {
      flashcard: {
        cardId: null,
        question: "",
        answer: "",
        tags: "",
        userId: this.$store.state.user.id,
      },
    };
  },
  methods: {
    resetFormInputs() {
      this.flashcard.cardId = null;
      this.flashcard.question = "";
      this.flashcard.answer = "";
      this.flashcard.tags = "";
    },
    editFlashcardById() {
      Flashcardservice.editFlashcardById(
        this.flashcard.cardId,
        this.flashcard
      ).then(() => {
        console.log(this.flashcard);
        this.$store.commit("UPDATE_FLASHCARD", this.flashcard);
        this.resetFormInputs();
      });
    },
    cancelEdit() {
      this.resetFormInputs();
    },
    created() {
      this.cardId = this.$route.params.id;
      Flashcardservice.getFlashcardById(this.cardId).then((response) => {
        console.log(response.data);
        this.flashcard = response.data;
      });
    },
  },
};
</script>

<style>
.flashcard-edit-container {
  font-family: Arial, Helvetica, sans-serif;
  background-color: #9c27b0;
  margin-top: 2px;
  border-right: 2px solid white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding-bottom: 20px;
  border-top: 0px solid white;
  color: white;
  padding-top: -5px;
  padding-left: 100px;
  padding-right: 0px;
}
.flashcard-edit-title {
  color: white;
  font-size: 24px;
  font-family: Arial, Helvetica, sans-serif;
  padding-right: 160px;
}
.flashcard-edit-button {
  background-color: white;
  color: black;
  cursor: pointer;
  width: 100px;
  display: inline-block;
  margin-left: 1rem;
  border-radius: 5px;
  font-weight: bold;
}

.flashcard-edit-form {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 85%;
}

.flashcard-edit-input-group {
  /* flex-basis: 15%; */
  width: 200px;
  height: 25px;
  text-align: center;
}

.flashcard-edit-button-group {
  padding-top: 14px;
  flex-basis: 30%;
  display: flex;
  flex-direction: row;
  justify-content: flex;
  align-self: center;
  height: 37px;
  width: 100px;
  border-radius: 5px;
}

.flashcard-edit-input {
  width: 100%; /* fill the available space */
  height: 100%;
  border-radius: 5px;
  text-align: center;
}

.flashcard-edit-button:hover {
  background-color: rgba(223, 222, 222);
}
</style>