package edu.austral.dissis.chess.validators.orientation
import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.Movement
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.ValidResult
import kotlin.test.Test
import kotlin.test.assertTrue

class VerticalValidatorTest {

    private val validator = VerticalValidator()
    val board = Board(emptyMap(), emptyMap(), 8, 8)

    @Test
    fun validateMoveUp() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(2,3), board)) is ValidResult)
    }
    @Test
    fun invalidateMoveUp() {
        assertTrue(validator.validate(Movement(Square(4,2), Square(2,3), board)) is InvalidResult)
    }
    @Test
    fun validateMoveDown() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(2,1), board)) is ValidResult)
    }
    @Test
    fun invalidateMoveDown() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(1,1), board)) is InvalidResult)
    }
}