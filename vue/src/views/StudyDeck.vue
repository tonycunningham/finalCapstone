<template>
  <div class="grid-container">
    <deck-list class="left-sidebar" @deck-clicked="viewDeck" />
    <div v-if="selectedDeckId === null" class="main-content">
      <div class="background-image"></div>
    </div>
    <study-session v-if="selectedDeckId !== null" :deckId="selectedDeckId" class="main-content" />
  </div>
</template>

<script>
import StudySession from '../components/StudySession.vue';
import DeckList from '../components/DeckList.vue'

export default {
  components: {StudySession, DeckList},
  name: "StudyDeck",
  data() {
    return {
      selectedDeckId: null,
    };
  },
  methods: {
    viewDeck(deckId){
      this.selectedDeckId = deckId;
    }
  },
  created() {
    if (this.$route.params.deckId) {
      this.selectedDeckId = this.$route.params.deckId;
    }
  } 
};
</script>

<style scoped>
  .grid-container {
    display: grid;
    grid-template-columns: 0.65fr 3fr; /* Left sidebar takes 1/4 of the page, main content takes 3/4 */
    grid-template-rows: 69vh;
  }
  
  .left-sidebar {
    grid-column: 1; /* Take up the first column */
    grid-row: 1; /* Take up the full height of the grid */
    border-radius: 5px;
    height: 66.8vh;
  }
  
  .main-content {
    grid-column: 2; /* Take up the second column */
    grid-row: 1; /* Take up the full height of the grid */
    border-radius: 5px;
  }
  
  .background-image {
    background-image: url("/src/assets/backgroundstudy.jpg");
    background-size: cover;
    width: 100%;
    height: 99.8%;
    margin-top: 2px;
    border-radius: 5px;
  }
</style>