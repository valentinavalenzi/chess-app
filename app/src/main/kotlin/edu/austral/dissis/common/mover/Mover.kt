package edu.austral.dissis.common.mover

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.game.GameResult

interface Mover {
    fun canExecuteMove(game: Game, movement: Movement): Boolean
    fun move(game: Game, movement: Movement): Game
}