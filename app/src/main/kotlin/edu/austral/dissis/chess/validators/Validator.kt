package edu.austral.dissis.chess.validators
import Movement
import edu.austral.dissis.chess.results.Result

interface Validator {
    fun validate(movement: Movement): Result
}