import types.ColorType
import types.PieceType
data class Piece(val color: ColorType, val type: PieceType, val movementQuantity: Int) {
    fun move() : Piece {
        return Piece(color, type, movementQuantity + 1)
    }
}
