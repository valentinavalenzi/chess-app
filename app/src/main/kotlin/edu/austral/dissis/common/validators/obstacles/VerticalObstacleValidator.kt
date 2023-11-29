package edu.austral.dissis.common.validators.obstacles

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

class VerticalObstacleValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val (start, end) = calculateStartAndEnd(movement)

        for (i in start until end) {
            val squareToCheck = movement.from.copy(y = i)
            if (isSquareOccupied(game.board, squareToCheck)) {
                return InvalidResult("There is a piece in the way!")
            }
        }
        return ValidResult()
    }

    private fun calculateStartAndEnd(movement: Movement): Pair<Int, Int> {
        return if (movement.from.y > movement.to.y) {
            movement.to.y + 1 to movement.from.y
        } else {
            movement.from.y + 1 to movement.to.y
        }
    }

    private fun isSquareOccupied(board: Board, square: Square): Boolean {
        return board.getPieceAt(square) != null
    }
}
