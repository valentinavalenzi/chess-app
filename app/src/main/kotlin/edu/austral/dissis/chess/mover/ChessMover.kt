package edu.austral.dissis.chess.mover

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.mover.Mover

class ChessMover : Mover {
    override fun canExecuteMove(game: Game, movement: Movement): Boolean {
        TODO("Not yet implemented")
    }

    override fun move(game: Game, movement: Movement): Game {
        val newBoard = game.board.move(movement)
        return game.copy(newBoard, game.switchTurn())
    }
}
