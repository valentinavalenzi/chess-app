package edu.austral.dissis.common.game

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.CompositeValidator
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.results.game.GameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.results.game.SameMoveResult

data class Game(
    val board: Board, val turn: ColorType,
    val generalValidators: List<Validator>,
    val pieceRules: Map<Piece, CompositeValidator>,
    val winningValidations: List<Validator>
) {

    fun move(piece: Piece, movement: Movement): GameResult {
        if (piece.color == turn && pieceRules[piece]?.validate(movement, this) is ValidResult) {
            val checkGeneralValidations = generalValidations(movement)
            if (checkGeneralValidations is SameMoveResult) return checkGeneralValidations
            if (checkGeneralValidations is NextMoveResult) {
                val newBoard = board.move(movement)
                val newGame = Game(newBoard, switchTurn(), generalValidators,
                                    newPieceRules(newBoard, movement, piece), winningValidations)

                val checkWinningConditions = winningConditions(movement, winningValidations, newGame)
                if (checkWinningConditions is FinishGameResult) return FinishGameResult(checkWinningConditions.winner)

                return NextMoveResult(newGame)
            }
        }
        return SameMoveResult("None of the conditions are met to make the move")
    }


    private fun generalValidations(movement: Movement): GameResult {
        for (validator in generalValidators) {
            when (val validation = validator.validate(movement, this)) {
                is ValidResult -> continue
                is InvalidResult -> return SameMoveResult(validation.reason)
            }
        }
        return NextMoveResult(this)
    }

    private fun switchTurn(): ColorType {
        return if (turn == ColorType.WHITE) {
            ColorType.BLACK
        } else
            ColorType.WHITE
    }

    private fun newPieceRules(newBoard: Board, movement: Movement, oldPiece: Piece): Map<Piece, CompositeValidator> {
        val newPiece = newBoard.getPieceAt(movement.to)
        val newPieceRules = pieceRules.toMutableMap()
        if (newPiece != null) {
            newPieceRules.remove(oldPiece)
            newPieceRules[newPiece] = pieceRules[oldPiece] ?: error("No rules found for the old piece")
            return newPieceRules
        }
        return newPieceRules
    }

    private fun winningConditions(movement: Movement, winningValidations: List<Validator>, newGame: Game): GameResult {
        return if (winningValidations.any() {it.validate(movement, newGame) is InvalidResult}) FinishGameResult(turn)
        else NextMoveResult(newGame)
    }

}