#Part 1
##Question 1 
	Our design is intended to be flexible in that it can support many different types of actions. Anything on the board is essentially represented in the back end as a Sprite object, and sprite objects contain specific actor objects that define their behavior. By using this design, we make the actual actions of the sprite really flexible and therefore make the types of gameplay that can be supported flexible as well. 
##Question 2 
	The back end of the game engine is designed to give client classes only the access they need. For example, the Model sits on top of the SpriteModel to encapsulate all the sprites, so that other parts of our program cannot directly modify the game during gameplay. 
##Question 3 
	Our part forms the back end of the game engine. It should house everything that is on the game board and everything that is represented on the screen is meant to have a back end representation. 
##Question 4 
	One error that could occur in our part is when a user tries to move a sprite that cannot be moved, perhaps a grass tile. We would handle that by throwing an exception that informs the user that the tile cannot be moved. 
##Question 5
	I think our API is designed to be flexible but also to encapsulate pieces of the program that should be encapsulated. I also think it is extendable to other genres of games at the most basic level, although it may need to be revised if we try to do so. 
	

	