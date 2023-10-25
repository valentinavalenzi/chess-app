package edu.austral.dissis.chess.validators

import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.Movement
import Square
import edu.austral.dissis.chess.results.ValidResult

fun movesFinder(pieceFrom: Square, board: Board, gameValidators: List<Validator>): List<Movement> {
    var validMoves = emptyList<Movement>()
    //board is 0 index based
    for (i in 0 until board.rowAmount) {
        for (j in 0 until board.columnAmount) {
            val to = Square(i, j)
            val move = Movement(pieceFrom, to, board)
            val pieceRules = board.pieceRules
            if (gameValidators.all { it.validate(move) is ValidResult } && pieceRules[board.getPieceAt(pieceFrom)]!!.validate(move) is ValidResult) {
                validMoves = validMoves.plus(move)
            }
        }
    }
    return validMoves
}