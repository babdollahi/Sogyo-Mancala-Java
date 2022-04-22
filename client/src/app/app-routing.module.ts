import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { Player } from './domain/player';
import { MancalaComponent } from './mancala/mancala.component';
import { PlayComponent } from './play/play.component';

const routes: Routes = [
  { path: 'about', component: AboutComponent },
  { path: 'mancala', component: MancalaComponent },
  { path: 'play', component: PlayComponent, data: { player1: Player, player2: Player } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
