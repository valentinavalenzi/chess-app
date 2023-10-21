package edu.austral.dissis.chess.validators.amounts

import Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.Validator
import kotlin.math.abs
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult

data class AmountValidator(private val quantity: Int) : Validator {
    override fun validate(movement: Movement): Result {
        val xDiff = movement.to.x - movement.from.x
        val yDiff = movement.to.y - movement.from.y

        return if ((abs(xDiff) == quantity && abs(yDiff) == 0) ||
                (abs(xDiff) == 0 && abs(yDiff) == quantity) ||
                (abs(xDiff) == quantity && abs(yDiff) == quantity)) ValidResult()
        else InvalidResult()
    }
}

