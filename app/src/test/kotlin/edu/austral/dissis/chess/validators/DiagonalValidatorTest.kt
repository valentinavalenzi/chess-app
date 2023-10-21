package edu.austral.dissis.chess.validators

import Movement
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.orientation.DiagonalValidator
import kotlin.test.Test
import kotlin.test.assertTrue

class DiagonalValidatorTest {
    private val validator = DiagonalValidator()

    @Test
    fun validateMoveDiagonalUp() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(3,3))) is ValidResult)
    }
    @Test
    fun invalidateMoveDiagonalUp() {
        assertTrue(validator.validate(Movement(Square(2, 2), Square(4, 3))) is InvalidResult)
    }
    @Test
    fun validateMoveDiagonalDown() {
        assertTrue(validator.validate(Movement(Square(4,4), Square(2,2))) is ValidResult)
    }
    @Test
    fun invalidateMoveDiagonalDown() {
        assertTrue(validator.validate(Movement(Square(2, 2), Square(1, 2))) is InvalidResult)
    }
}