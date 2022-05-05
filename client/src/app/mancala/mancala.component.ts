import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Player } from '../domain/player';

@Component({
  selector: 'mancala',
  templateUrl: './mancala.component.html',
  styleUrls: ['./mancala.component.css']
})
export class MancalaComponent implements OnInit {

  player1: Player | undefined;
  player2: Player | undefined;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  async start(): Promise<void> {
    try {
      const players = { player1: this.player1, player2: this.player2 };
      const respose = await fetch('http://localhost:8080/start', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(players)
      });

      if (respose.ok) {
        this.router.navigateByUrl('/play', { state: players });
      } else {
        console.error(respose.statusText);
      }
    }
    catch (error) {
      console.error(error);
    }
  }
}
