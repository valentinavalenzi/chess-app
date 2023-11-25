package edu.austral.dissis.checkers.mover

import edu.austral.dissis.checkers.utils.calculateMiddleSquare
import edu.austral.dissis.checkers.utils.canEatMore
import edu.austral.dissis.checkers.validators.IsEatingValidator
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.mover.Mover
import edu.austral.dissis.common.results.ValidResult

class CaptureMover : Mover {
    override fun canExecuteMove(game: Game, movement: Movement): Boolean {
        return IsEatingValidator().validate(movement, game) is ValidResult
    }

    override fun move(game: Game, movement: Movement): Game {
        // captures and makes new board and game afterwards
        val middleSquare = calculateMiddleSquare(movement)
        val newBoard = game.board.move(movement).removePiece(middleSquare)
        val newGame = game.copy(board = newBoard, turn = game.turn, game.newPieceRules(newBoard, movement))

        return if (hasEaten(game, newGame) && canEatMore(newGame, movement.to)) newGame
        else newGame.copy(turn = newGame.switchTurn())
    }

    private fun hasEaten(game: Game, newGame: Game): Boolean {
        val gamePieces = game.board.getAllPiecesOfColor(game.turn.opposite())
        val newGamePieces = newGame.board.getAllPiecesOfColor(game.turn.opposite())
        return (gamePieces.size - newGamePieces.size) > 0
    }

}