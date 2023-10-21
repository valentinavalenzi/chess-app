package edu.austral.dissis.chess.validators.orientation
import Movement
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.orientation.VerticalValidator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class VerticalValidatorTest {

    private val validator = VerticalValidator()

    @Test
    fun validateMoveUp() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(2,3))) is ValidResult)
    }
    @Test
    fun invalidateMoveUp() {
        assertTrue(validator.validate(Movement(Square(4,2), Square(2,3))) is InvalidResult)
    }
    @Test
    fun validateMoveDown() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(2,1))) is ValidResult)
    }
    @Test
    fun invalidateMoveDown() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(1,1))) is InvalidResult)
    }
}