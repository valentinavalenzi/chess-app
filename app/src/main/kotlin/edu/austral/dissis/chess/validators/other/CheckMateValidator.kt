package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.results.game.GameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.utils.movesFinder
import edu.austral.dissis.common.validators.EndGameValidator
import edu.austral.dissis.common.validators.Validator

class CheckMateValidator : EndGameValidator {

    private val checkValidator = CheckValidator()
    override fun validate(movement: Movement, game: Game): GameResult {
        val board = game.board
        val oppositeColor = game.turn
        val enemyPieces = board.getAllPiecesOfColor(oppositeColor)
        for ((square) in enemyPieces) {
            val possibleMovements = movesFinder(square, game)
            for (possibleMovement in possibleMovements) {
                if (checkValidator.isInCheck(possibleMovement, game)) return FinishGameResult(game.turn)
            }
        }
        return NextMoveResult(game)
    }

}
