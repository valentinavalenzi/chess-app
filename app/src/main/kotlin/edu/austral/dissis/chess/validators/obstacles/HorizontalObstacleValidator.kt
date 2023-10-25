package edu.austral.dissis.chess.validators.obstacles

import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.Validator;
import edu.austral.dissis.chess.results.Result;
import edu.austral.dissis.chess.results.ValidResult

class HorizontalObstacleValidator : Validator {
    override fun validate(movement: Movement): Result {
        if (movement.from.y != movement.to.y) {
            return InvalidResult()
        }

        val xDirection = if (movement.from.x < movement.to.x) 1 else -1

        var currentSquare = movement.from.copy()
        currentSquare = currentSquare.copy(x = currentSquare.x + xDirection)

        while (currentSquare != movement.to) {
            if (movement.board.getPieceAt(currentSquare) != null) {
                return InvalidResult()
            }
            currentSquare = currentSquare.copy(x = currentSquare.x + xDirection)
        }
        return ValidResult()
    }
}
