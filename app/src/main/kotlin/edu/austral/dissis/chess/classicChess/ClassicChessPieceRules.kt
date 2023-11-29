package edu.austral.dissis.chess.classicChess

import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.amounts.QMoveNSquaresValidator
import edu.austral.dissis.chess.validators.enemies.IsEatingEnemyValidator
import edu.austral.dissis.chess.validators.other.CastlingValidator
import edu.austral.dissis.common.factory.RulesFactory
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.OrValidator
import edu.austral.dissis.common.validators.moves.NullDestinationValidator
import edu.austral.dissis.common.validators.obstacles.DiagonalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.HorizontalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.VerticalObstacleValidator
import edu.austral.dissis.common.validators.orientation.*
import types.PieceType

class ClassicChessPieceRules : RulesFactory {
    override fun createRules(type: PieceType): AndValidator {
        return when (type) {
            PieceType.PAWN -> createPawnRules()
            PieceType.KNIGHT -> createKnightRules()
            PieceType.BISHOP -> createBishopRules()
            PieceType.ROOK -> createRookRules()
            PieceType.QUEEN -> createQueenRules()
            PieceType.KING -> createKingRules()
            else -> throw IllegalArgumentException("Invalid piece type")
        }
    }

    fun createPawnRules(): AndValidator {
        return AndValidator(
            listOf(
                OrValidator(
                    listOf(
                        AndValidator(
                            listOf(
                                VerticalValidator(),
                                VerticalObstacleValidator(),
                                QMoveNSquaresValidator(0, 2, PieceType.PAWN),
                                NotBackwardsValidator(),
                                NullDestinationValidator(),
                            )
                        ),
                        AndValidator(
                            listOf(
                                VerticalValidator(),
                                VerticalObstacleValidator(),
                                AmountValidator(1),
                                NotBackwardsValidator(),
                                NullDestinationValidator(),
                            )
                        ),
                        AndValidator(
                            listOf(
                                DiagonalValidator(),
                                AmountValidator(1),
                                IsEatingEnemyValidator(),
                                NotBackwardsValidator()
                            )
                        )
                    )
                )
            )
        )
    }

    fun createKnightRules(): AndValidator {
        return AndValidator(listOf(LValidator()))
    }

    fun createBishopRules(): AndValidator {
        return AndValidator(
            listOf(
                DiagonalValidator(),
                DiagonalObstacleValidator()
            )
        )
    }

    fun createRookRules(): AndValidator {
        return AndValidator(
            listOf(
                OrValidator(
                    listOf(
                        AndValidator(
                            listOf(
                                VerticalValidator(),
                                VerticalObstacleValidator()
                            )
                        ),
                        AndValidator(
                            listOf(
                                HorizontalValidator(),
                                HorizontalObstacleValidator()
                            )
                        )
                    )
                )
            )
        )
    }

    fun createQueenRules() : AndValidator {
        return AndValidator(
            listOf(
                OrValidator(
                    listOf(
                        AndValidator(
                            listOf(
                                VerticalValidator(),
                                VerticalObstacleValidator()
                            )
                        ),
                        AndValidator(
                            listOf(
                                HorizontalValidator(),
                                HorizontalObstacleValidator()
                            )
                        ),
                        AndValidator(
                            listOf(
                                DiagonalValidator(),
                                DiagonalObstacleValidator()
                            )
                        )
                    )
                )
            )
        )
    }

    fun createKingRules(): AndValidator {
        return AndValidator(
            listOf(
                OrValidator(
                    listOf(
                        AndValidator(
                            listOf(
                                VerticalValidator(),
                                VerticalObstacleValidator(),
                                AmountValidator(1)
                            )
                        ),
                        AndValidator(
                            listOf(
                                HorizontalValidator(),
                                HorizontalObstacleValidator(),
                                AmountValidator(1)
                            )
                        ),
                        AndValidator(
                            listOf(
                                DiagonalValidator(),
                                DiagonalObstacleValidator(),
                                AmountValidator(1)
                            )
                        ),
                        AndValidator(
                            listOf(
                                HorizontalValidator(),
                                AmountValidator(2),
                                CastlingValidator()
                            )
                        ),
                    )
                )
            )
        )
    }
}