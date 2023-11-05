package edu.austral.dissis.common.validators.orientation

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator
import kotlin.math.abs

class DiagonalValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (abs(movement.to.x - movement.from.x) == abs(movement.to.y - movement.from.y)) ValidResult()
        else InvalidResult("You aren't moving diagonally!")
    }

}