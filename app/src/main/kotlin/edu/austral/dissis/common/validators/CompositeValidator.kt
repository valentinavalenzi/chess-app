package edu.austral.dissis.common.validators

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.results.Result

class CompositeValidator (val validators: List<Validator>) : Validator {

    override fun validate(movement: Movement, game: Game): Result {
        return if (validators.all { it.validate(movement, game) is ValidResult }) ValidResult() else InvalidResult("One or more validators failed!")
    }

    fun add(validator: Validator) = validators.plus(validator)
}