package edu.austral.dissis.chess.game

interface GameValidatorContext {
    fun getCurrentGameState(): Game
}