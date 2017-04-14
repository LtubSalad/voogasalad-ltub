# Design

To represent your design at a reasonably high level and provide an organization to the plan document, break it into modules rather than all the classes you can think of. A module is a concept in your program that may be represented by a single class or a group of classes related in some standard way, such as set of subclasses, that has a purpose with regards to the program's functionality and collaborates with other modules.
	
Game Engine:
* Map
* Tile
* Screen - the different pages for the view 
* Sprite 
* Model
* View 
* Event Bus
* User Input
* Game Loop

Game Data: 
* Files (GIFs, XML, MP3s) 

Game Authoring Environment:
* Map Design
* Avatar Design
* Scripting
* Screen Design - general screen layout
* Model Design - initial user-defined data (lives you start off with, time limit, how many monsters we start on the screen) 
* View  
* XML Writer (shared component)

Game Player (Runner):
* Game Loader

Utility - Resource Loader:
* XML Reader 
* XML Writer 
* Heads Up Display Utility - points, lives → make it super extendable




Some of these modules will simply be used by other modules, but some will be more complex   frameworks. A framework means that your code is in control and users of your design must fit into the extension, or plug in, points you provide. Thus it is important that you describe these extension points in English and provide examples of their use in addition to listing the classes and methods you expect to implement.

The game authoring framework that users can use will be a combination of the avatar designer and model designer. See the next question for a discussion of how this will be implemented. Another framework that our code will provide is the event bus. This class will have a few public methods like on(ActionEvent) and off(ActionEvent) which will take a generic event and propagate it to all parts of our program that could possibly handle that event. For example, consider the case in which we have written a bullet class that can fire its own event on every update of the screen. It will send this event to the bus and the bus can place this event in the model so the position can be updated and then the view will listen to these changes and update the screen. Imagine this is all the bullet can do. Now, we can add a new type of bullet with new events like collideEvent that are also put on the bus when triggered. Perhaps this event will be written such that it not only changes the model to update the bullet’s position, but also the firing of the collideEvent will trigger a sound from the SoundSystem. Maybe it will also inflict damage on the object with which it collided. This will be left up to the class. The point is it still uses the bus framework we have provided but the new class will customize the events that it can fire. 


One primary framework you will be creating is an architecture to support building games, i.e., a series of events, different states, all possible collisions, levels of rules, smart actors, plans, etc. This architecture might be based on something that simplifies your design (yet is very flexible), a distinguishing feature of the game genre, the user interface you want to present, etc. You will need to explain how to use this structure to actually design a game and how you will present this structure to the game designer through your authoring environment.

Complex object interactions
Classes which are supposed to perform actions, such as towers which shoot monsters, or monsters which move down a line, should all implement the actor interface. This means they have an act() method which is called regularly by the game loop, and they are instantiated with access to a portion of the Model- a data structure that keeps track of the data that is specific to each playthrough of a game. They are also given access to the EventBus, which is a class that has access to the model and can modify the various components of the game. In such a fashion, in each game loop, the run() methods of Actors are called. Actors can then need to call the Model to receive the data they need, then pass events to the EventBus so that the Actors can modify other components of the games. 
If the game designer wishes to define intelligent actions for objects he creates, he may use IntelligentActors, which are instantiated with a given lambda that is based off the game designer’s projects.

The user will be presented with an object creator, a model programmer, and a screen editor. The object creator will hold a selection of images and options that the object can “implement.” The game engine part of our team will allow general sprite objects to use composition to contain certain attributes like moveable, selectable, and attacker/attackable. Beyond this, when certain selections like moveable are selected, other pertinent options will be displayed like “walk” or “fly.” Another example would be that perhaps the user selects an attacker and then would have to choose its range, firing rate, and splash damage. In this way, custom objects will be able to be created by the user for selection in a screen editor.
Now that the model and objects have been set, a screen editor will put it together so that the user can choose from all objects to place on the screen. Additionally, some preset backgrounds will be available so that the user can choose maps that we as the game creators think will work well, but the user will also have some tiles to choose from (or that could have been created in the object creator) to create their own custom map. Tiles, for example, will be things like grass, dirt, water, and stone.

In everything, focus on how to build a variety of games in your genre. Explain the key features in your game genre, how you will support those features, and how you expect new games to be written (i.e., what needs to be sub-classed, what accepts lambda functions, what goes into resource files, and how communication will occur between your separate programs).

The key features of a tower defense game include: attackers, towers, a path, placement, number of lives, winning conditions, and losing conditions. We will support attackers and towers are part of a composition of different features that can be added to sprites as described in the previous question. Things like path, placement, and number of lives will be stored in the model and can be changed via features in the model programmer described above. Finally winning and losing conditions will have defaults but will also be able to accept lambda functions to change what will cause a level to be completed or a gamer to lose the game. An entire game “situation” will be able to be saved into resource files so things like current position and type of objects will be saved into XML and will be able to be read back into the model for viewing. Communication between separate programs will occur either through events fired through the event bus or via observable objects. For instance, the image of a sprite on the map while the game is running will be observing the model to change position and other attributes like health, but when it is in the range of something that it can attack, an event will be propagated through the event bus so that a sound is fired, a collision is created, and perhaps a new image is formed to show something explode.