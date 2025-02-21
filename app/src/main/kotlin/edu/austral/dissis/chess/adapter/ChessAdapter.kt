package edu.austral.dissis.chess.adapter

import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Game
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.results.game.SameMoveResult
import types.PieceType

class ChessAdapter(var game: Game) : GameEngine {
    override fun applyMove(move: Move): MoveResult {
        val from = Square(move.from.column, move.from.row)
        val to = Square(move.to.column, move.to.row)

        return when (val moveResult = game.move(Movement(from, to, game.board))) {
            is NextMoveResult -> createNewGameState(moveResult)
            is SameMoveResult -> InvalidMove("Invalid move")
            is FinishGameResult -> GameOver(turnAdapter(game.turn))
            else -> InvalidMove("Invalid Move")
        }
    }

    private fun createNewGameState(result: NextMoveResult) : MoveResult {
        game = result.nextMoveGame
        val pieces: Map<Square, Piece> = game.board.availablePieces
        return NewGameState(pieceAdapter(pieces), turnAdapter(game.turn))
    }
    override fun init(): InitialState {
        return InitialState(
            BoardSize(game.board.columnAmount, game.board.rowAmount),
            pieceAdapter(game.board.availablePieces),
            turnAdapter(game.turn)
        )
    }

}

fun pieceAdapter(pieces: Map<Square, Piece>): List<ChessPiece> {
    return pieces.map { (square, piece) ->
        val color = when (piece.color) {
            ColorType.WHITE -> PlayerColor.WHITE
            ColorType.BLACK -> PlayerColor.BLACK
        }
        val type = when (piece.type) {
            PieceType.PAWN -> "pawn"
            PieceType.ROOK -> "rook"
            PieceType.KNIGHT -> "knight"
            PieceType.BISHOP -> "bishop"
            PieceType.QUEEN -> "queen"
            PieceType.KING -> "king"
            PieceType.CHANCELLOR -> "chancellor"
            PieceType.ARCHBISHOP -> "archbishop"
        }
        val position = Position(square.y, square.x)

        ChessPiece(piece.getID().toString(), color, position, type)
    }
}

fun turnAdapter(color: ColorType): PlayerColor {
    return when (color) {
        ColorType.WHITE -> PlayerColor.WHITE
        ColorType.BLACK -> PlayerColor.BLACK
    }
}
