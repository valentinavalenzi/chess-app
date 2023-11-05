package edu.austral.dissis.common.validators

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult

class OrValidator (val validators: List<Validator>): Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (validators.any { it.validate(movement, game) is ValidResult }) ValidResult()
        else InvalidResult("None of the validators were valid!")
    }
}