package edu.austral.dissis.chess.validators.game

import Piece
import Square
import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.classicGame.ClassicGameSetup
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.types.ColorType
import types.PieceType
import kotlin.test.Test
import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.other.CheckValidator

class CheckTest {

    private val king = Piece(ColorType.BLACK, PieceType.KING, 0)
    private val rook = Piece(ColorType.WHITE, PieceType.ROOK, 0)
    private val pawn = Piece(ColorType.WHITE, PieceType.PAWN, 0)
    private val queen = Piece(ColorType.WHITE, PieceType.QUEEN, 0)

    private val pieces = mapOf(
        Square(0,3) to king,
        Square(1,0) to rook,
        Square(1, 4) to pawn,
        Square(2, 2) to queen
    )
    private val gameSetup = ClassicGameSetup()
    private val validatorsMap = gameSetup.createValidatorsMap(pieces)
    private val board = Board(pieces, validatorsMap, 8, 8)
    private val checkValidator = CheckValidator()

    @Test
    fun testIsInCheck() {
        assert(checkValidator.validate(Movement(Square(0,3), Square(0, 4), board)) is InvalidResult)
    }

    @Test
    fun secondTestCheck() {

        val blackKing = Piece(ColorType.BLACK, PieceType.KING, 0)
        val rook = Piece(ColorType.WHITE, PieceType.ROOK, 0)
        val king = Piece(ColorType.WHITE, PieceType.KING, 0)

        val pieces = mapOf(
            Square(0,7) to blackKing,
            Square(1,6) to rook,
            Square(2, 5) to king
        )
        val gameSetup = ClassicGameSetup()
        val validatorsMap = gameSetup.createValidatorsMap(pieces)
        val board = Board(pieces, validatorsMap, 8, 8)
        val checkValidator = CheckValidator()

        assert(checkValidator.validate(Movement(Square(0,7), Square(1, 7), board)) is InvalidResult)
    }

    @Test
    fun thirdTestCheck() {

        val blackKing = Piece(ColorType.BLACK, PieceType.KING, 0)
        val rook = Piece(ColorType.WHITE, PieceType.ROOK, 0)
        val king = Piece(ColorType.WHITE, PieceType.KING, 0)

        val pieces = mapOf(
            Square(0,7) to blackKing,
            Square(1,6) to rook,
            Square(2, 5) to king
        )
        val gameSetup = ClassicGameSetup()
        val validatorsMap = gameSetup.createValidatorsMap(pieces)
        val board = Board(pieces, validatorsMap, 8, 8)
        val checkValidator = CheckValidator()

        assert(checkValidator.validate(Movement(Square(0,7), Square(1, 6), board)) is InvalidResult)
    }

}