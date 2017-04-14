1. Sprite comes within range of a sprite on a different team.	
The gameLoop component that is checking for any in-range collisions will fire an event that signifies this use case. How it is handled will be more dependent on who the detector sprite is.
2. Sprite is a spawner and spawn time has elapsed.			
After it has verified that the time required in order to spawn a new sprite has been met, there will be a new event fired that will create a new sprite and add it to the sprite model.
3. User wants to make a sprite that explodes on its death.			
would require setting the onDeath event of the specific sprite to do what you specify. For this that would mean including an explosion when the sprite dies.
4. Sprite gets shot with a weapon from same team.			
Nothing will happen, because damage of a sprite can only be incurred by a weapon originating from a member on another team. This is maintained by giving a weapon by giving it a target, and only considering a collision to occur if the target it was given was hit.
5. Sprite gets shot with a weapon from another team.			
This would result in the decrementing of the sprites health. The weapon has a target set when it's first created, so it knows where it's supposed to travel and when to fire a collision event. This would result in the decrementing of the health of the target sprite.
6. Game is saved in editing environment			
User will get a button to save the current game in the authoring environment. Everything crucial to the current game state will be stored in the model so there will be a class that takes a model object and produces a serialized object through XStream and stored in a central file / folder with resources and stored in a standardized area (data/xml/....)
7. Game is loaded into editing environment			
User gets to choose from file system a saved xml file. Using a saved game xml file (made through XStream) we willl have a series of classes which will be delegated responsibility for building sprites and other information which make up state (GameBuilderManager, SpriteBuilderManager, SpriteBuilder). A lot of this infrastructure is already built in the process of passing data from authoring to the engine originally.
8. When hovering over a sprite, its info is displayed			
Will have to create a readOnlySpriteData class which will build a standardized format of data to be read in to create this small display on the front-end. An event will have to be called (name undecided). Data accessed probably through model.getReadOnlySprite(int id)
9. Clicking on a sprite causes fireworks to appear			
This will be a user-created event, a hierarchy of classes we are planning on implementing. This will be made in the game authoring envionrment and will be tied to the visual image of the sprite so that an event is created when clicked that creates fireworks
10. Upgrade Feature: A new weapon type is given to a sprite (i.e. arrow -> bomb) after already being created			
The easiest way to do this is to simply stream the format for a new sprite with these updated attributes through xml to the back-end like it currently is being done in the beginning. Then there will have to be an function built into the SpriteBuilder that allows for replacing the sprite. This will also result in a new event being created (named something like spriteModelEvent.Replace). We also will have to implement ID numbers in order to be able to successfully replace sprites
11. Clicking on a sprite allows its speed to be changed			
The user types in (or selects) the new speed. This change is reflected in the sprite's data representing its state. 
12. Game is loaded into engine			
A GameData object, created through the processing of the XML file representation, is processed and made into a Game. This Game class builds the game and configures the game loop
13. User starts playing a game he or she has created 			
Once the Game object is created from the game data, call game.start()
14. User types in a cheat code to make all sprites have inifinite health			
This code triggers an event that accesses the sprite's health state and makes it 1000000000000000
15. Path for the sprite to follow is defined by the user and reflected in the game engine			
The path representation in the XML file is used to creaet a Path object in the back end of the game engine, which the sprite's mover component refers to when moving the sprite 
16. The tower shoots monsters at regular intervals			
This is achieved by combining a RangeChecker(monster) attribute with a SpriteSpawner. The user specifies how the Sprite responses to the RangeChecker, and tells a bullet to be spawned during certain events
17. All monsters have died, and the level switches			
We will hard-code some conditionals to be checked within the GameLoop, including one that counts the number of monsters that have died.
18. The user wants to make a sprite that just moves back and forth.			
The user selects setPosition(getX+10),getY), selects wait(2), then selects setPosition(getX-10, getY)
19. The user creates a sprite but only has a really large image to use			
He sets the width and height variables of an ImageHolder to 50
20. The user wants to switch the image of a sprite before it dies			
The onDeathEvent calls setImage(dead) and wait(2)
21. show the available skills of a selected sprite			
render the skills in the skill pane and create the corresponding click listeners 
22. spawn sprite every 3 second			
add the trigger spawning event and pass a list of sprites, then set the interval to be 3 seconds
23. ai hits the target with lowest health			
ai on action, add filter to the model to get the lowest health sprite, then hit it
24. right click to move and hit enemy			
if target is enemy, move and hit by chaining the move and hit event
25. range consistent damage			
create a sprite as the skill issuer, inflict damage to sprites in range every 1 second
26. User wants change the level while playing the game			
stop the current game and jump to the leve choose page
27. user wants to see some thing clearly			
zoom the camera around the selected location
28. user wants to register			
register in the registration page with a user name and a password
29. user forgets the password			
pop up the security question
30. user wants create a game 			
using script or dragging components onto the canvas and changing the attributes
31. User wants to assign different paths to different sprites			
When a path is made, it will be added to a drop down box. Then the user should be able to manually select a "spawner" sprite (this is an attribute that the sprite will contain) and a popup box will occur allowing the user to choose its path from a list of created, viable paths.
32. User has run out of money to place tower sprites on the screen			
Each sprite that has a cost attribute will cost the user money to place on the screen. Each time one of these sprites is placed, it will decrement the number of gold the user starts off with. Once this number is 0 (or another purchase would make it negative) the GUI should no longer allow the user to place those sprites.
33. User is trying to place two non-tile sprites in the same area			
GUI should pop up an error saying this is not allowed. Will have to check the coordinate grid in ScreenMap to see if this space is already occupied by another non-tile sprite. If not, then place away. If so, then pop-up the error message.
34. User would like to remove a sprite from the screen that he/she has already placed.			
Allow the user to click on a coordinate in the grid in ScreenMap. This will pop up a pane that shows all current sprites held within that space and provide an option to remove any of them. This would just correspond to deleting it from the observable list that holds all current screen objects.
35. User is trying to start a game without having made any paths for which to associate with the monsters.			
Disallow the user to save that game and pop up a message saying they must choose at least one path. Then, once the path is chosen, automatically associate with all sprites of type monster through a for loop that checks for this condition. Then, the user can save this game set up.
36. The user loses the current level.			
The Central level manager in the game engine is registered for the level end events and when that event is triggered it will emit a series of events to switch to the next level.
37. The user clicks one sprite and asks it to move to another point.			
The AI is able to implement the auto path finding algorithm and let the sprite find a path and move to the point.
38. The user clicks the sprite and shows its skill box.			
The input manager emits the click event and the clicked sprite is identified from the canvas. A showSkillEvent will be emitted to display all skills of that sprite on the view,
39. A sprite is attacked by enemies and dies.			
The game engine emits an attackSpriteEvent to the event bus, and the game player receives this event and knows which sprite is attacked from the attackSpriteEvent, and emits an updateSpriteViewEvent to the event bus, and the corrsponding sprite receives this event and emits a sendSpriteAttributesEvent to the event bus, and finally the game player can get the udpated sprite attributes (eg, health) from the event, and when the health is below than 0, the sprite emits a DeathEvent to the event bus and the sprite is dead and removed from the model.
40. The user needs to choose which level of the game he wants to play.			
The game player has a window with levels of the game, and the user can pick one from them.