# Design

## Design Update
### Changing sprite design

Initially, we designed sprites to contain refrences to "Attribute" classes. These Attributes were designed to store data concerning the functionalities of each Sprite, and specified how the game engine should handle various different sprites. Functionality, how ever, was largely delegated to various "Manager" classes, instead of within the Attributes themselves.

Midway through the project, we changed the design of the Sprite and its relationship to its various attribute so that each sprite stored an internal eventbus, and video game designers can have some degree of control over a sprite's response to events. This works as follows:

Aside from storing private variables and preset functions they can perform, a sprite's subattributes(we used "components" to describe them in the meeting) should now also specify what kind of (preset) events the Sprite wants to listen to. For example, a Collidable attribute allows the Sprite holding it to handle a "Collision" event, which is fired whenever the CollisionManager figures out that a Collidable has bumped into another Collidable. 
(All sprites, regardless of how few attributes they have, can handle "instantiation" and "death" events, which would be fired when a sprite is added/removed.)
Depending on which attributes(components) a Sprite possesses, it can listen for a variety of events, and the video game designer can set actions to perform on these events dynamically. Other examples of events Sprites should dynamically respond to include a constantly fired event that corresponds to the constant "update" function and the "SpriteInRangeEvent" for the RangeChecker attribute.

The idea of this is that this makes it easy for the video game designer to define more complex functionalities using basic features, and requiring less hard code. (i.e. instead of having the Tower implement a preset, tower-specific Attacker attribute, we can ask the Tower to check for monsters in range with a RangeChecker, then fire a bullet with SpriteSpawner if it finds something and it's been more than 3 seconds since the last shot.)

### Passing Data
The classes that Sprites use to define various features of their functionality- which were initially described as "Attributes", and are now refered to as "Components", are no longer converted to "Data" classes before being saved to XML files. Instead, since we find that Components were already suitable for conversion through XStream, we decided to simply instantiate actual components from the developer side and then convert their XStream-equivalent files for use by the game engine.

The data passed to the game engine will consist of an DeveloperData class that tracks LevelData, SpriteMakerModels, and GeneralGameData.

## Utility: Image Processor
This allows the game to take images and trim out the transparent regions.
	
Game Engine:
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
* Path Design
* Sprite Design
* Spawner Design
* Model Design - initial user-defined data (lives you start off with, time limit, how many monsters we start on the screen) 
* XML Writer (shared component)

Game Player (Runner):
* Game Loader

Utility - Resource Loader:
* XML Reader 
* XML Writer 
* Heads Up Display Utility - points, lives → make it super extendable

Some of these modules will simply be used by other modules, but some will be more complex

The game authoring framework that users can use will be a combination of the sprite designer, path designer, and spawner designer. Another framework that our code will provide is the event bus. This class will have a few public methods like on(ActionEvent), off(ActionEvent), and emit(EventType, EventHandler) which will take a generic event and propagate it to all parts of our program that could possibly handle that event. For example, consider the case in which we have written a bullet class that can fire its own event on every update of the screen. It will send this event to the bus and the bus can place this event in the model so the position can be updated and then the view will listen to these changes and update the screen. Imagine this is all the bullet can do. Now, we can add a new type of bullet with new events like collideEvent that are also put on the bus when triggered. Perhaps this event will be written such that it not only changes the model to update the bullet’s position, but also the firing of the collideEvent will trigger a sound from the SoundSystem. Maybe it will also inflict damage on the object with which it collided. This will be left up to the class. The point is it still uses the bus framework we have provided but the new class will customize the events that it can fire. 

One primary framework you will be creating is an architecture to support building games, i.e., a series of events, different states, all possible collisions, levels of rules, sprites with components and skills, plans, etc. This architecture might be based on something that simplifies your design (yet is very flexible), a distinguishing feature of the game genre, the user interface you want to present, etc. You will need to explain how to use this structure to actually design a game and how you will present this structure to the game designer through your authoring environment.

Complex object interactions
Classes which are supposed to perform actions, such as towers which shoot monsters, or monsters which move down a line, should all implement the actor interface. This means they have an act() method which is called regularly by the game loop, and they are instantiated with access to a portion of the Model- a data structure that keeps track of the data that is specific to each playthrough of a game. They are also given access to the EventBus, which is a class that has access to the model and can modify the various components of the game. In such a fashion, in each game loop, the run() methods of Actors are called. Actors can then need to call the Model to receive the data they need, then pass events to the EventBus so that the Actors can modify other components of the games. 
If the game designer wishes to define intelligent actions for objects he creates, he may use IntelligentActors, which are instantiated with a given lambda that is based off the game designer’s projects.

The user will be presented with an object creator, a model programmer, and a screen editor. The object creator will hold a selection of images and options that the object can “implement.” The game engine part of our team will allow general sprite objects to use composition to contain certain attributes like moveable, selectable, and attacker/attackable. Beyond this, when certain selections like moveable are selected, other pertinent options will be displayed like “walk” or “fly.” Another example would be that perhaps the user selects an attacker and then would have to choose its range, firing rate, and splash damage. In this way, custom objects will be able to be created by the user for selection in a screen editor.
Now that the model and objects have been set, a screen editor will put it together so that the user can choose from all objects to place on the screen. Additionally, some preset backgrounds will be available so that the user can choose maps that we as the game creators think will work well, but the user will also have some tiles to choose from (or that could have been created in the object creator) to create their own custom map. Tiles, for example, will be things like grass, dirt, water, and stone.

In everything, focus on how to build a variety of games in your genre. Explain the key features in your game genre, how you will support those features, and how you expect new games to be written (i.e., what needs to be sub-classed, what accepts lambda functions, what goes into resource files, and how communication will occur between your separate programs).

The key features of a tower defense game include: attackers, towers, a path, placement, number of lives, winning conditions, and losing conditions. We will support attackers and towers are part of a composition of different features that can be added to sprites as described in the previous question. Things like path, placement, and number of lives will be stored in the model and can be changed via features in the model programmer described above. Finally winning and losing conditions will have defaults but will also be able to accept lambda functions to change what will cause a level to be completed or a gamer to lose the game. An entire game “situation” will be able to be saved into resource files so things like current position and type of objects will be saved into XML and will be able to be read back into the model for viewing. Communication between separate programs will occur either through events fired through the event bus or via observable objects. For instance, the image of a sprite on the map while the game is running will be observing the model to change position and other attributes like health, but when it is in the range of something that it can attack, an event will be propagated through the event bus so that a sound is fired, a collision is created, and perhaps a new image is formed to show something explode.


********************************************************************************

# DESIGN for analysis

# provides the high-level design goals of your project

 * Describe the problem your team is trying to solve:

The goal of this project is to create a platform that supports the creation of a Tower Defense game, and allows a user to set-up the specifications of his/her own game and then run this game. This means that we must try to create an environment which allows the user to build a wide variety of games with a wide variety of specifications such as winning conditions and losing conditions, and different components with which to give their sprites within the game. The goal is to empower the game designer by creating a framework which is both easy to use and works for many different kinds of games, perhaps even different genres. In order to do this, we are building different hierarchies for objects that share some qualities but leave flexibility and specifications up to the developer, allowing for dynamic changing of game units’ behaviors, and limiting dependencies between components of our project with a central place for behaviors to be handled and executed. 

 * Describe the primary design goals of the project (where is it most flexible):

The primary design aspect of our project is to have one centralized place where all of the changes that occur throughout the duration of the game is handled. This is handled by the event bus which is the core of our event-based system. We compare this event bus with the dependency injection pattern, where instead of having every object in the project depend on something else to execute its functionality, it passes it off to the event bus to take care of. The good thing about using events is to reduce dependencies between all of the components and the fact that it eliminates the need for a bulky controller class. We will also be using composition, a method of dynamically changing the components and skills of the sprites in our game. These interchangeable components will extend a superclass, allowing for different implementations of components which will thus allow for maximum flexibility and extensibility for the sprites.
In terms of game data, the process through which a Game in the engine is created from an XML file saved from the authoring environment uses the idea of a Builder pattern, in which classes are delegated to put create different parts of a game (ie. the Sprites) and then a fully functional game object is produced. 

 * Describe the primary architecture of the design (what is closed and what is open):

Ideally, we wanted the project to remain as encapsulated as possible, especially between the game authoring and game playing environments. To do this, we have kept certain parts of our program invisible to the user and to other components of the program. The most accessible aspect would be the event bus, since almost every corner of the program will need to trigger an event or be altered by an event handler. We are going to keep the data of the entire program stored in one central location. These things include the data of all the sprites, the number and configuration of levels, the winning and losing conditions, and general data such as amount of starting money and number of lives. This will be held by the model and the subcomponents of the model, in the class SerializableDeveloperData which can be given to the game data/translation code to turn into the actual game elements that the game engine, and thus the event bus, can act upon. Once the actual game elements are loaded into the model, which is a hierarchical system composed of SpriteModel, PlayerRelationModel, PlayerStatsModel, and SelectionModel, they are given access to the EventBus, which is a class that can modify the various components of the game through the event type emitted into the bus and the handler associated with such an event. In such a fashion, any part of the game engine can theoretically affect another part of the game engine which provides us with incredible flexibility to extend the functionality of our program. For one example, when a monster sprite (a sprite that has the path follower component) reaches the end of its path, it can fire off an event which is handled in the PlayerStatsModel to reduce the number of lives by 1.

 * Describe your chosen game genre and what qualities make it unique that your design will support:

We chose the tower defense genre of games. This genre has a map with different paths chosen by the user for spawners to spawn monsters onto, and also has different sprites like towers that have different abilities according to the user’s desires. Some will move and some won’t, some can attack while others can’t, etc. The goal is to defend your territory by placing different defensive characters around and on your territory. Thus, the user is given the power to configure the map in the beginning. This is an important feature of tower defense games. In order to allow users to upgrade some features of the map, there needs to be a currency outside of the levels that keeps track of ability to upgrade. This means that an upgrade component is available for use in any sprite and will cost the user some money to augment the numeric values of its other components like damage strength or range. 

* Discuss the design at a high level (without referencing specific classes, data structures, or code):

Our project is split up into four primary components: the Game Engine, the Game Authoring Environment, Game Data, and the Game Player. The Game Engine is the bulk of the back-end of our program. It is where the modules that encompass the actual thinking of the game are created, and where they are capable of interacting with one another. This includes the characters of the games, the map, and the data related to the games. The Game Authoring Environment is the user interface where users will have the power to configure their levels, design their characters, choose the starting parameters of the game, etc. The Game Player isthe window that allows users to look into the actual game; it’s where the animation and playing actually happens. Finally, the Game Data is where information extracted from the user in the game authoring environment is translated into classes that the game engine can act upon (like sprite, level, game, etc.)

# Explain in detail, how to add new features to your project

 * Game Engine:
 * 
Sprite: Sprites are easily extendable because they are containers for components and skills. Since all components and skills descend from a superclass, if someone wanted to create a new type, they would just create a new subclass that implements this interface for that component or skill. We employed this several times throughout the project and creating a new type of component or skill didn’t take us more than an hour each. We simply added methods that overrode the function in the superclass and the specific component or skill now had different functionality.

Event Bus: The event bus is designed such that any and all possible event triggers and handlers can be registered to it. There is a logical hierarchy of event types that one can create a subclass off of and then use the existing methods (on, off, emit) in the event bus to register either an existing handler or create a new one themselves by extending off of BusEventHandler, which we’ve provided. This system is flexible enough to allow for lambda expressions to be used in either the BusEventType or the BusEventHandler, which would make creating new triggerable conditions within gameplay very quick to create and if the user wanted to save this type of event handling for later, they could create classes out of these lambda functions. 

Managers: We use managers to handle keeping track of game data like scores, lives, collisions, user input, sounds, and timers. Should someone want to add another metric to keep track of during the game, then they could add another manager and then instantiate it in the Game class in the constructor where all of the other managers can be found, add them as a loop component to the game loop and the rest will be handled assuming you write your class in the same manner that we have written our managers.

* Game Data:

The translation process provides interfaces for any type of data object to be turned into another data object (using a new concrete implementation of the Translator interface). To this end, the process of extracting data from the authoring environment and setting it up in the Game Player/Engine is extendable for other types of data/ engine designs that may better suit other game genres. 

* Game authoring environment

The current tower game authoring environment extends GameAuthor and we actually already made a primitive real time strategy authoring environment that extends GameAuthor as well. So, if one wanted to create a new type of authoring environment from scratch, then they could extend this class to do so and save out the data collected from the user into SerializableDeveloperData like we do in the tower defense game and then the game engine could read this data into a playable game. 

Modifying the Sprite creation environment to support sprites with new components is relatively quick- the ObjectSetter class uses reflection to allow the developer to instantiate an arbitrary object from the developer side, so long as said object's parameters are of form String, primitive, Path, enum, or SpriteMakerModel. (If we wish to add in a component which takes in a different parameter type, a new VariableSetter class needs to be made to support that new type). The programmer needs to go the SpriteCreationScreen to add the component's class to the list of classes for the ComponentSelectorPane to use. The programmer needs to annotate the constructor of the component with a @ConstructorForDeveloper annotation to tell the component setter to use that constructor when generating its field setters. If we wish to specify the name of each parameter to be shown on the developer side we need to add an additional @VariableName annotation to the parameter list. If we wish to be able to load a components internal fields onto the developer interface to be viewed, we need to modify the getParameters method of the component to return an object array of items corresponding to its internal fields.

Furthermore, if one wanted to add or take away steps needed to completely author a game in our current authoring environment, then they need only “addStep” in the instantiateStep method of the TowerAuthor class. In fact, we currently have a couple of other steps that are completely functional that are commented out in this method because they didn’t link up with the DeveloperData class. So allowing the user to input more information to the game from start to finish would require writing the new GUI component, adding it as a step to the TowerAuthor class, and then ensuring that the GUI component’s information can be stored in the DeveloperData class.

* Game player

We provide the IView interface to other developers to write their own view components that could visualize our game in their own way. Our current view can be found in View.java, but we also concocted example classes like RTSView and TDView that both implement the IView interface to show how to extend our game player to handle other types of game visualization. All one needs to do it render the sprites, stats, and the “bottom pane” which is where clickable sprites and items that can be used in game play are held.

# Justify major design choices, including trade-offs (i.e., pros and cons), made in your project

* Sprites with components

The details on how to compose a sprite with various dynamic components: whether to check collision, how does it attack, what skills are available and what are their effects.

We spent a significant part of our project designing with non tower-defense games in mind, and hence tried to make our Sprite classes as flexible as possible. In particular, we designed Sprites to possess an arbitrary number of “Component” objects that contained variables describing features such as health, image, sound files, skillsets and movement speed. This allowed us to implement Sprites with a variety of different functionalities. Components were not always modular- the Collidable component, for example, relied on the bounds of the Image component to determine when collisions occured, and several components needed a reference to the Sprite holding that component to function correctly. In the case of components that were non-modular, this sometimes represented the fact that their functionality was dependent on other components. For example, with the Collidable component, if a Sprite didn’t have an images component, Collision detection wouldn’t be possible, so said component would not be able to properly function. 

The idea of Sprites being defined by their components facilitated a relatively straightforward flow of information from the Game Authoring Environment to the Game Engine, as the engine utilizes the components a sprite has to determine its actions over the course of the game. This restricts sprites to only being customizable through their components, however, almost any aspect of a sprite’s functionality can be captured within a component. 


* Instantiating objects from the developer side:

Since the Components that a Sprite needed to have could be defined by an arbitrary number of variables of arbitrary type, the people working on the developer side decided to use reflection to view the constructors of Component objects and provide options to instantiate components based on the parameters of the constructor. Constructors to be used by the developer interface were annotated with a @ConstructorForDeveloper annotation, and the parameters of the constructors were annotated by @VariableName annotations to specify what description of the parameters of the constructors should be shown on the developer interface. Depending on the type of each constructor parameter, a different gui component would be displayed on the screen. For example, if a component received a Path object as a parameter, a PathSetter GUI object would be displayed on the screen, and if a component received a parameter that was a string or any primitive type, a SimpleVariableSetter GUI object would be displayed on the screen. This made it very easy for users to specify the creation of new components from the developer side, as programmers only needed to annotate the constructors of their Component classes for the developer interface to be able to set said component.
One disadvantage of this method was that it required some degree of change to the Component classes in the form of annotation, which meant that engine items were not entirely encapsulated from the front end. Another disadvantage of this method was that it required some ugly-looking if statements to specify what kind of setter should be produced based on the parameter type. However, we believe that the code smells involved in such a method were reasonable if they allowed programmers to instantiate arbitrary new components while writing minimal new code.

We designed our code to allow video game developers to be able to load the parameters of Sprites and their Components that they had previously created back into the development interface to be modified once more. However, because we did not want to write boilerplate code involving component-specific setter objects, and the design of component setter objects, was determined at runtime by a single generic ObjectSetter class, we could not write code that knew about component-specific methods such as the Health component's getHealth method, or the PathFollower component's getPath method. To get around this, Daniel suggested that we make all components implement a getParameters method, which returned an object array of the fields within the component that corresponded to the parameters that were specified within the component's constructor, arranged in an order that matched the order of the constructor parameters. Using this, it was possible to instantiate arbitrary component setters from preexisting components, with text or image fields that already held inputs corresponded to the actual data used by the component.
One disadvantage of this method is that it is not extremely flexible- if the parameters used by the constructor annotated with ConstructorForDeveloper change, then the object array returned by getParameters must change too. Moreover, because we are dealing with object arrays, this means that typecasting must occur so that the variable setters may set their fields to match the details listed within the array.
These are both code smells. However, we could not implement a flexible generics-based design where the design of the component setters were generated at runtime without losing information about what component-specific fields were stored within each component, and how they should be obtained. The issue of flexibility is also a genuine concern, but this concern would still be present even if each component produced its own hard-coded component setter, as a GUI-based setter would still need to change if the component was changed to rely on different components. Hence, though this solution is not ideal, we believe that it is the best possible option for us to perform while maximizing the extensibility and flexibility of our code.


# State any assumptions or decisions made to simplify or resolve ambiguities in the project's functionality
One of the most important decisions that we made with regards to the design of the project was to design a class that was not in fact written to accomodate any particular form of game entity. No assumptions whatsoever were made about the functionality that a Sprite would provide, aside from the fact that it would store a collection of components which added functionalities of their own. This decision was made in part because it seemed wise to design our code to be as flexible as possible, but we also had practical motivations since there were many members of the team who wished to create games in genres aside from tower defence.

Another decision made during the late stages of the project was to remove the development interface's ability to specify how objects should placed on the screen. This was to simplify some of the challenges concerning passing data in the face of incoming deadlines. Similarly, though the design of the game engine and the development interface was such that it supported script-defined event handlers within the Sprite classes' internal buses, team members ran into bugs when trying to convert game data files if Sprites held internal script engines. Because of incoming deadlines and the apparent intractability of data conversion with scripting, the scripting functionality was also removed from the project.



