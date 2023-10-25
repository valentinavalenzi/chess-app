package edu.austral.dissis.chess.validators.orientation

import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.Movement
import Square
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.ValidResult
import kotlin.test.Test
import kotlin.test.assertTrue

class DiagonalValidatorTest {
    private val validator = DiagonalValidator()
    val board = Board(emptyMap(), emptyMap(), 8, 8)

    @Test
    fun validateMoveDiagonalUp() {
        assertTrue(validator.validate(Movement(Square(2,2), Square(3,3), board)) is ValidResult)
    }
    @Test
    fun invalidateMoveDiagonalUp() {
        assertTrue(validator.validate(Movement(Square(2, 2), Square(4, 3), board)) is InvalidResult)
    }
    @Test
    fun validateMoveDiagonalDown() {
        assertTrue(validator.validate(Movement(Square(4,4), Square(2,2), board)) is ValidResult)
    }
    @Test
    fun invalidateMoveDiagonalDown() {
        assertTrue(validator.validate(Movement(Square(2, 2), Square(1, 2), board)) is InvalidResult)
    }
}