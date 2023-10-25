package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator

class IsInsideBoardValidator : Validator {
    override fun validate(movement: Movement): Result {
        val board = movement.board
        return if (movement.from.x >= 0 && movement.from.x < board.rowAmount
            && movement.from.y >= 0 && movement.from.y < board.columnAmount
            && movement.to.x >= 0 && movement.to.x < board.rowAmount
            && movement.to.y >= 0 && movement.to.y < board.columnAmount
        ) ValidResult() else InvalidResult()
    }
}