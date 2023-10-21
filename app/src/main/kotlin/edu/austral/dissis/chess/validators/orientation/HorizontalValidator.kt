package edu.austral.dissis.chess.validators.orientation

import Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.Validator
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult

class HorizontalValidator : Validator {
    override fun validate(movement: Movement): Result {
        return if (movement.from.y == movement.to.y) ValidResult() else InvalidResult()
    }
}