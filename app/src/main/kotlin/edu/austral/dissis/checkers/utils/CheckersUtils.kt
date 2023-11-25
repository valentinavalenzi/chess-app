package edu.austral.dissis.checkers.utils

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.game.IsInsideBoardValidator

fun calculateMiddleSquare(movement: Movement): Square {
    val dx = movement.to.x - movement.from.x
    val dy = movement.to.y - movement.from.y
    val intermediateX = movement.from.x + dx / 2
    val intermediateY = movement.from.y + dy / 2
    return Square(intermediateX, intermediateY)
}

fun canEatMore(game: Game, square: Square): Boolean {
    val piece = game.board.getPieceAt(square)

    if (piece != null) {
        val possibleMoves = findValidCheckersMoves(square, game)

        for (move in possibleMoves) {
            val middleSquare = calculateMiddleSquare(move)
            val targetPiece = game.board.getPieceAt(move.to)
            if (targetPiece == null && game.board.getPieceAt(middleSquare)?.color == game.turn.opposite()) {
                return true // Found a potential capture move
            }
        }
    }

    return false // No more capture moves found
}

fun findValidCheckersMoves(square: Square, game: Game): List<Movement> {
    val piece = game.board.getPieceAt(square) ?: return emptyList()
    val validMoves = mutableListOf<Movement>()

    val validDirections = if (piece.color == ColorType.WHITE) listOf(2, -2) else listOf(-2, 2)
    validDirections.forEach { dx ->
        findValidMovesInDirection(dx, validDirections, square, game, piece, validMoves)
    }

    return validMoves
}

fun findValidMovesInDirection(
    dx: Int,
    directions: List<Int>,
    square: Square,
    game: Game,
    piece: Piece,
    validMoves: MutableList<Movement>
) {
    directions.forEach { dy ->
        val destination = Square(square.x + dx, square.y + dy)

        if (isValidMove(piece, square, destination, game) && isInsideBoard(destination, game)) {
            validMoves.add(Movement(square, destination, game.board))
        }
    }
}

fun isValidMove(piece: Piece, from: Square, to: Square, game: Game): Boolean {
    val movement = Movement(from, to, game.board)
    return game.pieceRules[piece]?.validate(movement, game) is ValidResult
}

fun isInsideBoard(square: Square, game: Game): Boolean {
    return IsInsideBoardValidator().validate(Movement(square, square, game.board), game) is ValidResult
}

