package edu.austral.dissis.chess.validators.amounts

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator
import kotlin.math.abs
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

data class AmountValidator(private val quantity: Int) : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val xDiff = movement.to.x - movement.from.x
        val yDiff = movement.to.y - movement.from.y

        return if ((abs(xDiff) == quantity && abs(yDiff) == 0) ||
                (abs(xDiff) == 0 && abs(yDiff) == quantity) ||
                (abs(xDiff) == quantity && abs(yDiff) == quantity)) ValidResult()
        else InvalidResult("It's not a valid amount of squares")
    }
}

