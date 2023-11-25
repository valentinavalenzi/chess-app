package edu.austral.dissis.checkers.mover

import edu.austral.dissis.checkers.validators.IsEatingValidator
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.mover.Mover
import edu.austral.dissis.common.results.InvalidResult

class CommonCheckersMover : Mover {
    override fun canExecuteMove(game: Game, movement: Movement): Boolean {
        return IsEatingValidator().validate(movement, game) is InvalidResult
    }

    override fun move(game: Game, movement: Movement): Game {
        val newBoard = game.board.move(movement)
        return game.copy(newBoard, turn = game.turn.opposite(), newPieceRules = game.newPieceRules(newBoard, movement))
    }
}