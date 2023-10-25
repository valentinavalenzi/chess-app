package edu.austral.dissis.chess.validators.obstacles

import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.Movement
import Piece
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.validators.AndValidator
import edu.austral.dissis.chess.validators.CompositeValidator
import edu.austral.dissis.chess.validators.OrValidator
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.orientation.DiagonalValidator
import edu.austral.dissis.chess.validators.orientation.HorizontalValidator
import edu.austral.dissis.chess.validators.orientation.VerticalValidator
import org.junit.jupiter.api.Test
import edu.austral.dissis.chess.types.ColorType
import types.PieceType

class HorizontalObstacleValidatorTest {
    @Test
    fun checkHorizontalObstacleValidator() {
        val blackKing = Piece(ColorType.BLACK, PieceType.KING, 0)
        val blackBishop = Piece(ColorType.BLACK, PieceType.BISHOP, 0)
        val blackPieces = mapOf(
            Square(4, 7) to blackKing,
            Square(5, 7) to blackBishop
        )

        val horizontalValidator = HorizontalValidator()
        val verticalValidator = VerticalValidator()
        val diagonalValidator = DiagonalValidator()
        //as if the king could move by 2 squares
        val oneValidator = AmountValidator(2)
        val orValidators = OrValidator(listOf(horizontalValidator, diagonalValidator, verticalValidator))
        val andValidator = AndValidator(listOf(oneValidator))
        val kingComposite = CompositeValidator(listOf(andValidator, orValidators))
        val bPiecesValidator = mapOf(blackKing to kingComposite)

        val bBoard = Board(blackPieces, bPiecesValidator, 8, 8)

        val validator = HorizontalObstacleValidator()
        assert(validator.validate(Movement(Square(4, 7), Square(6, 7), bBoard)) is InvalidResult)
    }
}