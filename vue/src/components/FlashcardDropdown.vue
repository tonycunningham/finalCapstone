<template>
  <div class="container">
    <div class="title-block">
      <h2>Add Flashcard to Deck</h2>
    </div>
    <div class="form">
      <div>
        <div class="enter-card">
          <label for="card-input">Enter Card ID: </label>
        </div>
        <input placeholder="Card ID" class="input-cardid" type="text" id="addCardInput" v-model="deckDto.cardId" />
      </div>
      
        <label class="choose-a-deck" for="selectedDeck">Choose A Deck:</label>
      
      <select class="drop-down-box" v-model="selectedDeck" placeholder="Pick a Deck">
        <option disabled selected value="">Choose A Deck</option>
        <option
          v-for="deck in this.$store.state.listofuserdecks"
          :key="deck.deckId"
          :value="deck.deckId"
        >
          {{ deck.deckName }}
        </option>
      </select>
      <div>
        <button class="add-card-button" @click="addCardtoDeck">Add Card</button>
      </div>
    </div>
  </div>
</template>

<script>
import Deckservice from "@/services/Deckservice.js";

export default {
  data() {
    return {
      deckDto: {
        deckId: null,
        cardId: null,
        deckname: this.$store.state.currentdeckname,
      },
      decks: [],
      selectedDeck: null,
    };
  },
  methods: {
    addCardAlert() {
      window.alert("Card has been added to your deck");
    },

    addCardtoDeck() {
      this.deckDto.deckId = this.selectedDeck;
      Deckservice.addFlashcardToDeckByCardId(this.deckDto).then((response) => {
        console.log(response.data);
        this.addCardAlert();
        this.deckDto.deckId;
        this.deckDto.cardId;
        this.deckDto.deckname;
        this.deckDto.cardId = null;
        this.selectedDeck = null;
      });
    },
    updateDeckId(deckId) {
      this.deckDto.deckId = deckId;
    },
  },
  created() {
    Deckservice.getDecks().then((response) => {
      this.decks = response.data;
    });
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-height: 100%;
  width: 410px;
  color: white;
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
  border-radius: 5px;
}

.title-block,
.form {
  border-bottom: 2px solid white;
  background-color: #9c27b0;
  width: 100%;
  border-radius: 5px;
  height: 63px;
}

.form {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 51.75vh;
  border-radius: 5px;
  padding-top: 100px;
  font-size: 25px;
}
.choose-a-deck {
  margin-top: 15px;
  margin-bottom: 15px;
  font-weight: bold;
}
.enter-card{
  padding-bottom: 15px;
  font-weight: bold;
}
.input-cardid{
  height: 30px;
  width: 200px;
  text-align: center;
  border-radius: 5px;
  
}
.drop-down-box{
  height: 30px;
  width: 200px;
  text-align: center;
  border-radius: 5px;
}
.add-card-button{
  align-self: center;
  height: 32px;
  width: 100px;
  margin-top: 15px;
    border-radius: 5px;
    font-weight: bold;
}
.add-card-button:hover {
  background-color: rgba(223, 222, 222);
}
</style>