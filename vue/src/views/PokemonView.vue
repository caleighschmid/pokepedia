<template>
    <div>
      <pokemon-list />
    </div>
  </template>
  
  <script>
  import PokemonList from "../components/PokemonList.vue";
  export default {
    components: { PokemonList },
    name: "pokemon-view",
  };
  </script>
  
  <style>
  </style>