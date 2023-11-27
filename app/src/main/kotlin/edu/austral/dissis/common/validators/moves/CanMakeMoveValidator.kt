package edu.austral.dissis.common.validators.moves

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.validators.game.IsTurnValidator


class CanMakeMoveValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val piece = game.board.getPieceAt(movement.from) ?: return InvalidResult("No piece found")
        return if (IsTurnValidator().validate(movement, game) is ValidResult &&
                    game.pieceRules[piece]?.validate(movement, game) is ValidResult) ValidResult()
        else InvalidResult("None of the conditions are met to make the move")
    }
}