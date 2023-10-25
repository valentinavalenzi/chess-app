package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.Movement
import Piece
import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.types.ColorType

data class Game (val board: Board, val turn: ColorType) : GameValidatorContext{

    fun move(piece: Piece, movement: Movement) : Game {
        return if (piece.color == turn) {
            Game(board.move(movement), switchTurn())
        } else {
            this
        }
    }

    private fun switchTurn() : ColorType {
        return if (turn == ColorType.WHITE) {
            ColorType.BLACK
        } else
            ColorType.WHITE
    }

    override fun getCurrentGameState(): Game {
        return this
    }
}