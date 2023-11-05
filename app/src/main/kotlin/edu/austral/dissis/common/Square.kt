package edu.austral.dissis.common

data class Square(val x: Int, val y: Int) {

    fun add(square: Square): Square {
        return Square(this.x + square.x, this.y + square.y)
    }
}