package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.chess.Movement
import Piece
import Square
import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator
import edu.austral.dissis.chess.types.ColorType
import types.PieceType

class CheckValidator() : Validator {
    override fun validate(movement: Movement): Result {
        return if (isInCheck(movement)) {
            InvalidResult() // because it will be in check if it moves
        } else {
            ValidResult()
        }
    }

    private fun isInCheck(movement: Movement): Boolean {
        val piece = movement.board.getPieceAt(movement.from)
        val pieceColor: ColorType = piece?.color ?: throw NoSuchElementException("No piece found")
        val board = movement.board
        val pieceRules = board.pieceRules
        //make the movement and validate for check
        return if (pieceRules[piece]!!.validate(movement) is ValidResult) {
            val newBoard = board.setPieceAt(movement, piece)
            isCheck(newBoard, pieceColor)
        } else false
    }

    private fun isCheck(board: Board, color: ColorType): Boolean {
        val kingPosition: Square = getKingPosition(board, color) ?: throw NoSuchElementException("No king found")
        val enemyPieces = board.getAllPiecesOfColor(color.opposite())
        for ((position, enemyPiece) in enemyPieces) {
            if (isAttackingKing(board, position, enemyPiece, kingPosition)) return true
        }
        return false
    }

    private fun getKingPosition(board: Board, color: ColorType): Square? {
        val positions = board.getAllOccupiedSquares()
        for (position in positions) {
            val piece = board.getPieceAt(position) ?: throw NoSuchElementException("No piece found")
            if (piece.type == PieceType.KING && piece.color == color) return position
        }
        return null
    }

    private fun isAttackingKing(board: Board, from: Square, enemyPiece: Piece, kingPosition: Square): Boolean {
        val movement = Movement(from, kingPosition, board)
        // if it's invalid it's because an enemy could make a movement to eat the king
        return (board.pieceRules[enemyPiece]!!.validate(movement) is ValidResult) && (NotEatingKingValidator().validate(movement) is InvalidResult)
    }

}