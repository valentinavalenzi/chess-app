package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.results.game.GameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.utils.movesFinder
import edu.austral.dissis.common.validators.EndGameValidator

class NoMoreMovesValidator : EndGameValidator {
    override fun validate(movement: Movement, game: Game) : GameResult {
        val turn = game.turn
        for ((square, piece) in game.board.getAllPiecesOfColor(turn)) {
            val moves = movesFinder(square, game)
            if (moves.isEmpty()) return FinishGameResult(turn.opposite())
            else continue
        }
        return NextMoveResult(game)
    }
}