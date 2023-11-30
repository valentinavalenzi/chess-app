package edu.austral.dissis.checkers


import edu.austral.dissis.checkers.adapter.CheckersAdapter
import edu.austral.dissis.checkers.classicCheckers.ClassicCheckersBoardFactory
import edu.austral.dissis.checkers.classicCheckers.ClassicCheckersPieceRules
import edu.austral.dissis.checkers.classicCheckers.ClassicCheckersSetup
import edu.austral.dissis.chess.classicChess.ClassicChessPieceRules
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.chess.gui.createGameViewFrom
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    launch(ChessGameApplication::class.java)
}

class ChessGameApplication : Application() {
    private val classicCheckersSetup = ClassicCheckersSetup(ClassicCheckersBoardFactory(), ClassicCheckersPieceRules())
    private val gameEngine = CheckersAdapter(classicCheckersSetup.createGame())
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Checkers"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = createGameViewFrom(gameEngine, imageResolver)
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}