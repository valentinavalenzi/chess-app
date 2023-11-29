package edu.austral.dissis.chess.capablancaChess

import edu.austral.dissis.chess.classicChess.ClassicChessPieceRules
import edu.austral.dissis.common.factory.RulesFactory
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.OrValidator
import edu.austral.dissis.common.validators.obstacles.DiagonalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.HorizontalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.VerticalObstacleValidator
import edu.austral.dissis.common.validators.orientation.DiagonalValidator
import edu.austral.dissis.common.validators.orientation.HorizontalValidator
import edu.austral.dissis.common.validators.orientation.LValidator
import edu.austral.dissis.common.validators.orientation.VerticalValidator
import types.PieceType

class CapablancaPieceRules : RulesFactory {

    private val classicChessPieceRules = ClassicChessPieceRules()
    override fun createRules(type: PieceType): AndValidator {
        return when (type) {
            PieceType.PAWN -> classicChessPieceRules.createPawnRules()
            PieceType.KNIGHT -> classicChessPieceRules.createKnightRules()
            PieceType.BISHOP -> classicChessPieceRules.createBishopRules()
            PieceType.ROOK -> classicChessPieceRules.createRookRules()
            PieceType.QUEEN -> classicChessPieceRules.createQueenRules()
            PieceType.KING -> classicChessPieceRules.createKingRules()
            PieceType.ARCHBISHOP -> createArchbishopRules()
            PieceType.CHANCELLOR -> createChancellorRules()
            else -> throw IllegalArgumentException("Invalid piece type")
        }
    }

    fun createArchbishopRules(): AndValidator {
        return AndValidator(
            listOf(
                OrValidator(
                    listOf(
                        AndValidator(
                            listOf(
                                DiagonalValidator(),
                                DiagonalObstacleValidator()
                            )
                        ),
                        AndValidator(
                            listOf(
                                LValidator()
                            )
                        )
                    )
                )
            )
        )
    }

    fun createChancellorRules(): AndValidator {
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
                                LValidator()
                            )
                        )
                    )
                )
            )
        )
    }
}