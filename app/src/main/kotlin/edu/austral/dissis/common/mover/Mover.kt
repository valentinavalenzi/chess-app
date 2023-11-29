package edu.austral.dissis.common.mover

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game

interface Mover {
    fun canExecuteMove(game: Game, movement: Movement): Boolean
    fun move(game: Game, movement: Movement): Game
}