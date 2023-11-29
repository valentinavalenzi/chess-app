package edu.austral.dissis.common

import edu.austral.dissis.common.factory.PieceFactory
import edu.austral.dissis.common.types.ColorType
import types.PieceType

class PieceFactory : PieceFactory {
    override fun createPiece(type: PieceType, color: ColorType, id: Int): Piece {
        return Piece(color, type, 0, id)
    }
}
