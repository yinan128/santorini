# Contract: moveWorker

## Operation:
moveWorker(worker: int, direction: Direction)
## Cross References:
Use case: The user attempts to move a worker
## Preconditions:
- The worker selected exists.
- The destination field is on the gameboard.
- The destination field is not occupied.
- The destination field is at most one level higher than the current field.
## Postconditions:
- gameOver if the worker reached a 3-block-high field (attribute modification).
- the previous field was cleared from worker (attribute modification).
- the currently reached field was occupied by worker (attribute modification).
- Location of the worker was updated (attribute modification).
