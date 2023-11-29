package edu.austral.dissis.common.factory

import edu.austral.dissis.common.Board

interface BoardFactory {
    fun createBoard(rowAmount: Int, colAmount: Int): Board
}