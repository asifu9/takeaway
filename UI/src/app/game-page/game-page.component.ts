import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { GameService } from "../service/game.service";
import { WebsocketService } from "../service/websocket.service";
import { Audit } from "../domain/audit.domain";

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {
  public gameSelections: Array<any> = new Array<any>();
  userName: string;

  constructor(private route: ActivatedRoute, private gameService: GameService, test: WebsocketService) {
    let userName = this.route.snapshot.paramMap.get('userName');
    let gameId = this.route.snapshot.paramMap.get('gameId');
    let gameInstanceId = this.route.snapshot.paramMap.get('gameInstanceId');
    if (this.gameService.currentActiveGame == null) {
      if (this.gameService.games == null || this.gameService.games.length == 0) {
        this.gameService.getAllGames().subscribe(result => {
          this.gameService.games = result;
          this.updateCurrentGame(gameId)
        })
      } else {
        this.updateCurrentGame(gameId)
      }
    }
    if (!this.gameService.user) {
      this.gameService.getUserDetails(userName).subscribe(result => {
        this.gameService.user = result;
      });
    }
    if (gameInstanceId) {
      this.gameService.isGameStarted = true;
      this.getExistingData(gameInstanceId);
    }
  }

  getImage(playerId) {
    if (playerId) {
      return this.gameService.players.get(playerId).profileImagePath;
    }
    return "";

  }
  getCalculatedString(audit) {
    return `[(${audit.calculatedNumber} + ${audit.number} ) /3 ] = ${audit.result}`;
  }

  getExistingData(gameInstanceId) {
    this.gameService.getExistingGameData(gameInstanceId).subscribe(result => {
      this.gameService.audits = result;
      if (result[result.length - 1] && result[result.length - 1].gameStatus == 'END') {
        if (this.gameService.user.id == result[result.length - 1].currentPlayerId) {
          this.gameService.status = 'won';
        } else {
          this.gameService.status = 'lost';
        }
      }
    })
  }
  updateCurrentGame(gameId) {
    this.gameService.games.forEach(g => {
      if (g.id == gameId) {
        this.gameService.currentActiveGame = g;
      }
    })
  }
  startGame() {
    this.gameService.audits = [];
    this.gameService.currentData = null;
    this.gameService.status = '';
    this.gameService.update = "Waiting for opponent player";
    this.gameService.startGame().subscribe(result => {

      this.gameService.isGameStarted = true;
    });
  }
  ngOnInit() {
  }

  userAction(input) {
    //user/input
    if (this.gameService.currentData) {
      this.gameService.currentData.number = this.gameService.currentData.result;
      let resul = (this.gameService.currentData.number + input);
      console.log(resul);
      if ((resul % 3) !== 0) {
        return;
      } else {
        this.gameService.currentData.calculatedNumber = input;
        this.gameService.updateGame("/number/endpoint/user/input", this.gameService.currentData);
      }
    } else {
      console.log("game service current data is empty");
    }
  }

  selectedInputClass() {

  }

}
