package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.chess.Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.types.ColorType
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.Validator

class NotBackwardsValidator (val color: ColorType) : Validator {
    override fun validate(movement: Movement): Result {
        return if (color == ColorType.BLACK && movement.from.y < movement.to.y) ValidResult()
        else if (color == ColorType.WHITE && movement.from.y > movement.to.y) ValidResult()
        else InvalidResult()
    }

}