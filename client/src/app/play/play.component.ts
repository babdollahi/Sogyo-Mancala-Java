import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Player } from '../domain/player';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit {

  player1: Player;
  player2: Player;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();

    if (navigation && navigation.extras && navigation.extras.state && navigation.extras.state) {
      this.player1 = navigation.extras.state['player1'];
      this.player2 = navigation.extras.state['player2'];
    } else {
      throw 'PlayComponent was invoked without valid players!';
    }
  }

  ngOnInit(): void {
  }
}
