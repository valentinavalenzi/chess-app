package edu.austral.dissis.chess.validators.other

import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.results.InvalidResult
import edu.austral.dissis.common.results.ValidResult
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.results.game.GameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.utils.availableMovesFinder
import edu.austral.dissis.common.utils.getKingPosition
import edu.austral.dissis.common.utils.movesFinder
import edu.austral.dissis.common.validators.EndGameValidator

class CheckMateValidator : EndGameValidator {

    private val checkValidator = CheckValidator()

    override fun validate(movement: Movement, game: Game): GameResult {
        val oppositeColor = game.turn
        val kingPosition = getKingPosition(game.board, oppositeColor) ?: throw NoSuchElementException("No king found")

        if (isCheckMate(game, oppositeColor, kingPosition, movement.to)) return FinishGameResult(game.turn)

        return NextMoveResult(game)
    }

    private fun isCheckMate(game: Game, oppositeColor: ColorType, kingPosition: Square, attackerSquare: Square): Boolean {
        val possibleKingMoves = movesFinder(kingPosition, game)
//        val kingInCheck = checkValidator.isCheck(game.board, oppositeColor, game)
        val kingInCheck = checkValidator.validate(Movement(kingPosition, kingPosition, game.board), game) is InvalidResult

        if (!kingInCheck) return false // King is not in check

        // Check if the king has no available moves
        val noEscapeMoves = possibleKingMoves.all { move ->
//            val hypotheticalGame = simulateMove(game, move)
            val isStillInCheck = checkValidator.validate(move, game) is InvalidResult
            isStillInCheck
        }

        // Check if any friendly piece can block the check
        val friendlyPieces = game.board.getAllPiecesOfColor(game.turn) - kingPosition
        var canBlockCheck = false

        for ((square, piece) in friendlyPieces) {
            val possiblePieceMoves = availableMovesFinder(square, game)

            for (move in possiblePieceMoves) {
//                val hypotheticalGame = simulateMove(game, move)
                val isCheckAvoided = checkValidator.validate(move, game) is ValidResult

                if (isCheckAvoided) {
                    canBlockCheck = true
                    break
                }
            }

            if (canBlockCheck) {
                break
            }

        }

        // Check if any friendly piece can capture the attacking piece
        val canCaptureAttacker = friendlyPieces.any { (square, piece) ->
//            val hypotheticalGame = simulateMove(game, Movement(square, attackerSquare, game.board))
            checkValidator.validate(Movement(square, attackerSquare, game.board), game) is InvalidResult
        }

        return noEscapeMoves && !canCaptureAttacker && !canBlockCheck
    }


    private fun simulateMove(game: Game, movement: Movement): Game {
        val newBoard = game.board.move(movement)
        return game.copy(board = newBoard, turn = game.turn.opposite())
    }

    private fun findAttackerSquare(game: Game, kingPosition: Square, oppositeColor: ColorType): Square {
        val enemyPieces = game.board.getAllPiecesOfColor(oppositeColor)

        for ((enemySquare, enemyPiece) in enemyPieces) {
            val possibleEnemyMoves = movesFinder(enemySquare, game)
            if (possibleEnemyMoves.any { it.to == kingPosition }) {
                return enemySquare // The square from which an enemy piece is attacking the king
            }
        }
        return Square(0, 0) // This should never happen
    }

}