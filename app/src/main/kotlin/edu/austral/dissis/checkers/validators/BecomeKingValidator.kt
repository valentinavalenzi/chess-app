package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.Validator

class BecomeKingValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        val pieceFrom = board.getPieceAt(movement.from)
        val pieceColor = pieceFrom?.color
        if (pieceColor == ColorType.BLACK && movement.to.y == 8) return ValidResult()
        return if (pieceColor == ColorType.WHITE && movement.to.y == 1) ValidResult()
        else InvalidResult("You can't become king!")
    }
}