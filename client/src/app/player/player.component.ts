import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Player } from '../domain/player';

@Component({
  selector: 'player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  @Input()
  name: string;

  @Output()
  playerCreated = new EventEmitter<Player>();

  constructor() {
    this.name = '';
  }

  ngOnInit(): void {
  }

  createPlayer() {
    if (this.name) {
      this.playerCreated.emit(new Player(this.name));
    }
    else {
      this.playerCreated.emit(undefined);
    }
  }
}
