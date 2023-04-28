<template>
  <div class="mydeck-grid-container">
    <deck-list @deck-clicked="viewDeck" class="mydeck-deck-list" />
    <div class="background-image"></div>
    <deck-form class="mydeck-deck-form" />
    <deck-flashcards v-if="selectedDeckId !== null" :deckId="selectedDeckId" class="mydeck-deck-flashcards"></deck-flashcards>
    <deck-edit :deckId="selectedDeckId" class="mydeck-deck-edit" />
  </div>
</template>

<script>
import DeckList from '@/components/DeckList.vue';
import DeckFlashcards from '@/components/DeckFlashcards.vue'
import DeckEdit from '../components/DeckEdit.vue';
import DeckForm from './DeckForm.vue';


export default {
  name: "MyDecks",
  components: {
    DeckList, 
    DeckFlashcards,
    DeckEdit,
    DeckForm
    },
    data() {
      return {
        selectedDeckId: null,
      };
    },
    methods: {
      viewDeck(deckId){
        console.log("loggingDeckId")
        console.log(deckId)
        this.selectedDeckId = deckId;
        this.$store.commit('SET_DECK_ID', this.selectedDeckId)
      }
    },
    created() {
      if (this.$route.params.deckId) {
        this.selectedDeckId = this.$route.params.deckId;
      }
    } 
};
</script>

<style>
 .background-image {
    background-image: url("/src/assets/backgroundstudy.jpg");
    background-size: cover;
    width: 100%;
    height: 99.8%;
    margin-top: 2px;
    border-radius: 5px;
  }

.mydeck-grid-container {
  display: grid;
  grid-template-columns: 17.65vw 81.5vw;
  grid-template-rows: 55vh 14.18vh;
  height: 667px;
}

.mydeck-deck-list {
  border-radius: 5px;
  grid-row: 1 / 2;
  grid-column: 1 / 2;
  max-width: 400px;
  max-height: 506px;
}

.mydeck-deck-form {
  border-radius: 5px;
  grid-row: 2;
  grid-column: 1 / 2;
  max-width: 400px;
  max-height: 100px;
}

.mydeck-deck-flashcards {
  border-radius: 5px;
  grid-row: 1 / 2;
  grid-column: 2;
  max-width: 100%;
  max-height: 100%;
}

.mydeck-deck-edit {
  border-radius: 5px;
  grid-row: 2;
  grid-column: 2;
}
</style>