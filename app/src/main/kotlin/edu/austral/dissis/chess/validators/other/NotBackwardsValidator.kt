package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.Validator

class NotBackwardsValidator (val color: ColorType) : Validator {
    override fun validate(movement: Movement, game: Game): Result {
        return if (color == ColorType.BLACK && movement.from.y < movement.to.y) ValidResult()
        else if (color == ColorType.WHITE && movement.from.y > movement.to.y) ValidResult()
        else InvalidResult("You are trying to move backwards!")
    }

}