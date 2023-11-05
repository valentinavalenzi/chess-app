package edu.austral.dissis.common.validators.game

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class IsInsideBoardValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        return if (movement.from.x > 0 && movement.from.x <= board.rowAmount
            && movement.from.y > 0 && movement.from.y <= board.columnAmount
            && movement.to.x > 0 && movement.to.x <= board.rowAmount
            && movement.to.y > 0 && movement.to.y <= board.columnAmount
        ) ValidResult() else InvalidResult("You can't move outside the board!")
    }
}