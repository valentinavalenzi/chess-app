import edu.austral.dissis.chess.results.ValidResult
import edu.austral.dissis.chess.validators.CompositeValidator

data class Board (private val availablePieces: Map<Square, Piece>, val pieceRules: Map<Piece, CompositeValidator>,
                  val rowAmount: Int, val columnAmount: Int) {

    fun getPieceAt(position: Square): Piece? {
        return availablePieces[position]
    }

    fun setPieceAt(position: Square, piece: Piece): Board {
        val updatedPieces = availablePieces + (position to piece)
        return Board(updatedPieces, pieceRules, rowAmount, columnAmount)
    }

    fun move(piece: Piece, movement: Movement): Board {
        return if (pieceRules[piece]!!.validate(movement) is ValidResult) {
            val newPiece = piece.move()
            val updatedPieces = availablePieces - movement.from + (movement.to to newPiece)
            val newPieceRules = pieceRules - piece + (newPiece to pieceRules[piece]!!)
            Board(updatedPieces, newPieceRules, rowAmount, columnAmount)
        } else {
            Board(availablePieces, pieceRules, rowAmount, columnAmount)
        }
    }


}