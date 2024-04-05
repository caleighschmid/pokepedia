<template>
    <div class="main">
        <h2>Welcome to</h2>
        <h1>PokéGuide</h1>


    </div>
</template>

<script>

</script>

<style scoped>
.main {
    text-align: center;
}

h2{
    padding-top: 10px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    color: #5f2917;
}

h1 {
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-size: 3em;
    text-transform: uppercase;
    letter-spacing: 4px;
    background: linear-gradient(45deg, #fff07f, #b4411b);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    /* animation: glow 2s infinite alternate; */
    animation: pulse 1s 3;
}

@keyframes pulse {
    0% {
        transform: scale(1);
    }
    50% {
        transform: scale(1.1);
    }
    100% {
        transform: scale(1);
    }
}

/* 
@keyframes glow {
    from {
        text-shadow: 0 0 10px #dbc534, 0 0 20px #dbb134, 0 0 30px #dba934;
    }

    to {
        text-shadow: 0 0 20px #bc6e1a, 0 0 30px #bc551a, 0 0 40px #bc451a;
    }
} */

</style>