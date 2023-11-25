package edu.austral.dissis.checkers.validators

import edu.austral.dissis.checkers.utils.calculateMiddleSquare
import edu.austral.dissis.checkers.utils.findValidCheckersMoves
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class MustCaptureValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        var possibleMoves = emptyList<Movement>()
        val sameTeamPieces = game.board.getAllPiecesOfColor(game.board.getPieceAt(movement.from)?.color ?: return InvalidResult("No piece found"))
        for ((square, piece) in sameTeamPieces) {
            possibleMoves = findValidCheckersMoves(square, game)
        }

        val captureMoves = possibleMoves.filter { move ->
            val middleSquare = calculateMiddleSquare(move)
            val targetPiece = game.board.getPieceAt(move.to)
            targetPiece == null && game.board.getPieceAt(middleSquare)?.color == game.turn.opposite()
        }

        return if (captureMoves.isNotEmpty()) {
            // If there are capture moves available, restrict non-capture movements
            if (captureMoves.any { it.from == movement.from && it.to == movement.to }) {
                ValidResult()
            } else {
                InvalidResult("You must make a capture move")
            }
        } else {
            // No capture moves available, allow any valid move
            ValidResult()
        }
    }

}