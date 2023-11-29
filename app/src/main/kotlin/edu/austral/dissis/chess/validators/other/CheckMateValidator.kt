package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.results.game.GameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.utils.getKingPosition
import edu.austral.dissis.common.utils.movesFinder
import edu.austral.dissis.common.utils.movesToFinder
import edu.austral.dissis.common.validators.EndGameValidator

class CheckMateValidator : EndGameValidator {

    private val checkValidator = CheckValidator()

    override fun validate(movement: Movement, game: Game): GameResult {
        val oppositeColor = game.turn
        val kingPosition = getKingPosition(game.board, oppositeColor) ?: throw NoSuchElementException("No king found")

        if (isCheckMate(game, oppositeColor, kingPosition)) {
            return FinishGameResult(game.turn)
        }

        return NextMoveResult(game)
    }

    private fun isCheckMate(game: Game, oppositeColor: ColorType, kingPosition: Square): Boolean {
        val possibleKingMoves = movesFinder(kingPosition, game)
        val kingInCheck = checkValidator.isCheck(game.board, oppositeColor, game)

        // Check if the king is in check
        if (!kingInCheck) return false // King is not in check

        // Check if the king has no available moves
        return possibleKingMoves.all { move ->
            val hypotheticalGame = simulateMove(game, move)
            val isStillInCheck = checkValidator.isCheck(hypotheticalGame.board, oppositeColor, hypotheticalGame)
            isStillInCheck
        }
    }


    private fun simulateMove(game: Game, movement: Movement): Game {
        val newBoard = game.board.move(movement)
        return game.copy(board = newBoard, turn = game.turn.opposite())
    }
}