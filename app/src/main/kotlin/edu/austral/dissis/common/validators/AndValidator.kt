package edu.austral.dissis.common.validators

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

class AndValidator (val validators: List<Validator>): Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (validators.all { it.validate(movement, game) is ValidResult }) ValidResult()
        else InvalidResult("One or more validators are not valid!")
    }
}