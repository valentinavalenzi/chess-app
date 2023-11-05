package edu.austral.dissis.chess.validators.enemies

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class IsEatingEnemyValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        return if (board.getPieceAt(movement.to) != null &&
            board.getPieceAt(movement.to)?.color != board.getPieceAt(movement.from)?.color) ValidResult()
        else InvalidResult("You can't eat your own pieces!")
    }
}