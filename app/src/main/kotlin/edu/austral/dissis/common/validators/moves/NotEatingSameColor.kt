package edu.austral.dissis.common.validators.moves

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class NotEatingSameColor : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        val pieceFrom = board.getPieceAt(movement.from)
        val pieceTo = board.getPieceAt(movement.to)
        return if (pieceFrom?.color != pieceTo?.color) ValidResult() else InvalidResult("You can't eat your own pieces!")
    }
}