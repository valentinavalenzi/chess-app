package edu.austral.dissis.common.validators.game

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class IsEatingNoOneValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        return if (board.getPieceAt(movement.to) == null) ValidResult() else InvalidResult("You are eating someone!")
    }
}