package edu.austral.dissis.chess.types

enum class ColorType {
    WHITE,
    BLACK;

    fun opposite(): ColorType {
        return when (this) {
            WHITE -> BLACK
            BLACK -> WHITE
        }
    }
}