package edu.austral.dissis.chess.validators.orientation

import Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.Validator
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult

class VerticalValidator : Validator {
    override fun validate(movement: Movement): Result {
        return if (movement.from.x == movement.to.x) ValidResult() else InvalidResult()
    }
}