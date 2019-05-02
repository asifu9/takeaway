import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { User } from "../domain/user.domain";
import { Game } from "../domain/game.domain";
import { WebsocketService } from "./websocket.service";
import { Audit } from "../domain/audit.domain";
import { Observable } from "rxjs/Rx";

@Injectable()
export class GameService {

  isGameStarted = false;
  currentActiveGame: Game;
  user: User;
  update: string;
  games: Game[] = [];
  audits: Audit[] = [];
  currentData: Audit;
  players = new Map<number, any>();
  status: string;
  constructor(private httpService: HttpService) {
    this.getAllPlayers();

  }

  public getUserDetails(userName: string) {
    return this.httpService.getDataFromCoreServer("player/username/" + userName);
  }

  public getAllPlayers() {
    return this.httpService.getDataFromCoreServer("player/").subscribe(result => {
      if (result) {
        result.forEach(element => {
          if (!this.players.has(element.id)) {
            this.players.set(element.id, element);
          }
        });
      }
    });
  }

  public getAllGames() {
    return this.httpService.getDataFromCoreServer("game/");
  }
  public getExistingGameData(gameInstanceId) {
    return this.httpService.getDataFromCoreServer("/number/game/audit/gameinstanceid/" + gameInstanceId);
  }



  public startGame() {
    console.log(this.currentActiveGame.name);
    if (this.currentActiveGame.name == "NumberGame") {
      let object: any = {};
      object.game = this.currentActiveGame;
      object.status = "NEW";

      return this.httpService.postDataAbsoluteURL(
        `http://${this.user.ipAddress}:${this.user.port}/client/game/number/`,
        object);
    }
  }

  public updateGame(url: string, object: Audit) {
    let uu = `http://${this.user.ipAddress}:${this.user.port}/client${url}`;
    this.httpService.postDataAbsoluteURL(uu, object).subscribe(result => {
    });
  }

  public processInput(data) {
    if (data) {
      this.update = "";
      this.currentData = data;
      this.audits.push(data);
      if (data.gameStatus == 'END') {
        if (this.user.id == data.currentPlayerId) {
          this.status = 'won';
        } else {
          this.status = 'lost';
        }
      } else {
        this.status = '';
      }
    }
  }

}
