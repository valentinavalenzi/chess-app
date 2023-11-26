package edu.austral.dissis.common.mover

import edu.austral.dissis.checkers.utils.calculateMiddleSquare
import edu.austral.dissis.common.validators.moves.PawnCanPromoteValidator
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.Validator
import types.PieceType

class PromotionMover (val newPieceType: PieceType, val newValidators: AndValidator): Mover {
    override fun canExecuteMove(game: Game, movement: Movement): Boolean {
        return PawnCanPromoteValidator().validate(movement, game) is ValidResult
    }

    override fun move(game: Game, movement: Movement): Game {
        return promotePawn(movement, game)
    }

    private fun promotePawn(movement: Movement, game: Game): Game {
        val oldPawn = game.board.getPieceAt(movement.to) ?: throw NoSuchElementException("No piece found")
        val newPiece = Piece(oldPawn.color, newPieceType, oldPawn.movementQuantity, oldPawn.id)
        val newBoard = game.board.setPieceAt(Movement(movement.from, movement.to, game.board), newPiece)
        val newBoardWithoutMiddlePiece = newBoard.removePiece(calculateMiddleSquare(movement))
        return game.copy(newBoardWithoutMiddlePiece, game.turn,
            game.pieceRules - oldPawn + (newPiece to newValidators))
    }
}