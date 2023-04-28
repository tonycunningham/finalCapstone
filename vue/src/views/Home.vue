<template>
  <div class="home">
    <h1>Home</h1>
    <p></p>
    <FlashcardComponent/>
  </div>
</template>

<script>

import FlashcardComponent from '@/components/FlashcardComponent.vue'



export default {

  components: {FlashcardComponent},
  name: "home",
  created () {
    
  }
};
</script>
<style>
h1 {
  text-align: center;
}
</style>
