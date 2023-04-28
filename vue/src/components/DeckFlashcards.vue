<template>
  <div class="deck-flashcards-container">
    <h1 class="deck-flashcards-title">
      Flashcards in Deck: {{ $store.state.currentdeckname }}
    </h1>
    <div class="deck-flashcards-line"></div>
    <div class="deck-flashcards-list">
      <div
        v-for="flashcard in this.$store.state.currentDeckFlashcards"
        :key="flashcard.cardId"
        class="deck-flashcards-item"
      >
        <span class="deck-flashcards-question"
          >Q: {{ flashcard.question }}</span
        >
        <span class="deck-flashcards-answer">A: {{ flashcard.answer }}</span>
        <div class="deck-flashcards-tags-container">
          <span class="deck-flashcards-cardId">Tags: {{ flashcard.tags }}</span>
          <span class="deck-flashcards-tags"
            >Card ID: {{ flashcard.cardId }}</span
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Deckservice from "@/services/Deckservice.js";
import flashcardImage from "../assets/flashcard-template.jpg";

export default {
  data() {
    return {
      DeckDto: {
        deckId: "",
        deckName: "",
        cardsInDeck: [],
      },
      flashcards: [],
      flipped: false,
      flashcardImage: flashcardImage,
    };
  },
  props: {
    deckId: {
      type: Number,
      required: true,
    },
  },
  watch: {
    deckId: {
      immediate: true,
      handler() {
        this.fetchFlashcards();
      },
    },
  },
  methods: {
    fetchFlashcards() {
      Deckservice.getFlashcardsFromDeckByDeckId(this.deckId)
        .then((response) => {
          this.DeckDto = response.data;
          this.flashcards = this.DeckDto.cardsInDeck.sort(
            (a, b) => a.cardId - b.cardId
          );
          this.$store.commit("SET_DECK_FLASHCARDS", this.flashcards);
        })
        .catch((error) => {
          console.error("Error fetching flashcards:", error);
        });
    },
    nextCard() {
      this.currentIndex++;
      this.flipped = false; // reset flipped to false when moving to next card
    },
    previousCard() {
      this.currentIndex--;
      this.flipped = false; // reset flipped to false when moving to previous card
    },
  },
};
</script>
<style>
.deck-flashcards-container {
  margin-top: 2px;
  border-right: 2px solid white;
  display: flex;
  flex-direction: column;
  align-items: row;
  justify-content: flex-start;
  background-image: 'backgroundstudy.jpg';
  backdrop-filter: blur(10px);
}
.deck-flashcards-title {
  background: #616161;
  color: white;
  text-align: center;
  max-width: 600px;
  align-self: center;
  border-radius: 10px;
  padding: 5px;
}
.deck-flashcards-list {
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

.deck-flashcards-item {
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
  /* text-shadow: 0.5px 0.5px 0.5px #000000; */
  background-repeat: no-repeat;
  background-position: center;
  background-size: 500px 250px;
  position: relative;
  background-image: url("../assets/flashcard-template.jpg");
  max-width: 500px;
  max-height: 250px;
}
.deck-flashcards-question {
  font-size: 25px;
  text-align: center;
  margin-bottom: 5px;
}
.deck-flashcards-answer {
  font-size: 25px;
  text-align: center;
  margin-bottom: 5px;
}
.deck-flashcards-tags-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.deck-flashcards-cardId {
  font-size: 20px;
  text-align: right;
}
.deck-flashcards-tags {
  font-size: 20px;
  text-align: left;
}
.deck-flashcards-item {
  background-image: url("../assets/flashcard-template.jpg");
  border-radius: 10px;
}
.deck-flashcards-tags-container {
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