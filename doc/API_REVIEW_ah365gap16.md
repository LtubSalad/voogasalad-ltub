Part 1
1. Composition! Allows the sprite to be dynamically changed.
2. Our primary interface for all of the attributes only has one (maybe two) public method that will be called and triggers actions the express the qualities that the atrribute should hold.
3. Sprites are called on every frame of the game loop to be updated and then displayed on the front end.
4. Possible errors would be having two sprites that share the same ID, which would throw an error indicating that the ID is already taken.
5. The API is good because it is extendable for later possible games to be made and because it limits the amount of information that is moving the project. Most of the functions and info are stored in a centralized model.

Part 2
1. Making more advanced functionality for the sprites, as well as new functionality if a new game were to be made.
2. The Event Bus is the most worrisome for me. This is because it's a new programming style that I am not personally familiar with but was suggested by another teammate.
3. This weekend I'm going to implement the moveAttribute.
4. I just created an issue for the implementing of the moveAttribute that I'm going to do this weekend. The description isn't that specific but gives my teammates enough information to know what I'll be doing.
5. I have a use case that checks to make sure the ID of the new sprite that I want to make isn't already taken.