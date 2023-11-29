package edu.austral.dissis.chess.mover

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.mover.Mover

class CommonChessMover : Mover {
    override fun canExecuteMove(game: Game, movement: Movement): Boolean {
        return game.board.getPieceAt(movement.from) != null
    }

    override fun move(game: Game, movement: Movement): Game {
        val newBoard = game.board.move(movement)
        return game.copy(newBoard, game.switchTurn(), game.newPieceRules(newBoard, movement))
    }
}