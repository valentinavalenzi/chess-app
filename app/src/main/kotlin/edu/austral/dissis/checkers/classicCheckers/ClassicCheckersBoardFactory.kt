package edu.austral.dissis.checkers.classicCheckers

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.factory.BoardFactory
import edu.austral.dissis.common.factory.EveryPieceFactory
import edu.austral.dissis.common.types.ColorType
import types.PieceType

class ClassicCheckersBoardFactory : BoardFactory {

    private val everyPieceFactory = EveryPieceFactory()
    override fun createBoard(rowAmount: Int, colAmount: Int): Board {
        val pieces = mapOf(
            Square(2, 1) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 0),
            Square(4, 1) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 1),
            Square(6, 1) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 2),
            Square(8, 1) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 3),
            Square(1, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 4),
            Square(3, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 5),
            Square(5, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 6),
            Square(7, 2) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 7),
            Square(2, 3) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 8),
            Square(4, 3) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 9),
            Square(6, 3) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 10),
            Square(8, 3) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.BLACK, 11),

            Square(1, 6) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 12),
            Square(3, 6) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 13),
            Square(5, 6) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 14),
            Square(7, 6) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 15),
            Square(2, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 16),
            Square(4, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 17),
            Square(6, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 18),
            Square(8, 7) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 19),
            Square(1, 8) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 20),
            Square(3, 8) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 21),
            Square(5, 8) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 22),
            Square(7, 8) to everyPieceFactory.createPiece(PieceType.PAWN, ColorType.WHITE, 23),
        )
        return Board(pieces, rowAmount, colAmount)
    }
}