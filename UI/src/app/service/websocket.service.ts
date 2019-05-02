import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { GameService } from "./game.service";


@Injectable()
export class WebsocketService {
  private stompClient;
  ws: any;
  name: string;
  object: any;
  constructor(private gameService: GameService) {
    if (!this.gameService.user) {
      //it may take a while to load data, hence we need to wait
      setTimeout(() => {
        this.initiate();
      }, 3000)
    } else {
      this.initiate();
    }




  }

  initiate() {
    let url = `ws://${this.gameService.user.ipAddress}:${this.gameService.user.port}/client/wsocket`
    let socket = new WebSocket(url);
    this.ws = Stomp.over(socket);
    let that = this;
    this.ws.connect({}, function (frame) {
      that.ws.subscribe("/errors", function (message) {
        alert("Error " + message.body);
      });
      that.ws.subscribe("/game.sendMessage", function (message) {
        that.gameService.processInput(JSON.parse(message.body));
      });

    }, function (error) {
      alert("STOMP error " + error);
    });
  }



}