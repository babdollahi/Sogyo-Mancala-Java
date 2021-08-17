<template>
  <PlayGame v-if=isGameStarted
            v-on:game-state-changed=updateGameState
            v-bind:gameState=gameState
  />

  <StartScreen v-else
               v-on:game-state-changed=updateGameState
  />
</template>

<script>
import StartScreen from './components/StartScreen.vue';
import PlayGame from './components/PlayGame.vue';

export default {
  name: 'App',

  data() {
    return {
      gameState: undefined,
    }
  },

  computed: {
    isGameStarted() {
      return this.gameState !== undefined;
    }
  },

  methods: {
    updateGameState(newGameState) {
      this.gameState = newGameState
    }
  },

  components: {
    StartScreen,
    PlayGame
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
