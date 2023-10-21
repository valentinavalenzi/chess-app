package edu.austral.dissis.chess.validators.orientation

import Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.Validator
import kotlin.math.abs
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult

class LValidator : Validator {
    override fun validate(movement: Movement): Result {
        val deltaX = abs(movement.from.x - movement.to.x)
        val deltaY = abs(movement.from.y - movement.to.y)
        return if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) ValidResult() else InvalidResult()
    }
}