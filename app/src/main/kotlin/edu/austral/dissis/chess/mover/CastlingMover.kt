package edu.austral.dissis.chess.mover

import edu.austral.dissis.chess.validators.other.CastlingValidator
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.game.Game
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
            val newBoard = game.board
                .move(movement)
                .move(Movement(rookPosition, Square((movement.from.x + 1), movement.from.y), game.board))
            return game.copy(newBoard, game.switchTurn(), game.newPieceRules(newBoard, movement))
                        .copy(newBoard, game.switchTurn(),
                            game.newPieceRules(
                                newBoard,
                                Movement(rookPosition, Square((movement.from.x + 1), movement.from.y), game.board)
                            )
                        )

        }
        // long castling
        val rookPosition = Square((movement.from.x - 4), movement.from.y)
        val newBoard = game.board
            .move(movement)
            .move(Movement(rookPosition, Square((movement.from.x - 1), movement.from.y), game.board))
        return game.copy(newBoard, game.switchTurn(), game.newPieceRules(newBoard, movement))
                    .copy(newBoard, game.switchTurn(),
                        game.newPieceRules(
                            newBoard,
                            Movement(rookPosition, Square((movement.from.x - 1), movement.from.y), game.board)
                        )
                    )
    }
}