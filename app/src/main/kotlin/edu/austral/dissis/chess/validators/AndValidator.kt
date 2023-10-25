package edu.austral.dissis.chess.validators

import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult

class AndValidator (val validators: List<Validator>): Validator {
    override fun validate(movement: Movement): Result {
        return if (validators.all { it.validate(movement) is ValidResult }) ValidResult()
        else InvalidResult()
    }
}