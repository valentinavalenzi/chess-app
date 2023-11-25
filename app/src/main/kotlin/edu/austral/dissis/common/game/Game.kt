package edu.austral.dissis.common.game

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.mover.Mover
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.validators.Validator
import edu.austral.dissis.common.results.game.GameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.results.game.SameMoveResult
import edu.austral.dissis.common.validators.EndGameValidator
import edu.austral.dissis.common.validators.moves.CanMakeMoveValidator

data class Game(
    val board: Board,
    val turn: ColorType,
    val generalValidators: List<Validator>,
    val pieceRules: Map<Piece, Validator>,
    val winningValidations: List<EndGameValidator>,
    val movers: List<Mover>
) {

    fun move(movement: Movement): GameResult {
        if (CanMakeMoveValidator().validate(movement, this) is ValidResult) {
            val checkGeneralValidations = generalValidations(movement)
            if (checkGeneralValidations is SameMoveResult) return checkGeneralValidations
            if (checkGeneralValidations is NextMoveResult) {
                return executeMove(movement)
            }
        }
        return SameMoveResult("None of the conditions are met to make the move")
    }

    private fun executeMove(movement: Movement): GameResult {
        var newGame = this
        for (mover in movers) {
            if (mover.canExecuteMove(newGame, movement)) {
                newGame = mover.move(newGame, movement)

                val checkWinningConditions = winningConditions(movement, winningValidations, newGame)
                if (checkWinningConditions is FinishGameResult) return FinishGameResult(checkWinningConditions.winner)
            }
        }
        return NextMoveResult(newGame)
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

    fun switchTurn(): ColorType {
        return if (turn == ColorType.WHITE) {
            ColorType.BLACK
        } else
            ColorType.WHITE
    }

    fun newPieceRules(newBoard: Board, movement: Movement): Map<Piece, Validator> {
        val oldPiece = board.getPieceAt(movement.from) ?: error("No piece found at the source position")
        val newPiece = newBoard.getPieceAt(movement.to)
        val newPieceRules = pieceRules.toMutableMap()
        if (newPiece != null) {
            newPieceRules.remove(oldPiece)
            if (newPiece.type == oldPiece.type) {
                newPieceRules[newPiece] = pieceRules[oldPiece] ?: error("No rules found for the old piece")
                return newPieceRules
            }
        }
        return newPieceRules
    }

    fun copy(board: Board, turn: ColorType, newPieceRules: Map<Piece, Validator>): Game = Game(
        board,
        turn,
        generalValidators,
        newPieceRules,
        winningValidations,
        movers
    )

    private fun winningConditions(
        movement: Movement,
        winningValidations: List<EndGameValidator>,
        newGame: Game
    ): GameResult {
        return if (winningValidations.any() { it.validate(movement, newGame) is FinishGameResult }) FinishGameResult(
            newGame.turn
        )
        else NextMoveResult(newGame)
    }

}