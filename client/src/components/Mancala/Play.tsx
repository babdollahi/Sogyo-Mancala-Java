import { GameState } from "../../types/gameState";
import "./Play.css";

type PlayProps = {
  gameState: GameState;
  setGameState(newGameState: GameState): void;
};

export function Play({ gameState, setGameState }: PlayProps) {
  return (
    <>
      <p>
        {gameState.players[0].name} vs {gameState.players[1].name}
      </p>
      <p>Player two</p>
      <button className="button button1">{gameState.players[0].pits[0].nrOfStones} </button>
      <button className="button button1">{gameState.players[0].pits[1].nrOfStones} </button>
      <button className="button button1">{gameState.players[0].pits[2].nrOfStones} </button>
      <button className="button button1">{gameState.players[0].pits[3].nrOfStones} </button>
      <button className="button button1">{gameState.players[0].pits[4].nrOfStones} </button>
      <button className="button button1">{gameState.players[0].pits[5].nrOfStones} </button>
      <button className="button button2">{gameState.players[0].pits[6].nrOfStones} </button>
      <button className="button button3">{gameState.players[1].pits[6].nrOfStones} </button>

      <p></p>
      <button className="button button1">{gameState.players[1].pits[0].nrOfStones} </button>
      <button className="button button1">{gameState.players[1].pits[1].nrOfStones} </button>
      <button className="button button1">{gameState.players[1].pits[2].nrOfStones} </button>
      <button className="button button1">{gameState.players[1].pits[3].nrOfStones} </button>
      <button className="button button1">{gameState.players[1].pits[4].nrOfStones} </button>
      <button className="button button1">{gameState.players[1].pits[5].nrOfStones} </button>
      <p>Player one</p>
      
    </>
  );
}
