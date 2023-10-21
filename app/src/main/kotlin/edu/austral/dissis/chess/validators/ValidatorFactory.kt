package edu.austral.dissis.chess.validators

import types.PieceType

interface ValidatorFactory {
    fun create(type: PieceType): Validator
}