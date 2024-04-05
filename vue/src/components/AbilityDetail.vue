<template>
    <div class="ability-info">
        <h1 id="name"> {{ toSentenceCase(name) }}</h1>
        <p id="gen"> First introduced in Generation {{ extractGen(first_gen) }}</p>
        <p id="effect"> {{ short_effect }}</p>
        <p class="list">Pokemon who can have {{ toSentenceCase(name) }}:</p>
        <p class="list" v-for="pokemon in pokemon_with_ability" :key="pokemon.pokemon.name">
            <router-link :to="{
            name: 'detail',
            params: {
                id: extractId(pokemon.pokemon.url),
            },
        }">
                {{ toSentenceCase(pokemon.pokemon.name) }}
                <span v-if="pokemon.is_hidden === true">(hidden)</span>
            </router-link>
        </p>
    </div>
</template>


<script>
import PokemonService from "../services/PokemonService.js";

export default {
    name: "ability-detail",
    props: {
        id: String,
    },
    data() {
        return {
            name: "",
            short_effect: "",
            pokemon_with_ability: {},
            first_gen: "",
        }
    },
    methods: {
        toSentenceCase(word) {
            if (typeof word !== 'string' || word.length === 0) {
                return ''; // Return an empty string or handle accordingly
            }
            const sentenceCaseWord = word.charAt(0).toUpperCase() + word.slice(1);
            return sentenceCaseWord;
        },
        extractId(url) {
            let pokemonIndex = url.indexOf('pokemon');
            let pokemonStr = url.substring(pokemonIndex);
            let slashIndex = pokemonStr.indexOf('/');
            let numberStr = pokemonStr.substring(slashIndex + 1, pokemonStr.length - 1);
            return numberStr;
        },
        extractGen(generationString) {
            let generationIndex = generationString.indexOf('generation');
            let generationStr = generationString.substring(generationIndex);
            let slashIndex = generationStr.indexOf('/');
            let numberStr = generationStr.substring(slashIndex + 1, generationStr.length - 1);
            return numberStr;
        }
    },
    created() {
        let numId = +this.id;
        PokemonService.getAbilityDetail(numId).then((response) => {
            console.log(response.data);
            this.name = response.data.name;
            this.first_gen = response.data.generation.url ;

            // Access the correct position in the effect_entries array
            const englishEntry = response.data.effect_entries.find(
                (entry) => entry.language.name === "en"
            );

            this.short_effect = englishEntry ? englishEntry.short_effect : "";
            // If short_effect is not available, use flavor text
            if (this.short_effect === "") {
                const flavorTextEntry = response.data.flavor_text_entries.find(
                    (entry) => entry.language.name === "en"
                );
                this.short_effect = flavorTextEntry ? flavorTextEntry.flavor_text : "";
            }
            this.pokemon_with_ability = response.data.pokemon;
        })
    }
}
</script>


<style scoped>

.ability-info {
  max-width: 600px; /* Adjust the max-width as needed */
  margin: 20px auto; /* Center the content horizontally */
  padding: 20px;
  border: 1px solid #c8c8c8;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.184);

  /* Text styles */
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
  font-size: 16px;
  line-height: 1.5;

  background-color: bisque;
  
}

/* Additional styles for individual paragraphs or links if needed */
.ability-info p {
  margin-bottom: 5px; /* Add some spacing between paragraphs */
}

#name{
    text-align: center;
    margin-bottom: 5px;
}

#gen {
    font-size: small;
    text-align: center;
    margin-top: 5px;

}

#effect{
    font-size: large;
    text-align: center;
}

.list{
    text-align: center;

}

a {
  color: #0028a0;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}

</style>