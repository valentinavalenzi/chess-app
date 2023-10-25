package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator

class NotEatingSameColor : Validator {
    override fun validate(movement: Movement): Result {
        val board = movement.board
        val pieceFrom = board.getPieceAt(movement.from)
        val pieceTo = board.getPieceAt(movement.to)
        return if (pieceFrom?.color != pieceTo?.color) ValidResult() else InvalidResult()
    }
}