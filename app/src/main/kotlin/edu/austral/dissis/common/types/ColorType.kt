package edu.austral.dissis.common.types

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