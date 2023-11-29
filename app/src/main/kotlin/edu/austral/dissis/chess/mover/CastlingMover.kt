package edu.austral.dissis.chess.mover

import edu.austral.dissis.chess.validators.other.CastlingValidator
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.mover.Mover
import edu.austral.dissis.common.results.ValidResult

class CastlingMover : Mover {
    override fun canExecuteMove(game: Game, movement: Movement): Boolean {
        return (CastlingValidator().validate(movement, game) is ValidResult)
    }

    override fun move(game: Game, movement: Movement): Game {
        if (movement.to.x - movement.from.x == 2) {
            // short castling
            val rookPosition = Square((movement.from.x + 3), movement.from.y)
            var newBoard = game.board.move(movement)
            var newGame = game.copy(newBoard, game.switchTurn(), game.newPieceRules(newBoard, movement))
            newBoard = newBoard.move(Movement(rookPosition, Square((movement.from.x + 1), movement.from.y), game.board))
            newGame = newGame.copy(newBoard, newGame.turn, newGame.newPieceRules(newBoard,
                Movement(rookPosition, Square((movement.from.x + 1), movement.from.y), newGame.board)))
            return newGame
        }
        // long castling
        val rookPosition = Square((movement.from.x - 4), movement.from.y)
        var newBoard = game.board.move(movement)
        var newGame = game.copy(newBoard, game.switchTurn(), game.newPieceRules(newBoard, movement))
        newBoard = newBoard.move(Movement(rookPosition, Square((movement.from.x - 1), movement.from.y), newGame.board))
        newGame = newGame.copy(newBoard, newGame.turn, newGame.newPieceRules(newBoard,
                            Movement(rookPosition, Square((movement.from.x - 1), movement.from.y), newGame.board)))
        return newGame
    }
}