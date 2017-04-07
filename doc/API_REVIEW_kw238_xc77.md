# part 1

Keping Wang kw238

1. Keping's API/design is intended to be flexible in a way that they used Event Driven System. Event Driven system leaves their program with flexible event design that each class can handle the event individually. Havign an inheritance over the event makes the API uniform and flexible to change.
2. Event API encapsulates the implementation decisions by hiding each event's detail in the event's specific method. Besides, GameLoop's API also encapsulates the detail by adding Lambda function to it. So that GameLoop's loop can go over the thing it wants flexibily by adding Lambda function.
3. Keping's backend is linked to frontend by MV structure. So backend will change the data contained in the backend database. Then in each frame, frontend will read from the model to update the view. Or send events based on the user input.
4. Keping's API will handle the exception by catching them and display the messgae in the console.
5. Because event-driven system gives the code extreme flexibility. This system is easy for adding any details.

Xingyu (Alex) Chen xc77

1. Xingyu's team designed a component based game object, consisting of components like Attacker, Mover, Health, Animation (image change) and Collider. So that different game objects could be composed by putting different kinds of components together.
2. Xingyu's team has multiple subsystems that share same APIs like `step()` and `update()`, which could be called in the main loop. This specific behavior will remain hidden for the main loop.
3. Xingyu's work is mostly based on the backend and the model. This model will be linked to the frontend view by passing itself to the view and let the view render the model.
4. There might be null pointer exceptions for certain APIs. In the development mode they would print the stack trace. In production it would be handled accordingly, and probably silently.
5. Component versus Inheritance on building game objects.

# Part 2

Keping Wang 

1. Overall design.
2. APIs for AI. That should be exactly extendible but not modifiable. Not sure how to let users programmatically define AI.
3. Camera.
4. I created an event bus with sample usage, which is clear enough.
5. Null pointers for some APIs.

Xingyu Chen

1. I'm most excited to work on the AI system part. It's really interesting to create a robust AI system. 
2. I'm most worried about the Collider System because we don't have a robust Physics Engine for now.
3. This weekend, I will finish the backend so that we can run simple games like SuperMario at least.
5. Yes, they descriptive, appropriate, and reasonably sized.
6. Not really for now.

