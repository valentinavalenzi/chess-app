package edu.austral.dissis.chess.classicChess

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.factory.EveryPieceFactory
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.factory.BoardFactory
import edu.austral.dissis.common.types.ColorType
import types.PieceType

class ClassicChessBoardFactory : BoardFactory {

    val everyPieceFactory = EveryPieceFactory()
    override fun createBoard(rowAmount: Int, colAmount: Int): Board {
        val pieces = mapOf(
            Square(1, 1) to everyPieceFactory.createPiece(PieceType.ROOK, ColorType.BLACK, 0),
            Square(2, 1) to everyPieceFactory.createPiece(PieceType.KNIGHT, ColorType.BLACK, 1),
            Square(3, 1) to everyPieceFactory.createPiece(PieceType.BISHOP, ColorType.BLACK, 2),
            Square(4, 1) to everyPieceFactory.createPiece(PieceType.QUEEN, ColorType.BLACK, 3),
            Square(5, 1) to everyPieceFactory.createPiece(PieceType.KING, ColorType.BLACK, 4),
            Square(6, 1) to everyPieceFactory.createPiece(PieceType.BISHOP, ColorType.BLACK, 5),
            Square(7, 1) to everyPieceFactory.createPiece(PieceType.KNIGHT, ColorType.BLACK, 6),
            Square(8, 1) to everyPieceFactory.createPiece(PieceType.ROOK, ColorType.BLACK, 7),

            Square(1, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 8),
            Square(2, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 9),
            Square(3, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 10),
            Square(4, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 11),
            Square(5, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 12),
            Square(6, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 13),
            Square(7, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 14),
            Square(8, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 15),

            Square(1, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 16),
            Square(2, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 17),
            Square(3, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 18),
            Square(4, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 19),
            Square(5, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 20),
            Square(6, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 21),
            Square(7, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 22),
            Square(8, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 23),

            Square(1, 8) to everyPieceFactory.createPiece(PieceType.ROOK, ColorType.WHITE, 24),
            Square(2, 8) to everyPieceFactory.createPiece(PieceType.KNIGHT, ColorType.WHITE, 25),
            Square(3, 8) to everyPieceFactory.createPiece(PieceType.BISHOP, ColorType.WHITE, 26),
            Square(4, 8) to everyPieceFactory.createPiece(PieceType.QUEEN, ColorType.WHITE, 27),
            Square(5, 8) to everyPieceFactory.createPiece(PieceType.KING, ColorType.WHITE, 28),
            Square(6, 8) to everyPieceFactory.createPiece(PieceType.BISHOP, ColorType.WHITE, 29),
            Square(7, 8) to everyPieceFactory.createPiece(PieceType.KNIGHT, ColorType.WHITE, 30),
            Square(8, 8) to everyPieceFactory.createPiece(PieceType.ROOK, ColorType.WHITE, 31),

        )
        return Board(pieces, rowAmount, colAmount)
    }
}