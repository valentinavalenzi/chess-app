package edu.austral.dissis.common.factory

import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.validators.AndValidator

interface GameSetup {

    fun createGame() : Game
    fun assignRules(board: Board) : Map<Piece, AndValidator>
}