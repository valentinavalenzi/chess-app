package edu.austral.dissis.checkers.classicCheckers

import edu.austral.dissis.checkers.validators.IsEatingValidator
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.common.factory.RulesFactory
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.OrValidator
import edu.austral.dissis.common.validators.moves.NullDestinationValidator
import edu.austral.dissis.common.validators.orientation.NotBackwardsValidator
import types.PieceType

class ClassicCheckersPieceRules : RulesFactory {
    override fun createRules(type: PieceType): AndValidator {
        return when (type) {
            PieceType.PAWN -> AndValidator(
                listOf(
                    OrValidator(
                        listOf(
                            AndValidator(
                                listOf(
                                    NotBackwardsValidator(),
                                    NullDestinationValidator(),
                                    AmountValidator(1)
                                )
                            ),
                            AndValidator(
                                listOf(
                                    NotBackwardsValidator(),
                                    IsEatingValidator(),
                                    AmountValidator(2)
                                )
                            )
                        )
                    )
                )
            )
            PieceType.KING -> AndValidator(listOf())
            else -> AndValidator(listOf())
        }
    }
}