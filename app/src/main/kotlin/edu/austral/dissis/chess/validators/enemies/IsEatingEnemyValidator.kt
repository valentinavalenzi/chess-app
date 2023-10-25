package edu.austral.dissis.chess.validators.enemies

import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator

class IsEatingEnemyValidator : Validator {
    override fun validate(movement: Movement): Result {
        val board = movement.board
        return if (board.getPieceAt(movement.to) != null && board.getPieceAt(movement.to)?.color != board.getPieceAt(movement.from)?.color) ValidResult()
        else InvalidResult()
    }
}