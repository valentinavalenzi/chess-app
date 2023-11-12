package server

import edu.austral.dissis.chess.classicChess.ClassicChessSetup


fun main() {
    val chessSetup = ClassicChessSetup()
    Server(chessSetup.createClassicGame())
}