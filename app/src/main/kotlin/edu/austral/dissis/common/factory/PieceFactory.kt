package edu.austral.dissis.common.factory

import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.types.ColorType
import types.PieceType

interface PieceFactory {
    fun createPiece(type: PieceType, color: ColorType, id: Int): Piece
}