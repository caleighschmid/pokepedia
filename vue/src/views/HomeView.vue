<template>
  <div class="home">
    <home />

    <div class="links">
      <router-link v-bind:to="{ name: 'pokemon' }" class="button">See All Pokemon</router-link>
      <router-link v-bind:to="{ name: 'abilities' }" class="button">See All Abilities</router-link>
      <router-link v-bind:to="{ name: 'favorite' }" class="button">My Favorite Pokemon</router-link>
    </div>

  </div>
</template>

<script>
import Home from '../components/Home.vue';

export default {
  components: { Home },
};
</script>

<style scoped>

.links{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.button{
  width: 200px;
    /* Adjust the width based on your design */
    margin: 10px;
    padding: 15px 15px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color: inherit;
    cursor: pointer;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.button:hover{
  background-color: rgb(195, 167, 235);
}

</style>