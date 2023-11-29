package edu.austral.dissis.common.validators.orientation

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

class HorizontalValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (movement.from.y == movement.to.y) ValidResult() else InvalidResult("You aren't moving horizontally!")
    }
}