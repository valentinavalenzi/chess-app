package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator
import types.PieceType

class NotEatingKingValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = movement.board
        val piece = board.getPieceAt(movement.to)
        return if (piece?.type != PieceType.KING) ValidResult() else InvalidResult("You are not allowed to eat the king!")
    }
}