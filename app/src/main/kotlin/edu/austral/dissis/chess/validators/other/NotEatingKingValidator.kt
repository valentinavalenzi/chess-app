package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator
import types.PieceType

class NotEatingKingValidator : Validator {
    override fun validate(movement: Movement): Result {
        val board = movement.board
        val piece = board.getPieceAt(movement.to)
        return if (piece?.type != PieceType.KING) ValidResult() else InvalidResult()
    }

}