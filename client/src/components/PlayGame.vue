<template>
    <div>
        <div class="header">
            <p>{{ gameState.players[0].name }} vs. {{ gameState.players[1].name }}</p>
        </div>

        <div class="board">
            To do...
        </div>
    </div>
</template>

<script>
export default {
  name: 'PlayGame',

  props: {
      gameState: {
          type: Object,
          required: true,
      }
  },

  methods: {
    play() {
        
    }
  }
}
</script>

<style scoped>
    .header {
        font-weight: bold;
    }

    .board {
        color: darkslategray;
    }
</style>