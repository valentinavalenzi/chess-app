package edu.austral.dissis.chess.validators

import Movement
import edu.austral.dissis.chess.results.InvalidResult
import types.ColorType
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult

class NotBackwardsValidator (val color: ColorType) : Validator {
    override fun validate(movement: Movement): Result {
        return if (color == ColorType.WHITE && movement.from.y < movement.to.y) ValidResult()
        else if (color == ColorType.BLACK && movement.from.y > movement.to.y) ValidResult()
        else InvalidResult()
    }

}