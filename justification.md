#Justification
<hr>
As is shown in the object model diagram, Board is of the highest hierarchy in this game.
It communicates with Player class and Field class.
<br>

The Board class stores the game status including round, all the players, current player, and the game board (as a map).
<br>

When the actual player(s) starts the game, 
s/he needs to pass parameters according to the instructions prompted in the console. 
The Board class will take care of the user input. There are 3 outcomes for the input:
* game proceeds -> switch round, player; update game board.
* game over -> end game and announce the winner.
* invalid user input -> ask user to pass parameters again until they are valid.

According to the game rules, it seems that a field can hold zero or one worker but this
relationship is not applied in object model diagram to decouple those two classes from different branches.
So the responsibility is passed to the Board class to identify if a field is occupied by a worker.
<br>

Some enums are created for the "building components" and "directions" so that it will be easier 
to check invalid parameters at compile time.


##FAQ
<hr>

####1. Who checks whether a move/build is valid?
The Field class checks a valid move since it stores the variables __hasWorker__ indicating whether 
it holds a worker and __hasDome__ indicating if it is domed. Furthermore, it includes a static 
method to compare the building height of two fields to suggest if next move is feasible.
The Tower class check a valid build action since it stores the current __level__ of a tower.


####2. Who performs state updates for move/build?
After a valid move, 
The moved worker will update its __location__.
The previous field and current field will update __hasWorker__.
The board may update __gameOver__ if the recent move reaches the top of a 3-block-high tower.
<br>
After a successful build action, 
The field may update __hasDome__ if a dome is built.
The tower will update its __level__ and __construction__.

####3. Who checks whether the game is over and who the winner is?
The Board class does. After a successful move, the board will check if the destination field has a 3-block-high
tower. If it does, then game is over and the __currentPlayer__ is the winner.

####4. Is unnecessary coupling avoided?
Field class and Worker class are decoupled although they seem to be tightly connected 
in the game rules.

####5. Is unnecessary complexity avoided?
Block and Dome are included in an enum named Component. So are the 8 directions in an enum named Direction.
They should be constants and do not need to be instantiated every time when they are needed.


