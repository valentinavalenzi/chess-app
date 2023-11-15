package edu.austral.dissis.client

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import java.net.InetSocketAddress

class Client() {
    private lateinit var client: Client
    private lateinit var gameView: GameView
    fun start(newGameView: GameView) {
        gameView = newGameView
        client = build()
        client.connect()
        client.send(Message("init", Unit))
        gameView.addListener(MovementListener(this))
    }

    fun acceptMove(message: Message<MoveResult>) {
        gameView.handleMoveResult(message.payload)
    }

    fun handleInit(message: Message<InitialState>) {
        gameView.handleInitialState(message.payload)
    }

    fun handleNewGameState( message: Message<NewGameState>) {
        gameView.handleMoveResult(message.payload)
    }

    fun handleInvalidMove(message: Message<InvalidMove>) {
        gameView.handleMoveResult(message.payload)
    }

    fun handleFinishGame(message: Message<GameOver>) {
        gameView.handleMoveResult(message.payload)
    }

    fun sendNewMove(move: Move) {
        client.send(Message("move", move))
    }

    fun disconnect() {
        client.closeConnection()
    }

    private fun build() : Client {
        return NettyClientBuilder.createDefault()
            .withAddress(InetSocketAddress(8080))
            .addMessageListener(
                "initResult",
                object : TypeReference<Message<InitialState>>() {},
                InitListener(this)
            )
            .addMessageListener(
                "nextMoveResult",
                object : TypeReference<Message<NewGameState>> () {},
                NextMoveResultListener(this)
            )
            .addMessageListener(
                "sameMoveResult",
                object : TypeReference<Message<InvalidMove>> () {},
                SameMoveResultListener(this)
            )
            .addMessageListener(
                "finishGameResult",
                object : TypeReference<Message<GameOver>> () {},
                FinishGameResultListener(this)
            )
            .build()
    }

}