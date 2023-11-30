package edu.austral.dissis.server

import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class InitListener (val server: Server) : MessageListener<Unit> {
    override fun handleMessage(message: Message<Unit>) {
        server.handleInit()
    }
}