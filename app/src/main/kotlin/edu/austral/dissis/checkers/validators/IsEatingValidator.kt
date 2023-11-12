package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.Validator

class IsEatingValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        val fromPiece = board.getPieceAt(movement.from)
        val toPiece = board.getPieceAt(movement.to)
        if (fromPiece == null) {
            return InvalidResult("No piece found at the source position")
        }
        if (toPiece != null) {
            return InvalidResult("The destination position is already occupied")
        }
        // diagonal move, already checked in the DiagonalValidator
        val dx = movement.to.x - movement.from.x
        val dy = movement.to.y - movement.from.y
        val opponentColor = if (fromPiece.color == ColorType.WHITE) ColorType.BLACK else ColorType.WHITE
        val intermediateX = movement.from.x + dx / 2
        val intermediateY = movement.from.y + dy / 2
        val intermediatePosition = Square(intermediateX, intermediateY)
        val intermediatePiece = board.getPieceAt(intermediatePosition)
        return if (intermediatePiece != null && intermediatePiece.color == opponentColor) ValidResult()
        else InvalidResult("There should be an opponent's piece in the middle.")
    }

}