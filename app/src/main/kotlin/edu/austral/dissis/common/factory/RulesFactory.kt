package edu.austral.dissis.common.factory

import edu.austral.dissis.common.validators.AndValidator
import types.PieceType

interface RulesFactory {
    fun createRules(type: PieceType) : AndValidator
}