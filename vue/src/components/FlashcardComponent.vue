<template>
  <div class="container">
    <div class="card-search">
      <h2 class="title">Search Flashcards:</h2>
      <div>
        <label for="tagInput"></label>
      </div>
      <input v-model="tagInput" type="text" id="tagInput" placeholder="Enter Flashcard Tag" class="input-text"/>
      <div>
        <button @click="getFlashcardByTag" class="search-button">Search</button>
      </div>
    </div>
    <div class="background">
      <div
        class="flashcard"
        v-for="flashcard in flashcards"
        :key="flashcard.id"
      >
        <span>Q: {{ flashcard.question }}</span>
        <span>A: {{ flashcard.answer }}</span>
        <div class="small-text">
          <span class="card-tags">Tags: {{ flashcard.tags }}</span>
          <span class="card-id">Card Id: {{ flashcard.cardId }}</span>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import Flashcardservice from "@/services/Flashcardservice.js";

export default {
  data() {
    return {
      flashcard: {
        question: "",
        answer: "",
        tags: "",
        userId: "",
        cardId: "",
      },
      flashcards: [],
      tagInput: "",
    };
  },
  methods: {
    getFlashcardByTag() {
      Flashcardservice.getFlashcardByTag(this.tagInput).then((response) => {
        console.log(response.data);
        this.flashcards = response.data;
      });
    },
  },
};
</script>

<style scoped>
.container {
  max-height: 100%;
  width: 100%;
  color: white;
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
}

.card-search {
  display: flex;
  flex-direction: row;
  background-color: #9c27b0;
  border-radius: 5px;
  align-items: center;
  border-left: 2px solid white;
  height: 63px;
  padding-left: 420px;
}

.flashcard {
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
  background-image: url("../assets/flashcard-template.jpg");
  font-size: 25px;
  max-width: 500px;
  max-height: 250px;
  text-align: center;
  margin-bottom: 5px;
}

.small-text {
  font-size: 20px;
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

.card-tags {
  text-align: left;
}

.card-id {
  text-align: right;
}

.background {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  list-style: none;
  margin: 0;
  padding: 0;
  margin-top: 0px;
  overflow-y: scroll;
  padding-bottom: 50px;
  margin-left: 2px;
  background-image: url("/src/assets/backgroundstudy.jpg");
  backdrop-filter: blur(10px);
  background-size: cover;
  width: 100%;
  height: 100%;
  max-height: 547px;
  margin-top: 2px;
  border-radius: 5px;
}
.title{
  padding-right: 20px;
}
.input-text{
  height: 25px;
  width: 250px;
  text-align: center;
  margin-right: 25px;
    border-radius: 5px;
}
.search-button{
  align-self: center;
  height: 32px;
  width: 100px;
    border-radius: 5px;
    font-weight: bold;
}
.search-button:hover {
  background-color: rgba(223, 222, 222);
}
</style>