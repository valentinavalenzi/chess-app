package edu.austral.dissis.common.validators.moves

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class NullDestinationValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = movement.board
        return if (board.getPieceAt(movement.to) == null) ValidResult() else InvalidResult("You are eating someone!")
    }
}