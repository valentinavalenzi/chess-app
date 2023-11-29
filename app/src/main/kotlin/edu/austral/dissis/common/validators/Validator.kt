package edu.austral.dissis.common.validators
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.Result

interface Validator {
    fun validate(movement: Movement, game: Game): Result
}