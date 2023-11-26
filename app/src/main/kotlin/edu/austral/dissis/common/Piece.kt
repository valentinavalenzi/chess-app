package edu.austral.dissis.common

import edu.austral.dissis.common.types.ColorType
import types.PieceType
data class Piece(val color: ColorType, val type: PieceType, val movementQuantity: Int, val id: Int = 0) {
    fun move() : Piece {
        return Piece(color, type, movementQuantity + 1, id)
    }

    fun getID() : Int {
        return id
    }

    fun hasMoved() : Boolean {
        return movementQuantity > 0
    }

}
