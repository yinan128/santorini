#Justification
<hr>
As is shown in the object model diagram, Game is the controller in this game.
It communicates with Player, Board, GameLogic and EventListener.
<br>



At the beginning of the game, each player can choose at most one god card (can be zero).
Then they need to place two workers per person onto the game board to their initial locations.
The Game class takes care of the user input.
There are 3 actions the player can perform after the game officially starts (workers are placed onto the initial locations), they are:
* moveWorker - move the worker from start to destination.
* build      - let the worker to build on a given location.
* skipAction - pass an optional action.

<br>

Some enums are created for the "building components", "worker action", so that it will be easier 
to check invalid parameters at compile time.


##FAQ
<hr>

####1. Who checks whether a move/build is valid?
The GameLogic class checks a valid move and build but most of the basic check responsibilities are delegated to the Board class since it stores the Field.

In some special cases, for example, when a god card is assigned to a player, 
corresponding GameLogic related to that god card will do some extra checks according to the description of the god card.


####2. Who performs state updates for move/build?
After a valid move, 
The Field class will change its state according to the last move action, mainly its __workers__.
The Worker class will update its state, mainly its __location__.
The board may update its __winner__ if the recent move has generated a winner.
The GameLogic will inform the EventListeners to update their state.

After a successful build action, 
The Field may update __hasDome__ if a dome is built.
The Tower will update its __level__ and __construction__.
The GameLogic will inform the EventListeners to update their state.

####3. Who checks whether the game is over and who the winner is?
The Board class does. After a successful move, the board will check if the destination field has a 3-block-high
tower. If it does, then game is over and the last __player__ who performed the move action is the winner.

####4. Is unnecessary complexity avoided?
Block and Dome are included in enum Component.
They should be constants and do not need to be instantiated every time when they are needed.

##Extensibility
<hr>
1) GameLogic interface is created to handle the user input such as move and build and it will give back corresponding consequences.
We can create new class implementing GameLogic interface to act as a unique game logic. 
In this game, since each god card represents a unique game logic, 
we can introduce or invent more god cards by creating new classes without changing the main content of the game.

One alternative for this is:
1) create an enum containing all the listed god cards. 
2) let the Player contain a field of such enum to identify what god card he has chosen. 
3) In Player's move and build method, use switch case to make corresponding actions.

The problem for this is: 
1) you may need to keep additional fields to record the state; 
2) your code will be tedious and full of duplication;
3) introducing new god cards requires making a lot of changes in code, although it's not the major problem.



##Design patterns
<hr>

1) Decorator pattern is used for the GameLogic interface. 
The god cards are not introducing a totally new game logic, they just change some behaviors based on the basic game logic.
In this case, it is convenient to let the god card decorate the basic game logic and only change certain part of its functionality.


2) Observer pattern is used to monitor the player actions such as move, build and skip. 
The SequenceHandler and GameLogicController used this pattern because they need event updates from the game to change its state.
For example, sequenceHandler need to decide which action is available in the next turn; GameLogicController need to know whether the god card has changed the game rule.
The observer pattern will also be necessary when GUI is implemented in this program since the view need to update every time when player makes a valid action.