package edu.austral.dissis.checkers


import edu.austral.dissis.checkers.adapter.CheckersAdapter
import edu.austral.dissis.checkers.classicCheckers.ClassicCheckersSetup
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    launch(ChessGameApplication::class.java)
}

class ChessGameApplication : Application() {
    private val classicCheckersSetup = ClassicCheckersSetup()
    private val gameEngine = CheckersAdapter(classicCheckersSetup.createClassicCheckers())
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = GameView(imageResolver)
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}