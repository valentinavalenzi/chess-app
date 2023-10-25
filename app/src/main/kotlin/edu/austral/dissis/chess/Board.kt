package edu.austral.dissis.chess

import Piece
import Square
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.CompositeValidator
import edu.austral.dissis.chess.types.ColorType

data class Board (private val availablePieces: Map<Square, Piece>, val pieceRules: Map<Piece, CompositeValidator>,
                  val rowAmount: Int, val columnAmount: Int) {

    fun getPieceAt(position: Square): Piece? {
        return availablePieces[position]
    }

    fun setPieceAt(movement: Movement, piece: Piece): Board {
        return Board(availablePieces - movement.from + (movement.to to piece), pieceRules, rowAmount, columnAmount)
    }

    fun move(movement: Movement): Board {
        val piece = getPieceAt(movement.from) ?: throw NoSuchElementException("No piece found")
        return if (pieceRules[piece]!!.validate(movement) is ValidResult) {
            val newPiece = piece.move()
            val updatedPieces = availablePieces - movement.from + (movement.to to newPiece)
            val newPieceRules = pieceRules - piece + (newPiece to pieceRules[piece]!!)
            Board(updatedPieces, newPieceRules, rowAmount, columnAmount)
        } else {
            Board(availablePieces, pieceRules, rowAmount, columnAmount)
        }
    }

    fun getAllOccupiedSquares() : List<Square> {
        return availablePieces.keys.toList()
    }

    fun getAllPiecesOfColor(color: ColorType): Map<Square, Piece> {
        return availablePieces.filter { it.value.color == color }
    }

}