package edu.austral.dissis.common

import edu.austral.dissis.common.types.ColorType

data class Board (val availablePieces: Map<Square, Piece>, val rowAmount: Int, val columnAmount: Int) {

    fun getPieceAt(position: Square): Piece? {
        return availablePieces[position]
    }

    fun setPieceAt(movement: Movement, piece: Piece): Board {
        return Board(availablePieces - movement.from + (movement.to to piece), rowAmount, columnAmount)
    }

    fun move(movement: Movement): Board {
        val piece = getPieceAt(movement.from) ?: throw NoSuchElementException("No piece found")
        val newPiece = piece.move()
        val updatedPieces = availablePieces - movement.from + (movement.to to newPiece)
        return Board(updatedPieces, rowAmount, columnAmount)
    }

    fun getAllOccupiedSquares() : List<Square> {
        return availablePieces.keys.toList()
    }

    fun getAllPiecesOfColor(color: ColorType): Map<Square, Piece> {
        return availablePieces.filter { it.value.color == color }
    }
     fun removePiece(position: Square): Board {
         return Board(availablePieces - position, rowAmount, columnAmount)
     }

    fun addPiece(piece: Piece, square: Square): Board {
        return Board(availablePieces + (square to piece), rowAmount, columnAmount)
    }

    fun getAllSquares() : List<Square> {
        val squares = mutableListOf<Square>()
        for (i in 0 until rowAmount) {
            for (j in 0 until columnAmount) {
                squares.add(Square(i, j))
            }
        }
        return squares
    }

}