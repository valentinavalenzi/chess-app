package edu.austral.dissis.common.validators

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.game.GameResult

interface EndGameValidator {

    fun validate(movement: Movement, game: Game): GameResult
}