package edu.austral.dissis.chess.validators.obstacles

import Movement
import Piece
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.Validator
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult

class VerticalObstacleValidator(private val pieceAt: (Square) -> Piece?) : Validator {
    override fun validate(movement: Movement): Result {
        val (start, end) = if (movement.from.y > movement.to.y) {
            movement.to.y + 1 to movement.from.y
        } else {
            movement.from.y + 1 to movement.to.y
        }
        for (i in start until end) {
            val squareToCheck = movement.from.copy(y = i)
            if (pieceAt(squareToCheck) != null) {
                return InvalidResult()
            }
        }
        return ValidResult()
    }
}