package edu.austral.dissis.chess.classicChess

import edu.austral.dissis.chess.mover.CastlingMover
import edu.austral.dissis.chess.mover.CommonChessMover
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.amounts.QMoveNSquaresValidator
import edu.austral.dissis.chess.validators.enemies.IsEatingEnemyValidator
import edu.austral.dissis.chess.validators.other.CastlingValidator
import edu.austral.dissis.chess.validators.other.CheckMateValidator
import edu.austral.dissis.chess.validators.other.CheckValidator
import edu.austral.dissis.chess.validators.other.NotEatingKingValidator
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.factory.GameSetup
import edu.austral.dissis.common.mover.PromotionMover
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.OrValidator
import edu.austral.dissis.common.validators.moves.NullDestinationValidator
import edu.austral.dissis.common.validators.game.IsInsideBoardValidator
import edu.austral.dissis.common.validators.orientation.NotBackwardsValidator
import edu.austral.dissis.common.validators.moves.NotEatingSameColor
import edu.austral.dissis.common.validators.obstacles.DiagonalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.HorizontalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.VerticalObstacleValidator
import edu.austral.dissis.common.validators.orientation.DiagonalValidator
import edu.austral.dissis.common.validators.orientation.HorizontalValidator
import edu.austral.dissis.common.validators.orientation.LValidator
import edu.austral.dissis.common.validators.orientation.VerticalValidator
import types.PieceType

class ClassicChessSetup (val classicChessBoardFactory: ClassicChessBoardFactory,
                         val classicChessPieceRules: ClassicChessPieceRules) : GameSetup {


    override fun createGame(): Game {

        val board = classicChessBoardFactory.createBoard(8, 8)
        val globalValidations = listOf(IsInsideBoardValidator(), NotEatingSameColor(),
                                        NotEatingKingValidator(), CheckValidator())
        val pieceRules = assignRules(board)
        val winningConditions = listOf(CheckMateValidator())
        val movers = listOf(CastlingMover(), CommonChessMover(),
                    PromotionMover(PieceType.QUEEN, classicChessPieceRules.createRules(PieceType.QUEEN)))

        return Game(board, ColorType.WHITE, globalValidations, pieceRules, winningConditions, movers)
    }

    override fun assignRules(board: Board): Map<Piece, AndValidator> {
        val validatorsMap = mutableMapOf<Piece, AndValidator>()
        val pieces = board.availablePieces.values.toList()

        for (piece in pieces) {
            val rules = classicChessPieceRules.createRules(piece.type)
            validatorsMap[piece] = rules
        }
        return validatorsMap
    }
}