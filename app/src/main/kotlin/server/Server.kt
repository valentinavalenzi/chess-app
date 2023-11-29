package server

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.Movement
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.chess.adapter.pieceAdapter
import edu.austral.dissis.chess.adapter.turnAdapter
import edu.austral.dissis.common.results.game.FinishGameResult
import edu.austral.dissis.common.results.game.NextMoveResult
import edu.austral.dissis.common.results.game.SameMoveResult
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

class Server (private var game: Game,
              private val serverBuilder: ServerBuilder = NettyServerBuilder.createDefault()) {
    private var server: Server
    init {
        server = build()
        server.start()
    }

    fun stop() { server.stop() }

    fun handleMove(move: Move) {
        val from = Square(move.from.column, move.from.row)
        val to = Square(move.to.column, move.to.row)
        val movement = Movement(from, to, game.board)
        when (val moveResult = game.move(movement)) {
            is NextMoveResult -> server.broadcast(Message("nextMoveResult", handleNextMove(moveResult)))
            is SameMoveResult -> server.broadcast(Message("sameMoveResult", InvalidMove(moveResult.reason)))
            is FinishGameResult -> server.broadcast(Message("finishGameResult", GameOver(turnAdapter(game.turn))))
        }
    }

    fun handleInit() {
        server.broadcast(Message("initResult", InitialState(
            BoardSize(game.board.rowAmount, game.board.columnAmount),
            pieceAdapter(game.board.availablePieces),
            turnAdapter(game.turn)
        )))
    }

    private fun build() : Server {
        return serverBuilder
            .withPort(8080)
            .addMessageListener(
                "move",
                object : TypeReference<Message<Move>> () {},
                MoveListener(this))
            .addMessageListener(
                "init",
                object : TypeReference<Message<Unit>> () {},
                InitListener(this)
            )
            .build()
    }

    private fun handleNextMove(result: NextMoveResult) : MoveResult {
        game = result.nextMoveGame
        val pieces: Map<Square, Piece> = game.board.availablePieces
        return NewGameState(pieceAdapter(pieces), turnAdapter(game.turn))
    }
}