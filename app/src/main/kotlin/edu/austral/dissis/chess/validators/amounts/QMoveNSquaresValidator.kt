package edu.austral.dissis.chess.validators.amounts

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator
import types.PieceType
import kotlin.math.abs

class QMoveNSquaresValidator (val quantity: Int, val n: Int, val pieceType: PieceType) : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val actualPiece = movement.board.getPieceAt(movement.from) ?: throw NoSuchElementException("No piece found")
        return if (actualPiece.type == pieceType && actualPiece.movementQuantity == quantity) {
            if (movement.from.x == movement.to.x && (abs(movement.from.y - movement.to.y) == n)) ValidResult()
            else if (movement.from.y == movement.to.y && abs(movement.from.x - movement.to.x) == n) ValidResult()
            else InvalidResult("Invalid quantity with amount of movements!")
        } else InvalidResult("Invalid quantity with amount of movements!")
    }
}