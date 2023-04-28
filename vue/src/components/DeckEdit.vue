<template>
  <div class="deck-edit-container">
    <div class="deck-edit-remove-button-group">
      <label class="deck-edit-remove-flashcard-label" for="cardIdInput"
        >Remove Card From Deck {{ $store.state.currentdeckname }}
      </label>
      <div class="input-button-group">
        <input
        placeholder="Enter Card ID"
          type="text"
          id="cardIdInput"
          class="deck-edit-remove-flashcard"
          v-model="removeFlashcardId"
        />
        <button class="deck-edit-remove-button" @click="removeACard">
          Remove Card
        </button>
      </div>
    </div>
    <div class="deck-edit-rename-form">
      <label class="deck-edit-rename-label" for="deckNameInput"
        >Rename Deck {{ $store.state.currentdeckname }}
      </label>
      <div class="input-button-group">
        <input
        placeholder="Enter New Name"
          type="text"
          id="deckNameInput"
          class="deck-rename-form"
          v-model="newDeckName"
        />
      </div>
      <div class="button-group">
        <button class="deck-edit-rename-button" @click="handleRenameClick">
          Submit
        </button>
        <button class="deck-edit-cancel-button" @click="cancelEdit">
          Cancel
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Deckservice from "@/services/Deckservice.js";
export default {
  data() {
    return {
      newDeckName: "",
      removeFlashcardId: null,
      // renameSubmitted: false,

      deckDto: {
        deckId: null,
        deckName: "",
        cardId: null,
      },
    };
  },
  methods: {
    handleRenameClick() {
      console.log("KORIE");
      this.updateName();
      this.renameDeck();
    },

    updateName() {
      this.$store.commit("SET_DECK_NAME", this.newDeckName);
    },

    resetDeckFormInputs() {
      this.removeFlashcardId = null;
      this.newDeckName = "";
    },

    async renameDeck() {
      const deckData = {
        deckId: this.$store.state.currentdeckid,
        deckName: this.$store.state.currentdeckname,
        userId: this.$store.state.user.id,
      };
      Deckservice.changeNameOfDeckByDeckId(deckData).then((response) => {
        console.log(response.data);
        this.resetDeckFormInputs();
      });
      this.updatingTheStore();
    },
    updatingTheStore() {
      this.$store.commit("UPDATE_DECK_NAME", this.$store.state.currentdeckid);
    },

    cancelEdit() {
      this.resetDeckFormInputs();
    },
    removeACard() {
      const dataObj = {
        data: {
          deckId: this.$store.state.currentdeckid,
          cardId: Number(this.removeFlashcardId),
        },
      };
      Deckservice.removeFlashcardFromDeckByCardId(dataObj)
        .then(() => {
          this.$store.commit('REMOVE_FLASHCARD_FROM_DECK', this.removeFlashcardId)
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style>
.deck-edit-container {
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
  background-color: #9C27B0;
  margin-top: 2px;
  border-right: 2px solid white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 20px;
  padding-top: 20px;
  border-top: 0px solid white;
  color: white;
}
.deck-edit-remove-button,
.deck-edit-cancel-button,
.deck-edit-rename-button {
  padding: 0.5rem;
  background-color: white;
  color: black;
  cursor: pointer;
  width: 100px;
  display: inline-block;
  margin-left: 11px;
  margin-right: 0.5rem;
  border-radius: 5px;
  font-weight: bold;
}
.deck-edit-rename-form,
.deck-edit-remove-button-group {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.deck-edit-remove-button-group {
padding-left: 375px;
padding-top: 16px;
}
.deck-edit-rename-form{
  padding-right: 400px;
}
.deck-edit-remove-flashcard-label {
  margin-bottom: 0.5rem;
}
.deck-edit-rename-label{
  margin-bottom: 0.5rem;
}

.input-button-group {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 1rem;
  justify-content: center;
  text-align: center;
}

.button-group {
  display: flex;
  align-items: center;
}
.deck-edit-remove-flashcard,
.deck-rename-form {
  margin-left: 0.5rem;
  border-radius: 5px;
  text-align: center;
  height: 25px;
}
.deck-edit-remove-button {
  margin-top: 1rem;
  width: 125px;
}
.deck-edit-remove-button:hover,
.deck-edit-cancel-button:hover,
.deck-edit-rename-button:hover {
  background-color: rgba(223, 222, 222);
}

</style>