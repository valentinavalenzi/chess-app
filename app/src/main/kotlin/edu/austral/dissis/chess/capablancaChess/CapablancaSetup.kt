package edu.austral.dissis.chess.capablancaChess

import edu.austral.dissis.chess.mover.CastlingMover
import edu.austral.dissis.chess.mover.CommonChessMover
import edu.austral.dissis.chess.validators.other.CheckValidator
import edu.austral.dissis.chess.validators.other.NotEatingKingValidator
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.factory.BoardFactory
import edu.austral.dissis.common.factory.GameSetup
import edu.austral.dissis.common.mover.PromotionMover
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.game.IsInsideBoardValidator
import edu.austral.dissis.common.validators.game.NoEnemyLeftValidator
import edu.austral.dissis.common.validators.moves.NotEatingSameColor
import types.PieceType

class CapablancaSetup (val capablancaBoardFactory : BoardFactory, val capablancaPieceRules : CapablancaPieceRules) : GameSetup {

    override fun createGame() : Game {

        val board = capablancaBoardFactory.createBoard(8, 10)
        val generalRules = listOf(IsInsideBoardValidator(), NotEatingSameColor(), NotEatingKingValidator(), CheckValidator())
        val pieceRules = assignRules(board)
        val winningConditions = listOf(NoEnemyLeftValidator())
        val movers = listOf(CastlingMover(), CommonChessMover(),
            PromotionMover(PieceType.QUEEN, capablancaPieceRules.createRules(PieceType.QUEEN)))

        return Game(board, ColorType.WHITE, generalRules, pieceRules, winningConditions, movers)
    }

    override fun assignRules(board: Board) : Map<Piece, AndValidator> {
        val validatorsMap = mutableMapOf<Piece, AndValidator>()
        val pieces = board.availablePieces.values.toList()

        for (piece in pieces) {
            val rules = capablancaPieceRules.createRules(piece.type)
            validatorsMap[piece] = rules
        }
        return validatorsMap
    }
}