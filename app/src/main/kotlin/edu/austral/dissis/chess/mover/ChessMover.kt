package edu.austral.dissis.chess.mover

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.mover.Mover

class ChessMover : Mover {

    // missing pawn promotion, enroque

    override fun move(game: Game, movement: Movement): Game {
        val newBoard = game.board.move(movement)
        return Game(newBoard, game.switchTurn(),
            game.generalValidators,
            game.newPieceRules(newBoard, movement),
            game.winningValidations, game.mover)
    }

}
