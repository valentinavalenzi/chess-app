package edu.austral.dissis.common.factory

import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.types.ColorType
import types.PieceType

class EveryPieceFactory : PieceFactory {
    override fun createPiece(type: PieceType, color: ColorType, id: Int): Piece {
        return Piece(color, type, 0, id)
    }
}
