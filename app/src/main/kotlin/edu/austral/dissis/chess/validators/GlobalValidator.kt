package edu.austral.dissis.chess.validators

import Board
import edu.austral.dissis.chess.Game
import Movement
import edu.austral.dissis.chess.results.InvalidResult
import edu.austral.dissis.chess.results.Result
import edu.austral.dissis.chess.results.ValidResult
import types.PieceType

class GlobalValidator (val game: Game) : Validator {

    override fun validate(movement: Movement): Result {
        return if (isInsideBoard(movement, game.board) is ValidResult &&
            isTurn(movement, game.board) is ValidResult &&
            isNotEatingSameColor(movement, game.board) is ValidResult &&
            isNotEatingKing(movement, game.board) is ValidResult) ValidResult()
        else InvalidResult()
    }

    private fun isInsideBoard(movement: Movement, board: Board) : Result {
        return if (movement.from.x >= 0 && movement.from.x < board.rowAmount
                && movement.from.y >= 0 && movement.from.y < board.columnAmount
                && movement.to.x >= 0 && movement.to.x < board.rowAmount
                && movement.to.y >= 0 && movement.to.y < board.columnAmount) ValidResult()
        else InvalidResult()
    }

    private fun isTurn(movement: Movement, board: Board) : Result {
        val piece = board.getPieceAt(movement.from)
        return if (piece?.color == game.turn) ValidResult()
        else InvalidResult()
    }

    private fun isNotEatingSameColor(movement: Movement, board: Board) : Result {
        val pieceFrom = board.getPieceAt(movement.from)
        val pieceTo = board.getPieceAt(movement.to)
        return if (pieceFrom?.color != pieceTo?.color) ValidResult()
        else InvalidResult()
    }

    private fun isNotEatingKing(movement: Movement, board: Board) : Result {
        val piece = board.getPieceAt(movement.to)
        return if (piece?.type != PieceType.KING) ValidResult()
        else InvalidResult()
    }

}