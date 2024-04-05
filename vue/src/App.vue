<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'pokemon' }">List all Pokemon</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'abilities' }">List all Abilities</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'favorite' }">List Favorite Pokemon</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>


#nav {
  display: flex;
  background-color: rgb(186, 246, 186);
  margin: 0px;
  padding: 10px;
  justify-content: center;
  color: black;
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

#nav a {
  color: black;
  text-decoration: none;
}

#nav a:hover {
  text-decoration: underline;
}

h1 {
  color: #5f2917;
}

p {
  color: #5f2917;
}

span {
  color: #5f2917;
}

label{
  color: #5f2917;
}
</style>
