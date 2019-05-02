import { Component, OnInit } from '@angular/core';
import { Game } from "../domain/game.domain";
import { ActivatedRoute, ActivatedRouteSnapshot, Router } from "@angular/router";
import { GameService } from "../service/game.service";

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {


  constructor(private route: ActivatedRoute,public router: Router,private gameService: GameService) { }

  ngOnInit() {
      let userName = this.route.snapshot.paramMap.get('userName');
      
      
    this.gameService.getUserDetails(userName).subscribe(result=> {
      this.gameService.user=result;
    });
     this.gameService.getAllGames().subscribe(result=> {
      this.gameService.games=result;
    });
  }

   startGame(game){
     this.gameService.currentActiveGame=game;
     this.router.navigate(['/game/'+this.gameService.user.userName+"/"+game.id]);
   }

}
