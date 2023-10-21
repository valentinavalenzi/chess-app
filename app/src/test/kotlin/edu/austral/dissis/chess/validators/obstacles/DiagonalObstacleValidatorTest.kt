package edu.austral.dissis.chess.validators.obstacles

import Board
import Movement
import Piece
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.AndValidator
import edu.austral.dissis.chess.validators.CompositeValidator
import edu.austral.dissis.chess.validators.NotBackwardsValidator
import edu.austral.dissis.chess.validators.OrValidator
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.amounts.QMoveNSquaresValidator
import edu.austral.dissis.chess.validators.orientation.DiagonalValidator
import edu.austral.dissis.chess.validators.orientation.HorizontalValidator
import edu.austral.dissis.chess.validators.orientation.VerticalValidator
import org.junit.jupiter.api.Test
import types.ColorType
import types.PieceType

class DiagonalObstacleValidatorTest {

    val pawn = Piece(ColorType.WHITE, PieceType.PAWN, 0)
    val bishop = Piece(ColorType.WHITE, PieceType.BISHOP, 0)
    private val pieces = mapOf(
        Square(2, 1) to pawn,
        Square(2, 0) to bishop
    )
    val verticalValidator = VerticalValidator()
    val notMovingBackwardsValidator = NotBackwardsValidator(ColorType.WHITE)
    val pawnAndValidators = AndValidator(listOf(verticalValidator, notMovingBackwardsValidator))

    val amountValidator = AmountValidator(1)
    val firstMoveValidator = QMoveNSquaresValidator(0, 2, pawn)
    val pawnOrValidator = OrValidator(listOf(amountValidator, firstMoveValidator))

    val diagonalValidator = DiagonalValidator()

    val pawnComposite = CompositeValidator(listOf(pawnAndValidators, pawnOrValidator))
    val bishopComposite = CompositeValidator(listOf(diagonalValidator))
    val piecesValidator = mapOf(pawn to pawnComposite, bishop to bishopComposite)

    private val board = Board(pieces, piecesValidator, 8, 8)

    @Test
    fun checkDiagonalObstacleValidator() {
        val validator = DiagonalObstacleValidator(board::getPieceAt)
        assert(validator.validate(Movement(Square(2, 0), Square(0, 2))) is InvalidResult)
    }

    @Test
    fun checkBlackVerticalObstacleValidator() {

        val blackPawn = Piece(ColorType.BLACK, PieceType.PAWN, 0)
        val blackBishop = Piece(ColorType.BLACK, PieceType.BISHOP, 0)
        val blackPieces = mapOf(
            Square(1, 6) to blackPawn,
            Square(2, 7) to blackBishop
        )

        val diagonalValidator = DiagonalValidator()
        val bishopComposite = CompositeValidator(listOf(diagonalValidator))
        val bPiecesValidator = mapOf(bishop to bishopComposite)

        val bBoard = Board(blackPieces, bPiecesValidator, 8, 8)

        val validator = DiagonalObstacleValidator(bBoard::getPieceAt)
        assert(validator.validate(Movement(Square(2, 7), Square(0, 5))) is InvalidResult)
    }

}