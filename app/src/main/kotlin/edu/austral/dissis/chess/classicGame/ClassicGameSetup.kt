package edu.austral.dissis.chess.classicGame

import Piece
import Square
import edu.austral.dissis.chess.Board
import edu.austral.dissis.chess.validators.AndValidator
import edu.austral.dissis.chess.validators.CompositeValidator
import edu.austral.dissis.chess.validators.OrValidator
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.amounts.QMoveNSquaresValidator
import edu.austral.dissis.chess.validators.enemies.IsEatingEnemyValidator
import edu.austral.dissis.chess.validators.enemies.IsNotEatingEnemyValidator
import edu.austral.dissis.chess.validators.obstacles.DiagonalObstacleValidator
import edu.austral.dissis.chess.validators.obstacles.HorizontalObstacleValidator
import edu.austral.dissis.chess.validators.obstacles.VerticalObstacleValidator
import edu.austral.dissis.chess.validators.orientation.DiagonalValidator
import edu.austral.dissis.chess.validators.orientation.HorizontalValidator
import edu.austral.dissis.chess.validators.orientation.LValidator
import edu.austral.dissis.chess.validators.orientation.VerticalValidator
import edu.austral.dissis.chess.validators.other.*
import edu.austral.dissis.chess.types.ColorType
import types.PieceType

class ClassicGameSetup {
    fun createClassicPieces(): Map<Square, Piece> {
        return mapOf(
            Square(0, 0) to Piece(ColorType.BLACK, PieceType.ROOK, 0),
            Square(1, 0) to Piece(ColorType.BLACK, PieceType.KNIGHT, 0),
            Square(2, 0) to Piece(ColorType.BLACK, PieceType.BISHOP, 0),
            Square(3, 0) to Piece(ColorType.BLACK, PieceType.KING, 0),
            Square(4, 0) to Piece(ColorType.BLACK, PieceType.QUEEN, 0),
            Square(5, 0) to Piece(ColorType.BLACK, PieceType.BISHOP, 0),
            Square(6, 0) to Piece(ColorType.BLACK, PieceType.KNIGHT, 0),
            Square(7, 0) to Piece(ColorType.BLACK, PieceType.ROOK, 0),
            Square(0, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
            Square(1, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
            Square(2, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
            Square(3, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
            Square(4, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
            Square(5, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
            Square(6, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
            Square(7, 1) to Piece(ColorType.BLACK, PieceType.PAWN, 0),

            Square(0, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(1, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(2, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(3, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(4, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(5, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(6, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(7, 6) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
            Square(0, 7) to Piece(ColorType.WHITE, PieceType.ROOK, 0),
            Square(1, 7) to Piece(ColorType.WHITE, PieceType.KNIGHT, 0),
            Square(2, 7) to Piece(ColorType.WHITE, PieceType.BISHOP, 0),
            Square(3, 7) to Piece(ColorType.WHITE, PieceType.KING, 0),
            Square(4, 7) to Piece(ColorType.WHITE, PieceType.QUEEN, 0),
            Square(5, 7) to Piece(ColorType.WHITE, PieceType.BISHOP, 0),
            Square(6, 7) to Piece(ColorType.WHITE, PieceType.KNIGHT, 0),
            Square(7, 7) to Piece(ColorType.WHITE, PieceType.ROOK, 0),
        )
    }

    fun createValidatorsMap(firstPieces: Map<Square, Piece>): Map<Piece, CompositeValidator> {
        var map = emptyMap<Piece, CompositeValidator>()
        val generalValidators = listOf(
            IsInsideBoardValidator(),
            NotEatingSameColor(),
            //checkValidator()
        )
        val pieces = firstPieces.values.toList()
        for (piece in pieces) {
            when (piece.type) {
                PieceType.PAWN -> {
                    if (piece.color == ColorType.WHITE) {
                        val or = OrValidator(
                            listOf(
                                AndValidator(
                                    listOf(
                                        VerticalValidator(),
                                        VerticalObstacleValidator(),
                                        QMoveNSquaresValidator(0, 2, piece),
                                        NotBackwardsValidator(ColorType.WHITE),
                                        IsNotEatingEnemyValidator(),
                                    )
                                ),
                                AndValidator(
                                    listOf(
                                        VerticalValidator(),
                                        VerticalObstacleValidator(),
                                        AmountValidator(1),
                                        NotBackwardsValidator(ColorType.WHITE),
                                        IsNotEatingEnemyValidator(),
                                    )
                                ),
                                AndValidator(
                                    listOf(
                                        DiagonalValidator(),
                                        AmountValidator(1),
                                        IsEatingEnemyValidator(),
                                        NotBackwardsValidator(ColorType.WHITE)
                                    )
                                )
                            )
                        )
                        map = map + mapOf(piece to CompositeValidator(generalValidators + or))
                    } else {
                        val or = OrValidator(
                            listOf(
                                AndValidator(
                                    listOf(
                                        VerticalValidator(),
                                        VerticalObstacleValidator(),
                                        QMoveNSquaresValidator(0, 2, piece),
                                        NotBackwardsValidator(ColorType.BLACK),
                                        IsNotEatingEnemyValidator(),
                                    )
                                ),
                                AndValidator(
                                    listOf(
                                        VerticalValidator(),
                                        VerticalObstacleValidator(),
                                        AmountValidator(1),
                                        NotBackwardsValidator(ColorType.BLACK),
                                        IsNotEatingEnemyValidator(),
                                    )
                                ),
                                AndValidator(
                                    listOf(
                                        DiagonalValidator(),
                                        AmountValidator(1),
                                        IsEatingEnemyValidator(),
                                        NotBackwardsValidator(ColorType.BLACK)
                                    )
                                )
                            )
                        )
                        map = map + mapOf(piece to CompositeValidator(generalValidators + or))
                    }
                }

                PieceType.ROOK -> {
                    val or = OrValidator(
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
                    map = map + mapOf(piece to CompositeValidator(generalValidators + or))
                }

                PieceType.KNIGHT -> {
                    map = map + mapOf(piece to CompositeValidator(generalValidators + LValidator()))
                }

                PieceType.BISHOP -> {
                    val or = OrValidator(
                        listOf(
                            AndValidator(
                                listOf(
                                    DiagonalValidator(),
                                    DiagonalObstacleValidator()
                                )
                            )
                        )
                    )
                    map = map + mapOf(piece to CompositeValidator(generalValidators + or))
                }

                PieceType.QUEEN -> {
                    val or = OrValidator(
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
                    map = map + mapOf(piece to CompositeValidator(generalValidators + or))
                }

                PieceType.KING -> {
                    val or = OrValidator(
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
                            )
                        )
                    )
                    map = map + mapOf(piece to CompositeValidator(generalValidators + or))
                }
            }
        }
        return map
    }

    fun createClassicBoard(): Board {
        return Board(createClassicPieces(), createValidatorsMap((createClassicPieces())), 8, 8)
    }
}