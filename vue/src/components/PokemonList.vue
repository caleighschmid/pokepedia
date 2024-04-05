<template>
  <div>
     <div class="buttons">
      <!-- <button :class="{ 'disabled': !count > 0, 'previous': count > 0 }" v-on:click="getPrev">Previous</button> -->
      <button :disabled="!count > 0" :class="{ 'disabled': !count > 0, 'previous': count > 0 }"
        v-on:click="getPrev">Previous</button>
      <!-- <button :class="{ 'disabled': count < 101, 'next': !count < 101 }" @click="getNext">Next</button> -->
      <button :disable="!count < 1260" :class="{ 'disabled': count > 1260, 'next': count <= 1260 }"
        @click="getNext">Next</button>
    </div>
    <ul>
      <li v-for="pokemon in pokemonArray" :key="pokemon.id">
        <router-link :to="{
        name: 'detail',
        params: {
          id: pokemon.id,
        },
      }">
          {{ pokemon.id }} - {{ toSentenceCase(pokemon.name) }}
        </router-link>
      </li>
    </ul>
   

  </div>
</template>

<script>
import pokemonService from "@/services/PokemonService.js";
export default {
  name: "pokemon-list",
  data() {
    return {
      pokemonArray: [],
      count: 0
    };
  },
  methods: {
    getMore() {
      pokemonService.getMorePokemon(this.count)
        .then((response) => {
          // console.log(response.data.results);
          let temp = response.data.results;
          this.pokemonArray = temp.map((item) => {
            let pokemonIndex = item.url.indexOf('pokemon');
            let pokemonStr = item.url.substring(pokemonIndex);
            let slashIndex = pokemonStr.indexOf('/');
            let numberStr = pokemonStr.substring(slashIndex + 1, pokemonStr.length - 1);
            item.id = +numberStr;
            return item;
          })
        })
    },
    getNext() {
      this.count += 20;
      this.getMore();
    },
    getPrev() {
      this.count -= 20;
      this.getMore();
    },
    toSentenceCase(word) {
      const sentenceCaseWord = word.charAt(0).toUpperCase() + word.slice(1);
      return sentenceCaseWord;
    }
  },
  created() {
    pokemonService.getAllPokemon().then((response) => {
      console.log(response.data.results);
      let temp = response.data.results;
      this.pokemonArray = temp.map((item) => {
        let pokemonIndex = item.url.indexOf('pokemon');
        let pokemonStr = item.url.substring(pokemonIndex);
        let slashIndex = pokemonStr.indexOf('/');
        let numberStr = pokemonStr.substring(slashIndex + 1, pokemonStr.length - 1);
        item.id = +numberStr;
        return item;
      })
    });
  },
};
</script>

<style scoped>
/* Basic styling for the unordered list */
ul {
  list-style: none;
  padding: 15;
text-align: center;
}

/* Style each list item as a box with some padding and margin */
/* li {
  
} */

/* Add some spacing between list items */
li+li {
  margin-top: 10px;
}

/* Optional: Style links inside list items */
li a {
  color: #000000;
  text-decoration: none;
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif
}

li a:hover {
  text-decoration: underline;
}

button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border: none;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

/* Primary button style */
button.next {
  background-color: #a645e2;
  color: #fff;
}

button.previous {
  background-color: #a645e2;
  color: #fff;
}

/* Secondary button style */
button.disabled {
  opacity: 0.5;
  /* You can adjust the opacity to visually indicate the button is disabled */
  cursor: not-allowed;
  /* Optionally, you can add other styling like changing the background color */
}

/* Hover effect for all buttons */
button.next:hover,
button.previous:hover {
  background-color: #555;
  color: #fff;
}

div.buttons {
  display: flex;
  flex-direction: row;
  padding: 0px 20px;
  align-items: center;
  justify-content: center;
}

div.buttons button {
  margin-right: 10px;
  margin-top: 10px;
}

</style>