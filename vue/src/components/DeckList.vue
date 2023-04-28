<template>
  <div class="deckList-container">
    <h1 class="deckList-title">My Decks</h1>
    <div class="deckList-line"></div>
    <ul class="deckList-list">
      <li
        v-for="deck in this.$store.state.listofuserdecks"
        :key="deck.deckId"
        @click="selectDeck(deck.deckId)"
        class="deckList-item"
      >
        <span>{{ deck.deckName }}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import Deckservice from "@/services/Deckservice.js";

export default {
  data() {
    return {
      decks: [],
      selectedDeckId: null,
      currentDeck: this.$store.state.listofuserdecks[this.deckId]
    };
  },
  created() {
    this.fetchDecks();
  },
  methods: {
    fetchDecks() {
      console.log(this.$store.state.currentdeckname);
      Deckservice.getDecksByUserId(this.$store.state.user.id)
        .then((response) => {
          this.decks = response.data;
          this.$store.commit("SET_LIST_OF_USERS_DECKS", response.data);
        })
        .catch((error) => {
          console.error("Error fetching decks:", error);
        });
    },
    selectDeck(deckId) {
      this.selectedDeckId = deckId;
      this.$store.commit("SET_DECK_ID", deckId);
      const selectedDeck = this.decks.find((deck) => deck.deckId === deckId);
      if (selectedDeck) {
        this.$store.commit("SET_DECK_NAME", selectedDeck.deckName);
      }
      this.$emit("deck-clicked", deckId);
    },
  },
};
</script>

<style>
.deckList-container {
  background-color: #9C27B0;
  margin-top: 2px;
  border-right: 2px solid white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding-top: 20px;
}

.deckList-title {
  color: white;
  font-size: 24px;
  text-align: center;
  margin: 0;
  margin-bottom: 10px;
  font-family: Arial, Helvetica, sans-serif;
  margin-top: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 0.1;
}

.deckList-line {
  height: 2px;
  width: 100%;
  background-color: white;
  margin-top: 5px;
  margin-bottom: 10px;
}

.deckList-list {
  list-style: none;
  margin: 0;
  padding-right: 92px;
  margin-top: 30px;
  overflow: auto;
}

.deckList-item {
  color: white;
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-family: Arial, Helvetica, sans-serif;
  background-image: url("/src/assets/deckcase.png");
  background-repeat: no-repeat;
  background-size: contain;
  background-position: left center;
  padding-left: 50px;
  margin-bottom: 40px;
  font-size: 20px;
}

.deckList-item:hover {
  background-color: #663399;
}
</style>