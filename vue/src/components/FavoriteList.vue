<template>
    <div>
        <h1>Favorites List:</h1>
        <ul>
            <li v-for="favorite in favoriteArray" :key="favorite.apiId">
                <router-link :to="{
                name: 'detail',
                params: {
                    id: favorite.apiId,
                },
            }">
                    <div>
                        {{ favorite.apiId }}
                    </div>
                    <div>
                        {{ toSentenceCase(favorite.name) }}
                    </div>
                    <div>
                        <img :src="favorite.sprites.front_default" alt="" />
                    </div>
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script>
import AuthService from '../services/AuthService';

export default {
    data() {
        return {
            favoriteArray: [],
        }
    },
    created() {
        AuthService.getAllFavorites()
            .then((response) => {
                console.log(response.data)
                this.favoriteArray = response.data;
            })
    },
    methods: {
        toSentenceCase(word) {
            const sentenceCaseWord = word.charAt(0).toUpperCase() + word.slice(1);
            return sentenceCaseWord;
        }
    }
}

</script>

<style scoped>

h1{
    text-align: center;
    padding-top: 10px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    color: #5f2917;
}

/* Apply basic styles to the unordered list */
ul {
    list-style: none;
    padding: 0;
    display: flex;
    flex-wrap: wrap;
    padding-left: 80px;
}

/* Style each list item as a card */
li a {
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

li div img {
    max-width: 100%;
    /* Ensure images within cards don't exceed the card width */
}

li div {
    margin-bottom: 10px;
}

li a:hover{
    background-color: rgb(195, 167, 235);
}

</style>