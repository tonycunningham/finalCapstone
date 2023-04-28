<template>
  <div id="app">
    <img
      class="company-image"
      id="logo"
      src="./.\assets\ks.jpg"
      alt="photo goes here"
    />
    <div id="nav" class="navigation">
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp; -->
      <router-link v-bind:to="{ name: 'MyDecks' }">My Decks</router-link
      >&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'MyCards' }">My Cards</router-link
      >&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'SearchFlashcard' }"
        >Search Flashcards</router-link
      >&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'StudyDeck' }">Study Session</router-link
      >&nbsp;|&nbsp;
      <!-- <router-link v-bind:to="{ name: 'CreateDeck' }">Create Deck</router-link
      >&nbsp;|&nbsp; -->
      <!-- <router-link v-bind:to="{ name: 'CreateFlashcard' }"
        >Create Flashcards</router-link -->
      <!-- >&nbsp;|&nbsp; -->
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
      <!-- <router-link v-bind:to="{ name: 'register' }" >Register</router-link> -->
    </div>
    <router-view />
  </div>
</template>

<style>
.company-image {
  height: 232px;
  width: 1500px;
  display: block;
  margin: 0 auto;
}
.navigation {
  border-radius: 5px;
  color: white;
  font-family: Arial, Helvetica, sans-serif;
  background-color: #9c27b0;
  text-align: center;
  display: block;
  margin: 0 auto;
}
a:link,
a:visited {
  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}
</style>
