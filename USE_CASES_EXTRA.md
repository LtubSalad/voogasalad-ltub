1. Sprite comes within range of a sprite on a different team.	
The gameLoop component that is checking for any in-range collisions will fire an event that signifies this use case. How it is handled will be more dependent on who the detector sprite is.
2. Sprite is a spawner and spawn time has elapsed.			
After it has verified that the time required in order to spawn a new sprite has been met, there will be a new event fired that will create a new sprite and add it to the sprite model.