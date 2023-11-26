package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator
import kotlin.math.absoluteValue

class CastlingValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val piece = game.board.getPieceAt(movement.from)
        if (movement.to.x - movement.from.x == 2) {
            // short castling
            val rook = game.board.getPieceAt(Square((movement.from.x + 3), movement.from.y))
            if (piece?.hasMoved() == false && rook?.hasMoved() == false) {
                return ValidResult()
            }
        } else if (movement.to.x - movement.from.x == -2) {
            // long castling
            val rook = game.board.getPieceAt(Square((movement.from.x - 4), movement.from.y))
            if (piece?.hasMoved() == false && rook?.hasMoved() == false) {
                return ValidResult()
            }
        }
        return InvalidResult("You can't castle!")
    }
}