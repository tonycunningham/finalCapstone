<template>
  <div class="flashcardList-container">
    <h1 class="flashcardList-title"></h1>
    <div class="flashcardList-line"></div>
    <div class="flashcardList-list">
      <div
        v-if="selectedFlashcard"
        class="flashcardList-item"
      >
        <span class="flashcardList-question">Q: {{ selectedFlashcard.question }}</span>
        <span class="flashcardList-answer">A: {{ selectedFlashcard.answer }}</span>
        <div class="flashcardList-tags-container">
          <span class="flashcardList-cardId">Tags: {{ selectedFlashcard.tags }}</span>
          <span class="flashcardList-tags">Card ID: {{ selectedFlashcard.cardId }}</span>
        </div>
      </div>
      <div
        v-else
        v-for="flashcard in this.$store.state.currentusersflashcards"
        :key="flashcard.cardId"
        class="flashcardList-item"
      >
        <span class="flashcardList-question">Q: {{ flashcard.question }}</span>
        <span class="flashcardList-answer">A: {{ flashcard.answer }}</span>
        <div class="flashcardList-tags-container">
          <span class="flashcardList-cardId">Tags: {{ flashcard.tags }}</span>
          <span class="flashcardList-tags">Card ID: {{ flashcard.cardId }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Flashcardservices from '../services/Flashcardservice';
import flashcardImage from '../assets/flashcard-template.jpg';

export default {
  data() {
    return {
      flashcards: [],
      currentUserId: this.$store.state.user.id,
      flashcardImage: flashcardImage,
      selectedFlashcard: null,
    };
  },
  created() {
    Flashcardservices.getAllFlashcardsFromUserId(this.currentUserId).then((response) => {
      if (response.status === 200) {
        this.flashcards = response.data;
        this.$store.commit('SET_FLASHCARDS_BY_USERID', response.data)
      }
    });
  },
  methods: {
    selectFlashcard(flashcard) {
      // Handle when a flashcard item is clicked
      this.selectedFlashcard = flashcard;
    },
  },
};
</script>

<style scoped>
.flashcardList-container {
  margin-top: 2px;
  border-right: 2px solid white;
  display: flex;
  flex-direction: column;
  align-items: row;
  justify-content: flex-start;
  background-image: 'backgroundstudy.jpg';
  backdrop-filter: blur(10px);
}
.flashcardList-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  list-style: none;
  margin: 0;
  padding: 0;
  margin-top: 0px;
  overflow-y: scroll;
  padding-bottom: 50px;
}

.flashcardList-item {
  flex: 0 0 calc(33.33% - 20px);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 500px;
  height: 250px;
  margin: 10px;
  color: black;
  font-weight: bold;
  background-repeat: no-repeat;
  background-position: center;
  background-size: 500px 250px;
  position: relative;
  background-image: url('../assets/flashcard-template.jpg');
  max-width: 500px;
  max-height: 250px;
  font-family: Arial, Helvetica, sans-serif;
}
.flashcardList-question {
  font-size: 25px;
  text-align: center;
  margin-bottom: 5px;
}
.flashcardList-answer {
  font-size: 25px;
  text-align: center;
  margin-bottom: 5px;
}

.flashcardList-cardId {
  font-size: 20px;
  text-align: right;
}
.flashcardList-tags {
  font-size: 20px;
  text-align: left;
}
.flashcardList-tags-container {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  padding: 10px;
  box-sizing: border-box;
}
</style>