
## API Review

### Part 1

1. What about your API/design is intended to be flexible?

The View is independent of the Model, they are both controlled by the Controller. If you want to add a new sprite a game, first the controller adds a sprite to the model, and then the controller asks the view to render the information. Frontend doesn't keep track of the Model. 

The Observer pattern also makes the design flexible. Game Data is being observed by both frontend and backend. 

2. How is your API/design encapsulating your implementation decisions?

The API encapsulates our implementation by reducing the amount of information the client needs to know.

3. How is your part linked to other parts of the project?

Game Engine is connected to the Game Authoring Environment through XML files.

Game Objects is connected to the System, which does the calculation. It also connects to the Game Authoring Environment. For example, when the user creates a new game object.

4. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

A exception in the Sound system on different operating systems. 

5. Why do you think your API/design is good (also define what your measure of good is)?

Good is flexible, encapsulates implementation, minimize dependencies. 

Our API, observer pattern, MVC model all makes the design good.


### Part 2
1. What feature/design problem are you most excited to work on?

The most exciting part should be how to render the view as natural as possible, like those in the commercial games. How to deal with user Inputs and translate them into events.

Also the exciting part is how to design a flexible backend so the user will be able to create a variety of types of games.

2. What feature/design problem are you most worried about working on?

How to make the frontend look good, instead of simply functioning. 

TO make the backend flexible.

3. What major feature do you plan to implement this weekend?

Create the user interface.

Make the game running.

4. Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?

Yes. We think so.

5. Do you have use cases for errors that might occur?

We don't think so. No errors.