import axios from 'axios';

const http = axios.create({
    baseURL: 'https://pokeapi.co/api/v2/pokemon/'
});

const httpAbility = axios.create({
    baseURL: 'https://pokeapi.co/api/v2/ability/'
})

export default {
    getAllPokemon() {
        return http.get();
    },
    getPokemonDetail(id) {
        return http.get(`${id}`);
    },
    getMorePokemon(count){
        return http.get(`?offset=${count}&limit=20`);
    },
    getAllAbilities() {
        return httpAbility.get();
    },
    getAbilityDetail(id) {
        return httpAbility.get(`${id}`);
    },
    getMoreAbilities(count) {
        return httpAbility.get(`?offset=${count}&limit=20`);
    }
}