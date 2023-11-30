package edu.austral.dissis.checkers.classicCheckers

import edu.austral.dissis.checkers.mover.CaptureMover
import edu.austral.dissis.checkers.mover.CommonCheckersMover
import edu.austral.dissis.common.mover.PromotionMover
import edu.austral.dissis.checkers.validators.IsEatingValidator
import edu.austral.dissis.checkers.validators.MustCaptureValidator
import edu.austral.dissis.chess.classicChess.ClassicChessBoardFactory
import edu.austral.dissis.chess.classicChess.ClassicChessPieceRules
import edu.austral.dissis.common.validators.game.NoEnemyLeftValidator
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.factory.GameSetup
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.OrValidator
import edu.austral.dissis.common.validators.moves.NullDestinationValidator
import edu.austral.dissis.common.validators.game.IsInsideBoardValidator
import edu.austral.dissis.common.validators.orientation.NotBackwardsValidator
import edu.austral.dissis.common.validators.moves.NotEatingSameColor
import edu.austral.dissis.common.validators.orientation.DiagonalValidator
import types.PieceType

class ClassicCheckersSetup(val classicCheckersBoardFactory: ClassicCheckersBoardFactory,
                            val classicCheckersPieceRules: ClassicCheckersPieceRules) : GameSetup {

    override fun createGame(): Game {

        val board = classicCheckersBoardFactory.createBoard(8, 8)
        val globalValidators = listOf(NotEatingSameColor(), IsInsideBoardValidator(),
                                        DiagonalValidator(), MustCaptureValidator())
        val movers = listOf(CommonCheckersMover(), CaptureMover(),
                        PromotionMover(PieceType.KING, AndValidator(listOf())))

        return Game(board, ColorType.WHITE, globalValidators, assignRules(board),
                        listOf(NoEnemyLeftValidator()), movers)
    }

    override fun assignRules(board: Board): Map<Piece, AndValidator> {
        val validatorsMap = mutableMapOf<Piece, AndValidator>()
        val pieces = board.availablePieces.values.toList()

        for (piece in pieces) {
            val rules = classicCheckersPieceRules.createRules(piece.type)
            validatorsMap[piece] = rules
        }
        return validatorsMap
    }
}