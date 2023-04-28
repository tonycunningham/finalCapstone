<template>
  <div class="flashcard-grid-container">
    <div class="background-image"></div>
    <flashcard-form class="flashcard-form" />
    <flashcard-list class="flashcard-flashcards" />
    <flashcard-edit :deckId="selectedDeckId" class="flashcard-edit" />
    <flashcard-delete class="flashcard-delete"/>
  </div>
</template>

<script>
import FlashcardList from '@/components/FlashcardList.vue'
import FlashcardEdit from '@/components/FlashcardEdit.vue';
import FlashcardForm from '@/components/FlashcardForm.vue';
import FlashcardDelete from '../components/FlashcardDelete.vue';


export default {
  name: "MyDecks",
  components: { 
    FlashcardList,
    FlashcardEdit,
    FlashcardForm,
    FlashcardDelete
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
    grid-column: 2; /* Take up the second column */
    grid-row: 1; /* Take up the full height of the grid */
    background-size: cover;
    width: 100%;
    height: 99.8%;
    margin-top: 2px;
  }

.flashcard-grid-container {
  display: grid;
  grid-template-columns: 17.65vw 81.63vw;
  grid-template-rows: 55vh 14.18vh;
  height: 667px;
}

.flashcard-form {
  border-radius: 5px;
  grid-row: 1 / 2;
  grid-column: 1 / 2;
  max-width: 400px;
  max-height: 526px;
}

.flashcard-delete {
  border-radius: 5px;
  grid-row: 2;
  grid-column: 1 / 2;
  max-width: 400px;
  max-height: 120px;
}

.flashcard-flashcards {
  border-radius: 5px;
  grid-row: 1 / 2;
  grid-column: 2;
  max-width: 100%;
  max-height: 100%;
}

.flashcard-edit {
  border-radius: 5px;
  grid-row: 2;
  grid-column: 2;
}
</style>