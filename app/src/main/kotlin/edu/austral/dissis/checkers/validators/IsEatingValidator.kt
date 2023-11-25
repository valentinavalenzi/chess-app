package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.Validator

class IsEatingValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        val fromPiece = game.board.getPieceAt(movement.from)
        val toPiece = game.board.getPieceAt(movement.to)

        if (fromPiece == null || toPiece != null) {
            return InvalidResult("No piece available or there is already a piece in the target square.")
        }

        val opponentColor = getOpponentColor(fromPiece.color)
        val intermediatePosition = calculateIntermediatePosition(movement)
        val intermediatePiece = game.board.getPieceAt(intermediatePosition)

        return if (intermediatePiece != null && intermediatePiece.color == opponentColor) ValidResult()
        else InvalidResult("There should be an opponent's piece in the middle in order to capture.")
    }

    private fun getOpponentColor(color: ColorType): ColorType {
        return if (color == ColorType.WHITE) ColorType.BLACK else ColorType.WHITE
    }

    private fun calculateIntermediatePosition(movement: Movement): Square {
        val dx = (movement.to.x + movement.from.x) / 2
        val dy = (movement.to.y + movement.from.y) / 2
        return Square(dx, dy)
    }
}
