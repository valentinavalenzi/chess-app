package edu.austral.dissis.common.validators.orientation

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

class VerticalValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (movement.from.x == movement.to.x) ValidResult() else InvalidResult("It's not a vertical movement!")
    }
}