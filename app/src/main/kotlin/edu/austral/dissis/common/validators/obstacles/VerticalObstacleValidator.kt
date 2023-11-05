package edu.austral.dissis.common.validators.obstacles

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

class VerticalObstacleValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val (start, end) = if (movement.from.y > movement.to.y) {
            movement.to.y + 1 to movement.from.y
        } else {
            movement.from.y + 1 to movement.to.y
        }
        for (i in start until end) {
            val squareToCheck = movement.from.copy(y = i)
            if (game.board.getPieceAt(squareToCheck) != null) {
                return InvalidResult("There is a piece in the way!")
            }
        }
        return ValidResult()
    }
}