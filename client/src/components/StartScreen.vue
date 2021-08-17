<template>
    <div>
        <input
            placeholder="Player 1 name"
            v-model=playerOne />

        <input
            placeholder="Player 2 name"
            v-model=playerTwo />

        <p>{{ errorMessage }}</p>

        <button v-on:click=confirmPlayers>Start</button>
    </div>
</template>

<script>
export default {
  name: 'StartScreen',

  data() {
      return {
        playerOne: undefined,
        playerTwo: undefined,
        errorMessage: "",
    }
  },

  methods: {
    async confirmPlayers() {
        if (!this.playerOne) {
            this.errorMessage = "Player 1 name is required";
            return;
        }

        if (!this.playerTwo) {
            this.errorMessage = "Player 2 name is required";
            return;
        }

        this.errorMessage = "";

        try {
            const response = await fetch('api/start', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nameplayer1: this.playerOne, nameplayer2: this.playerTwo })
            });

            if (response.ok) {
                const gameState = await response.json();
                this.$emit('game-state-changed', gameState);
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }
    }
  }
}
</script>

<style scoped>
    p {
        color: red;
    }
</style>