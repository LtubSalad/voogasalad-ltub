mrt28 and bt72 API design

#Part 1
####1. 
The Sprite class is definitely intended to be flexible through its use of composition. The fact that the attributes are made to be modeled on the strategy design pattern means that it will be easy to extend new behavior and customize the sprite's behavior. The interface between the front-end and back-end is also set up to be super extensible for building custom games as we have set forward the basic framework for scripting. The event bus will be important for leaving extension open as it will mean that there are no bulky controller classes. By making sprites the general form for everything on the screen, it means that we are open to creating anything. Then having the engines handle the data separately means that they are not permanently intertwined with the sprites themselves and would allow us to implement different strategies later one.

####2. 
Sprites are completely encapsulated to the user. Additionally the model is completely encapsulated because there are a select number of getter methods which hide how everything is implemented. Additionally we hope to encapsulate the phsyics and collisions engines behind the game because it will essentially be a black box that will be responsible for updating the game without the user having to worry about it, it is already a provided, set interface. We worked hard to make sure that as much as possible was closed.

####3. 
The front-end will pass in a format (probably XML) which describes the sprites and the sprites attributes which thus will define the board and the initial configuration for the game. Then the game player and the view of our game is told to update based on the processes that occur in the back-end of the game. The user of observers will in theory provide quick interactions between the back-end of the game engine and the visual field that will be used to update the game in the player. The engine which I work on is basically the brain of the game and is responsible for telling the other entities how state changes over time.

####4.
An exception that might occur is that someone might try to change an attribute to an attribute that doesn't exist. For this, I would throw a attributeNotFoundException which will alert the user of this and make them pick another attribute or build their own. I believe that most of the error handling will be dealt with on the front-end and the authoring environment because they will make sure that the game is passed to us in a way that is set, error-free and properly formatted for us to load in. 

####5.
I think a good API is one that is clean, concise and easy to use and therefore easy to use to extend. I believe a great part of our API is the ability to change the composition of the sprites dynamically and throughout the game. Another great part is that everything is stored centrally in the model where all the necessary information is and there is a clean API for accessing that information for anything that would need to. I also believe that event bus is a good thing for the API as it will allow for easier communication between pieces. 

#Part 2
####1.
I am most excited to work on handling scripting in the back-end. I really am interested in the challenge of being able to completely dynamically customize sprites as I feel that is the number one tool of a game developer system. 

####2.
I am most worried about working on handling complicated path-finding problems. Specifically, I feel that going into RTS games the path-finding problem becomes very complex and so I feel that might be both time-consuming and frustrating. It is also is exciting, don't get me wrong. I just am worried about implementing because I feel that is pretty central to having extensible functionality of this game developer platform. 

####3. 
I hope to completely finish the sprites in terms of communicating with the event bus and with being able to create them based on information passed in from the front-end. This is obviously an essential part of the project. The sprites themselves are close to being finished created, but that next connection is an important part to do.

####4.
The use-cases are descriptive, appropriate, and reasonably-sized. I feel that they cover a pretty broad set of goals  and they seem descriptive enough so that it prescribes the needed implementation. The use-cases are also pretty specific so they definitely are specific enough that we are not just providing shallow use-cases.

####5.
There are several use cases for this one. For instance, I created one for when a user tries to buy something when it does not have the necessary funds/materials/whatever. We probably could have done a better job at this overall though. It would be nice to establish specific behavior when the authoring environment gets bad input for a specific attribute. Overall, I think we did a good job though. 


