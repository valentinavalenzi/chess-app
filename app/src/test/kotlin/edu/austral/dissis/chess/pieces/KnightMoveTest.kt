package edu.austral.dissis.chess.pieces

import Piece
import Square
import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.validators.AndValidator
import edu.austral.dissis.chess.validators.CompositeValidator
import edu.austral.dissis.chess.validators.movesFinder
import edu.austral.dissis.chess.validators.orientation.LValidator
import edu.austral.dissis.chess.validators.other.IsInsideBoardValidator
import edu.austral.dissis.chess.validators.other.NotEatingSameColor
import org.junit.jupiter.api.Test
import edu.austral.dissis.chess.types.ColorType
import types.PieceType

class KnightMoveTest {

    private val whiteKnight = Piece(ColorType.WHITE, PieceType.KNIGHT, 0)

    private val globalRules = AndValidator(listOf(IsInsideBoardValidator(), NotEatingSameColor()))
    private val lOrientation = LValidator()

    val knightRules = CompositeValidator(listOf(globalRules, lOrientation))

    private val board = Board(
        mapOf(
            Square(1, 0) to whiteKnight,
            Square(0, 1) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(1, 1) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(3, 1) to Piece(ColorType.WHITE, PieceType.PAWN, 0)),
        mapOf(whiteKnight to knightRules),
        8,8
    )

    @Test
    fun knightMoves() {
        val moves = movesFinder(Square(1, 0), board, listOf(knightRules)) }
}