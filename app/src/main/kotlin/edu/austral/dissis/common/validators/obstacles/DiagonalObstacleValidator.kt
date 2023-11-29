package edu.austral.dissis.common.validators.obstacles

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class DiagonalObstacleValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {

        val xDirection = if (movement.from.x < movement.to.x) 1 else -1
        val yDirection = if (movement.from.y < movement.to.y) 1 else -1

        var currentSquare = movement.from.copy()
        currentSquare = currentSquare.copy(x = currentSquare.x + xDirection, y = currentSquare.y + yDirection)
        while (currentSquare != movement.to) {
            if (movement.board.getPieceAt(currentSquare) != null) {
                return InvalidResult("There's an obstacle diagonally")
            }
            currentSquare = Square(currentSquare.x + xDirection, currentSquare.y + yDirection)
        }

        return ValidResult()
    }
}
