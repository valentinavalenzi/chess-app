package edu.austral.dissis.checkers.mover

import edu.austral.dissis.checkers.validators.BecomeKingValidator
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.mover.Mover
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.game.IsInsideBoardValidator
import types.PieceType

class CheckersMover : Mover {
    override fun move(game: Game, movement: Movement): Game {
        val middleSquare = calculateMiddleSquare(movement)
        val newBoard = game.board.move(movement).removePiece(middleSquare)
        val newGame = Game(newBoard, game.turn, game.generalValidators,
                            game.newPieceRules(newBoard, movement),
                            game.winningValidations, game.mover)
        if (hasEaten(game, newGame) && canEatMore(newGame, movement.to)) return newGame
        if (BecomeKingValidator().validate(movement, game) is ValidResult) {
            return turnPawnIntoKing(movement.to, game)
        }
        return Game(newBoard, game.switchTurn(),
            game.generalValidators,
            game.newPieceRules(newBoard, movement),
            game.winningValidations, game.mover)
    }

    private fun calculateMiddleSquare(movement: Movement): Square {
        val dx = movement.to.x - movement.from.x
        val dy = movement.to.y - movement.from.y
        val intermediateX = movement.from.x + dx / 2
        val intermediateY = movement.from.y + dy / 2
        return Square(intermediateX, intermediateY)
    }

    private fun hasEaten(game: Game, newGame: Game): Boolean {
        val eatenPieces = game.board.getAllPiecesOfColor(game.turn.opposite()).size - newGame.board.getAllPiecesOfColor(game.turn.opposite()).size
        return eatenPieces > 0
    }

    private fun canEatMore(game: Game, square: Square): Boolean {
        val piece = game.board.getPieceAt(square)

        if (piece != null) {
            val possibleMoves = checkersMovesFinder(square, game)

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

    private fun checkersMovesFinder(square: Square, game: Game) : List<Movement> {
        val piece = game.board.getPieceAt(square) ?: return emptyList()
        val moves = mutableListOf<Movement>()
        val directions = if (piece.color == ColorType.WHITE) listOf(2, -2) else listOf(-2, 2)

        for (dx in directions) {
            for (dy in directions) {
                val destination = Square(square.x + dx, square.y + dy)
                if (game.pieceRules[piece]?.validate(Movement(square, destination, game.board), game) is ValidResult) {
                    if (IsInsideBoardValidator().validate(Movement(square, destination, game.board), game) is ValidResult)
                        moves.add(Movement(square, destination, game.board))
                }
            }
        }
        return moves
    }

    private fun turnPawnIntoKing(square: Square, game: Game) : Game {
        val oldPawn = game.board.getPieceAt(square) ?: throw NoSuchElementException("No piece found")
        val newKing = Piece(oldPawn.color, PieceType.KING, oldPawn.movementQuantity, oldPawn.id)
        val newBoard = game.board.setPieceAt(Movement(square, square, game.board), newKing)
        return Game(newBoard, game.turn, game.generalValidators,
            game.newPieceRules(newBoard, Movement(square, square, game.board)),
            game.winningValidations, game.mover)
    }

}