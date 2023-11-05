package edu.austral.dissis.common.validators.orientation

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator
import kotlin.math.abs
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

class LValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val deltaX = abs(movement.from.x - movement.to.x)
        val deltaY = abs(movement.from.y - movement.to.y)
        return if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) ValidResult() else InvalidResult("Is not L movement")
    }
}