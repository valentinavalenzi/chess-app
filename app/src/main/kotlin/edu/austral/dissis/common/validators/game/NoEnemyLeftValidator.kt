package edu.austral.dissis.common.validators.game

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.results.game.GameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.validators.EndGameValidator

class NoEnemyLeftValidator : EndGameValidator {
    override fun validate(movement: Movement, game: Game): GameResult {
        val board = game.board
        val lastPieceMoved = board.getPieceAt(movement.to)
        val pieceColor = lastPieceMoved?.color
        if (pieceColor != null) {
            if (board.getAllPiecesOfColor(pieceColor.opposite()) == emptyMap<Square, Piece>()) {
                return FinishGameResult(pieceColor)
            }
        }
        return NextMoveResult(game)
    }
}