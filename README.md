# voogasalad
---------------------
Game Authoring Engine Project
-----------------------
In this project, we constructed a game authoring environment for Tower Defense game genre. Meanwhile, this project also considered the extension to Real Time Strategy genre game.  In total, the project consists the following parts:

 - Game Authoring Environment
 - Game Engine
 - Game Player
 - Game Data Files

The game authoring environment provides users the ability to create a game, which will be stored as an XML document for later game play. Here is an image of the game environment: 

![This is cool, too bad you can't see it](environment.png "the modules")

The game engine is the main logic of the game design. We use eventbus to handle different types of events. We call the pieces to create a game sprite. Different sprites have different components, which will describe the properties of the sprite. 

The game data is a classes of data to store the information of sprites, like heath, golds, etc.

The game player part is the part to show the game environment, he logging of the game, etc. ere is an image of our game player:

![This is cool, too bad you can't see it](player.png "the modules")