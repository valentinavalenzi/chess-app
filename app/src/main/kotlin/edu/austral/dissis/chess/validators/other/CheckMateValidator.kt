package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.results.Result
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.chess.validators.*
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.validators.Validator

class CheckMateValidator () : Validator {

    private val checkValidator = CheckValidator()
    override fun validate(movement: Movement, game: Game): Result {
        val board = game.board
        val oppositeColor = game.turn.opposite()
        val enemyPieces = board.getAllPiecesOfColor(oppositeColor)
        for ((square) in enemyPieces) {
            val possibleMovements = movesFinder(square, game)
            for (possibleMovement in possibleMovements) {
                if (checkValidator.isInCheck(possibleMovement, game)) return InvalidResult("CheckMate!")
            }
        }
        return ValidResult()
    }

}
