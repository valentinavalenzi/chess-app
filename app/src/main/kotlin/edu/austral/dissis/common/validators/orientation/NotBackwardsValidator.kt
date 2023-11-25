package edu.austral.dissis.common.validators.orientation

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class NotBackwardsValidator () : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val color = game.board.getPieceAt(movement.from)?.color ?: return InvalidResult("No piece found at the source position")
        return if (color == ColorType.BLACK && movesDown(movement)) ValidResult()
        else if (color == ColorType.WHITE && movesUp(movement)) ValidResult()
        else InvalidResult("You are trying to move backwards!")
    }

    private fun movesUp(movement: Movement) = movement.from.y > movement.to.y

    private fun movesDown(movement: Movement) = movement.from.y < movement.to.y
}