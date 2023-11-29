package server

import edu.austral.dissis.chess.classicChess.ClassicChessBoardFactory
import edu.austral.dissis.chess.classicChess.ClassicChessPieceRules
import edu.austral.dissis.chess.classicChess.ClassicChessSetup


fun main() {
    val chessSetup = ClassicChessSetup(ClassicChessBoardFactory(), ClassicChessPieceRules())
    Server(chessSetup.createGame())
}