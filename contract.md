# Contract: moveWorker

## Operation:
moveWorker(player: Player, start: Location, destination: Location)

## Cross References:
Use case: The player having a Minotaur god card attempts to move a worker

## Preconditions:
- The player is eligible to perform a move action in the current turn.
- The start location is holding a worker which belongs to the player.
- The destination field is on the game board.
- The destination field does not have a dome.
- The destination field is either:
  1) unoccupied, adjacent to the start location and is no higher than the start field by one level
  2) occupied by an opponent worker which can move back to its previous location.

## Postconditions:
- The player will be the winner if the worker reached a 3-block-high field (attribute modification).
- The previous field was cleared from worker (attribute modification).
- The currently reached field was occupied by worker (attribute modification).
- Location of the worker was updated (attribute modification).
- If the worker moved onto a field which was holding an opponent worker, the opponent worker will move back to the previous field it stayed on.
