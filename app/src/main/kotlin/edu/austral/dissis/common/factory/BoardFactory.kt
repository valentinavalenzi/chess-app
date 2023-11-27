package edu.austral.dissis.common.factory

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square

interface BoardFactory {
    fun createBoard(pieces: Map<Square, Piece>, rowAmount: Int, colAmount: Int): Board
}