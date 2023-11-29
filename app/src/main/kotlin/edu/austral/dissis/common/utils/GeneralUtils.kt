package edu.austral.dissis.common.utils

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.Game
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

fun availableMovesFinder(pieceFrom: Square, game: Game): List<Movement> {
    val piece = game.board.getPieceAt(pieceFrom) ?: return emptyList()
    val validMoves = mutableListOf<Movement>()
    for (toSquare in game.board.getAllSquares()) {
        val move = Movement(pieceFrom, toSquare, game.board)
        if (game.pieceRules[piece]?.validate(move, game) is ValidResult) {
            validMoves.add(move)
        }
    }
    return validMoves
}

fun movesToFinder(to: Square, game: Game): List<Movement> {
    val piece = game.board.getPieceAt(to) ?: return emptyList()
    val validMoves = mutableListOf<Movement>()
    for ((from, fromPiece) in game.board.getAllPiecesOfColor(piece.color.opposite())) {
        val move = Movement(from, to, game.board)
        if (game.pieceRules[fromPiece]?.validate(move, game) is ValidResult) {
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
