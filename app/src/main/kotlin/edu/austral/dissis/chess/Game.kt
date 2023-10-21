package edu.austral.dissis.chess

import Board
import Movement
import Piece
import types.ColorType

data class Game (val board: Board, val turn: ColorType){

    fun move(piece: Piece, movement: Movement) : Game {
        return Game(board.move(piece, movement), switchTurn())
    }

    private fun switchTurn() : ColorType {
        return if (turn == ColorType.WHITE) {
            ColorType.BLACK
        } else
            ColorType.WHITE
    }
}