package edu.austral.dissis.common.validators.moves

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.Validator
import types.PieceType

class PawnCanPromoteValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        val pieceFrom = board.getPieceAt(movement.to)
        val pieceColor = pieceFrom?.color
        if (pieceFrom?.type == PieceType.PAWN && pieceColor == ColorType.BLACK && movement.to.y == 8) return ValidResult()
        return if (pieceFrom?.type == PieceType.PAWN && pieceColor == ColorType.WHITE && movement.to.y == 1) ValidResult()
        else InvalidResult("You can't become king!")
    }
}