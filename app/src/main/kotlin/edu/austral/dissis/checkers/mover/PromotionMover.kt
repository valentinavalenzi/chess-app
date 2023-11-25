package edu.austral.dissis.checkers.mover

import edu.austral.dissis.checkers.utils.calculateMiddleSquare
import edu.austral.dissis.common.validators.moves.PawnCanPromoteValidator
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.mover.Mover
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.validators.AndValidator
import types.PieceType

class PromotionMover (val newPieceType: PieceType): Mover {
    override fun canExecuteMove(game: Game, movement: Movement): Boolean {
        return PawnCanPromoteValidator().validate(movement, game) is ValidResult
    }

    override fun move(game: Game, movement: Movement): Game {
        return turnPawnIntoKing(movement, game)
    }

    private fun turnPawnIntoKing(movement: Movement, game: Game): Game {
        val oldPawn = game.board.getPieceAt(movement.to) ?: throw NoSuchElementException("No piece found")
        val newKing = Piece(oldPawn.color, newPieceType, oldPawn.movementQuantity, oldPawn.id)
        val newBoard = game.board.setPieceAt(Movement(movement.from, movement.to, game.board), newKing)
        val newBoardWithoutMiddlePiece = newBoard.removePiece(calculateMiddleSquare(movement))
        return game.copy(newBoardWithoutMiddlePiece, game.turn,
            game.pieceRules - oldPawn + (newKing to AndValidator(listOf())))
    }
}