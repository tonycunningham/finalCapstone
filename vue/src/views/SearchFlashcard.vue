<template>
  <div class="search-card-container">
    <flashcard-dropdown class:="flashcard-dropdown" />
    <flashcard-component />
  </div>
</template>

<script>
import FlashcardComponent from "../components/FlashcardComponent.vue";
import FlashcardDropdown from "../components/FlashcardDropdown.vue";

export default {
  components: { FlashcardComponent, FlashcardDropdown },
  name: "SearchFlashcard",
};
</script>

<style scoped>
.search-card-container {
  display: flex;
  margin-top: 2px;
}
</style>