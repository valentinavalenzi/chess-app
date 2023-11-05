package edu.austral.dissis.common.validators.obstacles

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator;
import edu.austral.dissis.common.results.Result;
import edu.austral.dissis.common.results.ValidResult

class HorizontalObstacleValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        if (movement.from.y != movement.to.y) {
            return InvalidResult("You are not moving horizontally!")
        }

        val xDirection = if (movement.from.x < movement.to.x) 1 else -1

        var currentSquare = movement.from.copy()
        currentSquare = currentSquare.copy(x = currentSquare.x + xDirection)

        while (currentSquare != movement.to) {
            if (game.board.getPieceAt(currentSquare) != null) {
                return InvalidResult("There is a piece in the way!")
            }
            currentSquare = currentSquare.copy(x = currentSquare.x + xDirection)
        }
        return ValidResult()
    }
}
