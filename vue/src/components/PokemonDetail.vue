<template>
  <div>
    <div class="pokemon-info">
      <div class="info">
        <h1 id="name"> {{ toSentenceCase(pokemon.name) }} </h1>
        <div id="types">
          <span v-for="(pokemonType, index) in types" :key="pokemonType.type">
            {{ toSentenceCase(pokemonType.type.name) }}
            <span v-if="index < types.length - 1" class="separator"> &nbsp;| </span>
          </span>
        </div>

        <!-- <p>Height: {{ pokemon.height }}</p>
        <p>Weight: {{ pokemon.weight }}</p> -->

        <div id="abilities">
          <span style="font-weight: bold;">Abilities: </span>
          <br>
          <span v-for="ability in abilities" :key="ability.name">
            <router-link :to="{
          name: 'ability-detail',
          params: {
            id: extractId(ability.ability.url)
          },
        }">
              {{ toSentenceCase(ability.ability.name) }}
              <span v-if="ability.is_hidden === true">(hidden)</span>
            </router-link>
            <br>
          </span>
        </div>

        <img :src="currentSpriteImg" alt="" id="images" />

      </div>


    </div>


    <div class="buttons">
      <router-link :to="{ name: 'pokemon' }">
        <button>Return to List</button>
      </router-link>
      <button v-on:click="saveFavorite">Save to Favorites</button>
    </div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";
import pokemonService from "../services/PokemonService";
export default {
  name: "pokemon-detail",
  props: {
    id: String,
  },
  data() {
    return {
      pokemon: {},
      spriteImgBack: "",
      spriteImgFront: "",
      abilities: {},
      types: {},
      currentSpriteIndex: 0,
    };
  },
  computed: {
    currentSpriteImg() {
      if (this.spriteImgBack === null) {
        return this.spriteImgFront
      } else {
        return this.currentSpriteIndex % 2 === 0
        ? this.spriteImgFront
        : this.spriteImgBack;
      }
    },
  },
  methods: {
    saveFavorite() {
      let newPokemon = {
        apiId: this.pokemon.id,
        name: this.pokemon.name,
        base_experience: this.pokemon.base_experience,
        height: this.pokemon.height,
        weight: this.pokemon.weight,
        sprites: {
          back_default: this.spriteImgBack,
          front_default: this.spriteImgFront
        }
      };
      AuthService.saveFavorite(newPokemon)
        .then((response) => {
          if (response.status === 201) {
            alert("Pokemon has been saved!");
            this.$router.push({ name: 'pokemon' })
          } 
        }).catch((error) => {
          alert("This pokemon has already been saved.");
        })
    },
    toSentenceCase(word) {
      if (typeof word !== 'string' || word.length === 0) {
        return ''; // Return an empty string or handle accordingly
      }
      const sentenceCaseWord = word.charAt(0).toUpperCase() + word.slice(1);
      return sentenceCaseWord;
    },
    extractId(url) {
      let abilityIndex = url.indexOf('ability');
      let abilityStr = url.substring(abilityIndex);
      let slashIndex = abilityStr.indexOf('/');
      let numberStr = abilityStr.substring(slashIndex + 1, abilityStr.length - 1);
      return numberStr;
    }
  },
  created() {
    let numId = +this.id;
    pokemonService.getPokemonDetail(numId).then((response) => {
      console.log(response.data);
      this.pokemon = response.data;
      this.spriteImgFront = response.data.sprites.front_default;
      this.spriteImgBack = response.data.sprites.back_default;
      this.abilities = response.data.abilities;
      this.types = response.data.types;
      console.log(this.spriteImg);

      setInterval(() => {
        this.currentSpriteIndex++;
      }, 1500);
    });

  },

};
</script>

<style scoped>
button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border: outset;
  border-width: 2px;
  border-radius: 7px;
  border-color: #a645e2;
  transition: background-color 0.3s ease;
  background-color: #a645e2;
  color: #fff;
}

button:hover {
  background-color: #555;
  color: #fff;
}

div.buttons {
  padding: 10px;
  display: flex;
  justify-content: center;
}

div.buttons button {
  margin: 10px;
}

div.info {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr .25fr .75fr .75fr;
  grid-template-areas:
    "images images name name"
    "images images type type"
    "images images abilities abilities"
    "images images abilities abilities";
  gap: 5px;
  border: 1px solid #c8c8c8;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.184);
  padding: 10px;
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
  width: 65%;
  /* place-items: center; */
  position: absolute;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: bisque;
}

#name {
  grid-area: name;
  align-self: center;
  justify-self: center;
}


#types {
  grid-area: type;
  display: flex;
  gap: 10px;
  align-self: start;
  justify-self: center;
  border: 1px solid rgba(117, 87, 53, 0.184);
  padding: 5px 10px;
}

#images {
  grid-area: images;
  border: 2px inset bisque;
  align-self: center;
  justify-self: center;
  background-color: white;

}

img {
  /* max-width: 100%; */
  height: 200px;
}

#abilities {
  grid-area: abilities;
  align-self: start;
  justify-self: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;

}

a {
  color: #0028a0;
  text-decoration: none;
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif
}

a:hover {
  text-decoration: underline;
}
</style>