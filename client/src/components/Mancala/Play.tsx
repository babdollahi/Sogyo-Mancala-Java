import React from "react";
import { GameState } from "../../types/gameState";
import "./Play.css";

type PlayProps = {
  gameState: GameState;
  setGameState(newGameState: GameState): void;
};

export function Play({ gameState, setGameState }: PlayProps) {
  
  async function handlePitClick(index: number ) {

    try {
      const response = await fetch("mancala/api/play", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          index: index
        }),
      });

    if (response.ok) {
      const gameState = await response.json();
      setGameState(gameState);
    } else {
      console.error(response.statusText);
    }
    } catch (error) {
      console.error(error);
    }
      
  }

  return (
    <>
      <p>
        {gameState.players[0].name} vs {gameState.players[1].name}
        <p>Status of Game: {gameState.gameStatus.endOfGame ? "Game ended!" : "Continuing!"}</p>
        {gameState.gameStatus.endOfGame && <p>The winner is: {gameState.gameStatus.winner}</p>}
        {gameState.players[0].hasTurn ? <p>{gameState.players[0].name} please select a bowl to play!</p> : <p>{gameState.players[1].name} please select a bowl to play!</p>}

      </p>
      <p>Player two: {gameState.players[1].name}</p>
      <button className="button button1" onClick={() => handlePitClick(12)}> {gameState.players[1].pits[5].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(11)}>{gameState.players[1].pits[4].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(10)}>{gameState.players[1].pits[3].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(9)}>{gameState.players[1].pits[2].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(8)}>{gameState.players[1].pits[1].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(7)}>{gameState.players[1].pits[0].nrOfStones} </button>
      <button className="button button3" onClick={() => handlePitClick(13)}>{gameState.players[1].pits[6].nrOfStones} </button>
      <button className="button button2" onClick={() => handlePitClick(6)}>{gameState.players[0].pits[6].nrOfStones} </button>

      <p></p>
      <button className="button button1" onClick={() => handlePitClick(0)}>{gameState.players[0].pits[0].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(1)}>{gameState.players[0].pits[1].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(2)}>{gameState.players[0].pits[2].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(3)}>{gameState.players[0].pits[3].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(4)}>{gameState.players[0].pits[4].nrOfStones} </button>
      <button className="button button1" onClick={() => handlePitClick(5)}>{gameState.players[0].pits[5].nrOfStones} </button>
      <p>Player one: {gameState.players[0].name}</p>

 
    </>
  );
}
