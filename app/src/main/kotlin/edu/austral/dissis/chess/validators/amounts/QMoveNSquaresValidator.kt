package edu.austral.dissis.chess.validators.amounts

import edu.austral.dissis.chess.Movement
import Piece
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator
import kotlin.math.abs

class QMoveNSquaresValidator (val quantity: Int, val n: Int, val piece: Piece) : Validator {
    override fun validate(movement: Movement): Result {
        return if (piece.movementQuantity == quantity) {
            if (movement.from.x == movement.to.x && (abs(movement.from.y - movement.to.y) == n)) ValidResult()
            else if (movement.from.y == movement.to.y && abs(movement.from.x - movement.to.x) == n) ValidResult()
            else InvalidResult()
        } else InvalidResult()
    }
}