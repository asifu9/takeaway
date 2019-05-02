import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { GamePageComponent } from './game-page/game-page.component';
import { GameService } from "./service/game.service";
import { HttpService } from "./service/http.service";
import { HttpClientModule } from '@angular/common/http';
import { GamesComponent } from './games/games.component'; 
import { WebsocketService } from "./service/websocket.service";

const appRoutes = [
  {path : 'game/:userName', component : GamesComponent},
  {path : 'game/:userName/:gameId', component : GamePageComponent},
  {path : 'game/:userName/:gameId/:gameInstanceId', component : GamePageComponent},
  {path: '',
    redirectTo: '/games/:userName',
    pathMatch: 'full'}

]

@NgModule({
  declarations: [
    AppComponent,
    GamePageComponent,
    GamesComponent
    
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    HttpClientModule
  ],
   
  providers: [GameService,HttpService,WebsocketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
