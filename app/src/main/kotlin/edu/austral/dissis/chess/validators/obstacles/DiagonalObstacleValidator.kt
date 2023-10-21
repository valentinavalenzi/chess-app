package edu.austral.dissis.chess.validators.obstacles

import Movement
import Piece
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator
import kotlin.math.abs

class DiagonalObstacleValidator(private val pieceAt: (Square) -> Piece?) : Validator {
    override fun validate(movement: Movement): Result {
        // Checks for diagonal movement
        if (abs(movement.from.y - movement.to.y) != abs(movement.from.x - movement.to.x)) {
            return InvalidResult()
        }

        val xDirection = if (movement.from.x < movement.to.x) 1 else -1
        val yDirection = if (movement.from.y < movement.to.y) 1 else -1

        var currentSquare = movement.from.copy()
        currentSquare = currentSquare.copy(x = currentSquare.x + xDirection, y = currentSquare.y + yDirection)
        while (currentSquare != movement.to) {
            if (pieceAt(currentSquare) != null) {
                return InvalidResult()
            }
            currentSquare = Square(currentSquare.x + xDirection, currentSquare.y + yDirection)
        }

        return ValidResult()
    }
}
