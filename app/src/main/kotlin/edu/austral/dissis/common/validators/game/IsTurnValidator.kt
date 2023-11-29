package edu.austral.dissis.common.validators.game

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class IsTurnValidator: Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val piece = movement.board.getPieceAt(movement.from) ?: return InvalidResult("No piece found")
        return if (piece.color == game.turn) ValidResult()
        else InvalidResult("It's not your turn")
    }
}