package edu.austral.dissis.chess.validators.orientation

import Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator
import kotlin.math.abs

class DiagonalValidator : Validator {
    override fun validate(movement: Movement): Result {
        return if (abs(movement.to.x - movement.from.x) == abs(movement.to.y - movement.from.y)) ValidResult()
        else InvalidResult()
    }

}