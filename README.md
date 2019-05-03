# Takeaway Challenge

Enviroment:
1. Java 1.8 or more
2. Maven
3. MySQL - any latest version
4. Node server v11.10.0, NPM 6.7.0 to run Angular 5.2 Application.

PLease follow bellow steps to run the application.
TO run the server
1. Create a database with the name "test" in mysql database.
2. go to server folder from downloaded files and run below maven command to compile and build the the jar.
   > mvn clean install
3. Once jar builded, run below command and make sure you see server is running on 8080 port.
 >  java -jar target/server-0.0.1-SNAPSHOT.jar
 
 
 Note: while starting server, it will initialize default table value for two tables, 1 entry in game table and 2 entry in players table.
 
Once server is up, lets run the client applications.
As we know, its a two player game, we need to run the application twice with the different user and port.

1. Go to client folder and compile the code to build jar using command "mvn clean install"
2. once the jar is build run bellow command to start player 1 application.
  >   java -jar target/client-0.0.1-SNAPSHOT.jar --server.port=2020 mwell

  in Above command we are passing two parameter, 
    a) Server port: make sure the passed server port should match will the player database server port.
    b) Player user name: this assumed the current loged in user 

3. Again open one more terminal and run second player instance.
   > java -jar target/client-0.0.1-SNAPSHOT.jar --server.port=3030 bLara
    
Yup, we are done with client application, now we need to run the UI whihc will be conected to these client application.

1. Go to downloaded UI folder and run command below command.
     > npm install
2. Once npm installs all the necessary files, run below command to star the UI.
     > ng serve --port=4200
     
     Same command you run in another terminal with differnt port like "ng server --port=4300"
     
 Till now we have started all the services which is necessary to run the application.
 
 Go to any browser and put the below url. this URL saying, user (mwell - username field) is going to play game (game id =1 ).
 
 http://localhost:4200/game/mwell/1
 
 Open another browser and put use below URL.
 
  http://localhost:4300/game/bLara/1
  
  you should see a in both browser option to start the game.
  

  To add a new Player : <br>
  URL: http://localhost:8080/core/player/ <br>
  Method: POST <br>
  Payload:  <br>
      
      {
        "firstName": "john",
        "lastName": "Cena",
        "userName": "jcena",
        "profileImagePath": "/images/user1.png",
        "ipAddress": "localhost",
        "port": 2020
    }
    
  To add a new Game: <br>
  URL: http://localhost:8080/core/game/ <br>
  Method: POST <br>
  Payload: <br>
  
       {
        "name": "Another Game",
        "gameType": "NUMBER"
        }
