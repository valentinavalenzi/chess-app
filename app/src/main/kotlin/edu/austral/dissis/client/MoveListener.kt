package edu.austral.dissis.client

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move

class MoveListener(val client: Client) : GameEventListener {
    override fun handleMove(move: Move) {
        client.sendNewMove(move)
    }
}