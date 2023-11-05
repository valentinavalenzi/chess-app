package edu.austral.dissis.chess.validators

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.game.Game
import types.PieceType

fun movesFinder(pieceFrom: Square, game: Game): List<Movement> {
    val piece = game.board.getPieceAt(pieceFrom) ?: return emptyList()
    val validMoves = mutableListOf<Movement>()
    for ((to, toPiece) in game.board.getAllPiecesOfColor(piece.color.opposite())) {
        val move = Movement(pieceFrom, to, game.board)
        if (game.pieceRules[piece]?.validate(move, game) is ValidResult) {
            validMoves.add(move)
        }
    }
    return validMoves
}

fun getKingPosition(board: Board, color: ColorType): Square? {
    val positions = board.getAllOccupiedSquares()
    for (position in positions) {
        val piece = board.getPieceAt(position) ?: throw NoSuchElementException("No piece found")
        if (piece.type == PieceType.KING && piece.color == color) return position
    }
    return null
}
