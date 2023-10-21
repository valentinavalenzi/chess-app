package edu.austral.dissis.chess.validators

import Board
import Movement
import Piece
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.amounts.QMoveNSquaresValidator
import edu.austral.dissis.chess.validators.obstacles.VerticalObstacleValidator
import edu.austral.dissis.chess.validators.orientation.HorizontalValidator
import edu.austral.dissis.chess.validators.orientation.VerticalValidator
import org.junit.jupiter.api.Test
import types.ColorType
import types.PieceType

class VerticalObstacleValidatorTest {

    val pawn = Piece(ColorType.WHITE, PieceType.PAWN, 0)
    val rook = Piece(ColorType.WHITE, PieceType.ROOK, 0)
    private val pieces = mapOf(
        Square(0, 1) to pawn,
        Square(0, 0) to rook
    )
    val verticalValidator = VerticalValidator()
    val notMovingBackwardsValidator = NotBackwardsValidator(ColorType.WHITE)
    val pawnAndValidators = AndValidator(listOf(verticalValidator, notMovingBackwardsValidator))

    val amountValidator = AmountValidator(1)
    val firstMoveValidator = QMoveNSquaresValidator(0, 2, pawn)
    val pawnOrValidator = OrValidator(listOf(amountValidator, firstMoveValidator))

    val horizontalValidator = HorizontalValidator()
    val rookOrValidators = OrValidator(listOf(horizontalValidator, verticalValidator))

    val pawnComposite = CompositeValidator(listOf(pawnAndValidators, pawnOrValidator))
    val rookComposite = CompositeValidator(listOf(rookOrValidators))
    val piecesValidator = mapOf(pawn to pawnComposite, rook to rookComposite)

    private val board = Board(pieces, piecesValidator, 8, 8)

    @Test
    fun checkVerticalObstacleValidator() {
        val validator = VerticalObstacleValidator(board::getPieceAt)
        assert(validator.validate(Movement(Square(0, 0), Square(0, 3))) is InvalidResult)
    }
}