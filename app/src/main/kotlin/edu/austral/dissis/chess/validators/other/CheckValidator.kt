package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.utils.getKingPosition
import edu.austral.dissis.common.game.Game

class CheckValidator : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (isInCheck(movement, game)) {
            InvalidResult("It's in check!") // because it will be in check if it moves
        } else {
            ValidResult()
        }
    }

    fun isInCheck(movement: Movement, game: Game): Boolean {
        val board = movement.board
        val piece = board.getPieceAt(movement.from)
        val pieceColor: ColorType = piece?.color ?: throw NoSuchElementException("No piece found")
        // make the movement and validate for check
        val newBoard = board.setPieceAt(movement, piece)
        return isCheck(newBoard, pieceColor, game)
    }

    private fun isCheck(board: Board, color: ColorType, game: Game): Boolean {
        val kingPosition: Square = getKingPosition(board, color) ?: throw NoSuchElementException("No king found")
        val enemyPieces = board.getAllPiecesOfColor(color.opposite())
        for ((position, enemyPiece) in enemyPieces) {
            if (isAttackingKing(board, position, enemyPiece, kingPosition, game)) return true
        }
        return false
    }

    private fun isAttackingKing(board: Board, from: Square, enemyPiece: Piece, kingPosition: Square, game: Game): Boolean {
        val movement = Movement(from, kingPosition, board)
        // if it's invalid it's because an enemy could make a movement to eat the king
        return (game.pieceRules[enemyPiece]?.validate(movement, game) is ValidResult) &&
                (NotEatingKingValidator().validate(movement, game) is InvalidResult)
    }

}