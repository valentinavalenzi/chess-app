package edu.austral.dissis.chess.validators.amounts

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator
import kotlin.math.abs

class QMoveNSquaresValidator (val quantity: Int, val n: Int, val piece: Piece) : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (piece.movementQuantity == quantity) {
            if (movement.from.x == movement.to.x && (abs(movement.from.y - movement.to.y) == n)) ValidResult()
            else if (movement.from.y == movement.to.y && abs(movement.from.x - movement.to.x) == n) ValidResult()
            else InvalidResult("Invalid quantity with amount of movements!")
        } else InvalidResult("Invalid quantity with amount of movements!")
    }
}