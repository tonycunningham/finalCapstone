<template>
  <div class="deck-container">
    <form class="deck-form-container">
      <div class="deck-form-group">
        <label for="deckname" class="deck-form-label" style="color: white;">Create Deck</label>
        </div><div>
        <input v-model="deckName" type="text" id="deckName" class="deck-form-input" placeholder="Enter New Deck Name" />
      </div>
      <button type="submit" class="deck-form-button" @click="createDeck">Create</button>
    </form>
    <div v-if="deckId">
      <p>Deck created successfully with DeckId: {{ deckId }}</p>
      <p class="result-message">{{ successMessage }}</p>
    </div>
    <div v-if="errorMessage">
      <p class="result-message">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import Deckservice from "@/services/Deckservice.js";

export default {
  data() {
    return {
      deckName: "",
      deckId: null,
      successMessage: "",
      errorMessage: "",
      Decks: [],
    };
  },
  methods: {
    createDeck() {
      const deckData = {
        deckName: this.deckName,
        userId: this.$store.state.user.id,
      };
      Deckservice.createDeck(deckData).then((response) => {
          console.log("Deck has been created successfully!", response);
          this.deckId = response.data.deckId;
          this.successMessage = `Deck was created successfully with a DeckID: ${response.data.deckId}`;

          this.deckName = "";
        })
        .catch((error) => {
          console.error("Failed to create Deck", error);
          this.errorMessage = "Failed to create Deck, please try again.";
        });
    },
  },
};
</script>

<style>
.deck-container {
  font-family: Arial, Helvetica, sans-serif;
   background-color: #9C27B0;
  margin-top: 2px;
  border-right: 2px solid white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding-bottom: 8px;
  padding-top: 0px;
  border-top: 0px solid white;
  border-radius: 5px;
}

.deck-form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  margin-top: 1rem;
}

.deck-form-group {
  display: flex;
  align-items: center;
  margin-bottom: -0.5rem;
  font-weight: bold;
  font-size: 20px;
}

.deck-form-label {
  font-weight: bold;
  margin-right: 0.5rem;
}

.deck-form-input {
  max-width: 200px;
  padding: 0.5rem;
  border-radius: 5px;
  text-align: center;
}

.white-text {
  color: white;
}

.deck-form-button {
  padding: 0.5rem;
  background-color: white;
  color: black;
  font-weight: bold;
  cursor: pointer;
  width: 100px;
  display: inline-block;
  margin-top: -7px;
  
}

.result-message {
  text-align: center;
  margin-top: 0.5rem;
  font-size: 1rem;
}

.deck-form-button:hover {
  background-color: rgba(223, 222, 222);
}

.deck-form-button:focus {
  outline: none;
}

.result-message {
  text-align: center;
  margin-top: 0.5rem;
  font-size: 1rem;
}

</style>