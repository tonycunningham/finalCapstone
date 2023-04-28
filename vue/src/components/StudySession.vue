<template>
  <div class="study-session">
    <h1 class="study-session-title">
      Study session with deck: {{ DeckDto.deckName }}
    </h1>
    <div
      class="card"
      v-if="currentIndex < flashcards.length"
      :class="{ flipped: flipped }"
      @click="flipped = !flipped"
    >
      <div class="card-front">
        <h2 class="card-question">{{ flashcards[currentIndex].question }}</h2>
      </div>
      <div class="card-back">
        <h2 class="card-answer">{{ flashcards[currentIndex].answer }}</h2>
      </div>
    </div>
    <div class="card-buttons">
      <button
        class="card-button"
        v-if="currentIndex === flashcards.length"
        @click="restartDeck()"
      >
        Finish
      </button>
    </div>
    <div class="card-buttons" v-if="currentIndex < flashcards.length">
      <button class="card-button-right" @click="markCardAsRight"> Right ✓</button>
      <button class="card-button-wrong" @click="markCardAsWrong"> Wrong ✕</button>
    </div>
  </div>
</template>
<script>
import Deckservice from "@/services/Deckservice.js";
export default {
  data() {
    return {
      DeckDto: {
        deckId: "",
        deckName: "",
        cardsInDeck: [],
        correctCards: [],
        incorrectCards: [],
      },
      flashcards: [],
      currentIndex: 0,
      flipped: false,
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
          this.DeckDto.correctCards = [];
          this.DeckDto.incorrectCards = [];
          this.flashcards = this.DeckDto.cardsInDeck.sort(
            (a, b) => a.cardId - b.cardId
          );
          this.currentIndex = 0;
        })
        .catch((error) => {
          console.error("Error fetching flashcards:", error);
        });
    },
    nextCard() {
      this.currentIndex++;
      this.flipped = false;
    },
    restartDeck() {
      this.showResults();
      this.currentIndex = 0;
      this.flipped = false;
      this.DeckDto.correctCards = [];
      this.DeckDto.incorrectCards = [];
    },
    markCardAsRight() {
      this.flashcards[this.currentIndex].correct = true;
      let temp = this.flashcards[this.currentIndex];
      this.DeckDto.correctCards.push(temp);
      this.nextCard();
    },
    markCardAsWrong() {
      this.flashcards[this.currentIndex].correct = false;
      let temp2 = this.flashcards[this.currentIndex];
      this.DeckDto.incorrectCards.push(temp2);
      this.nextCard();
    },
    showResults() {
      const totalCards =
        this.DeckDto.correctCards.length + this.DeckDto.incorrectCards.length;
      const percentageCorrect =
        (this.DeckDto.correctCards.length / totalCards) * 100;
      const message = `You got ${
        this.DeckDto.correctCards.length
      } out of ${totalCards} cards correct (${percentageCorrect.toFixed(2)}%).`;
      alert(`${message}`);
    },
  },
};
</script>

<style>
.study-session {
  text-align: center;
  margin-top: 2px;
  background-color: #616161;
  color: white;
  font-family: Arial, Helvetica, sans-serif;
  padding: 20px;
}
.study-session-title {
  margin-top: 0;
}
.card {
  position: relative;
  width: 900px;
  height: 425px;
  transform-style: preserve-3d;
  transition: transform 0.5s;
  display: inline-block;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  background-image: url("/src/assets/flashcard-template.jpg");
  background-size: cover;
}
.card.flipped {
  transform: rotateY(180deg);
}
.card-front,
.card-back {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 20px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  backface-visibility: hidden;
}
.card-front {
  z-index: 2;
}
.card-back {
  transform: rotateY(180deg);
}
.card-question {
  margin-bottom: 20px;
  font-size: 30px;
  color: black;
}
.card-answer {
  font-size: 30px;
  color: black;
}
.card-buttons {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-buttons button {
  width: 150px; /* sets fixed width for the buttons */
  background-color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  /* color: #9C27B0; */
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  margin: 0 10px; /* this separates the buttons */
}
.card-button-right:hover {
  /* background-color: rgb(103, 235, 103); */
  background-color: green;
  color: white;
}
.card-button-wrong:hover {
  /* background-color: rgb(247, 90, 90); */
  background-color: red;
  color: white;
}
.card-button:hover {
background-color: rgba(223, 222, 222);
}
</style>