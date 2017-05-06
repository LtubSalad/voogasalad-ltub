# voogasalad
---------------------
Game Authoring Engine Project
-----------------------
In this project, we constructed a game authoring environment for Tower Defense game genre. Meanwhile, this project also considered the extension to Real Time Strategy genre game.  In total, the project consists the following parts:

 - Game Authoring Environment
 - Game Engine
 - Game Player
 -  Game Data Files

The game authoring environment provides users the ability to create a game, which will be stored as an XML document for later game play. Here is an image of the game environment: 

![This is cool, too bad you can't see it](enviroment.jpeg "the modules")

The game engine is the main logic of the game design. We use eventbus to handle different types of events. We call the pieces to create a game sprite. Different sprites have different components, which will describe the properties of the sprite. 

The game data is a classes of data to store the information of sprites, like heath, golds, etc.

The game player part is the part to show the game environment, he logging of the game, etc. ere is an image of our game player:

![This is cool, too bad you can't see it](player.jpeg "the modules")

* names of all people who worked on ths project:  Jake Conroy, Tahia Emran, Yilin Gao, Alison Huang, Daniel Li, Matt Tribby, Keping Wang, Zhiyong Zhao
*  We started the project on March 23, and finished the project on May 4
* Role of each person: 
       *  Jake Conroy: Game authoring environment
       * Tahia Emran: game model
       * Yilin Gao: player and engine
       * Alison Huang: Sprite, Components
       * Daniel Li: Game authoring environment
       * Matt Triby: Sprite, component
       * Keping wang: Game Engine and event bus
       * Zhiyong Zhao: Game player
* Files to start the project : App.java in the player package
* files for testing: There are tester files for some java class
* Data or resources: the major resources are some images and sound. There are also some properties files and CSS files
* There are some shortcut for the game: theuser can cnontrol the camera by the mouse.
*  In the future, we can add more compoents and make the appearance of the game more charming. We may need to rewite some codes for performance.