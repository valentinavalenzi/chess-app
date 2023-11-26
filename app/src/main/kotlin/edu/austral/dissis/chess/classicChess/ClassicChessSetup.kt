package edu.austral.dissis.chess.classicChess

import edu.austral.dissis.chess.mover.CastlingMover
import edu.austral.dissis.chess.mover.CommonChessMover
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.amounts.QMoveNSquaresValidator
import edu.austral.dissis.chess.validators.enemies.IsEatingEnemyValidator
import edu.austral.dissis.chess.validators.other.CastlingValidator
import edu.austral.dissis.chess.validators.other.CheckMateValidator
import edu.austral.dissis.chess.validators.other.CheckValidator
import edu.austral.dissis.chess.validators.other.NotEatingKingValidator
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.mover.PromotionMover
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.OrValidator
import edu.austral.dissis.common.validators.moves.NullDestinationValidator
import edu.austral.dissis.common.validators.game.IsInsideBoardValidator
import edu.austral.dissis.common.validators.orientation.NotBackwardsValidator
import edu.austral.dissis.common.validators.moves.NotEatingSameColor
import edu.austral.dissis.common.validators.obstacles.DiagonalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.HorizontalObstacleValidator
import edu.austral.dissis.common.validators.obstacles.VerticalObstacleValidator
import edu.austral.dissis.common.validators.orientation.DiagonalValidator
import edu.austral.dissis.common.validators.orientation.HorizontalValidator
import edu.austral.dissis.common.validators.orientation.LValidator
import edu.austral.dissis.common.validators.orientation.VerticalValidator
import types.PieceType

class ClassicChessSetup {
    private val queenValidator = AndValidator(
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
    val movers = listOf(CastlingMover(), CommonChessMover(), PromotionMover(PieceType.QUEEN, queenValidator))

    val globalValidations = listOf(
        IsInsideBoardValidator(),
        NotEatingSameColor(),
        NotEatingKingValidator(),
        CheckValidator()
    )

    val blackRook1 = Piece(ColorType.BLACK, PieceType.ROOK, 0, 1)
    val blackKnight1 = Piece(ColorType.BLACK, PieceType.KNIGHT, 0, 2)
    val blackBishop1 = Piece(ColorType.BLACK, PieceType.BISHOP, 0, 3)
    val blackKing = Piece(ColorType.BLACK, PieceType.KING, 0, 4)
    val blackQueen = Piece(ColorType.BLACK, PieceType.QUEEN, 0, 5)
    val blackBishop2 = Piece(ColorType.BLACK, PieceType.BISHOP, 0, 6)
    val blackKnight2 = Piece(ColorType.BLACK, PieceType.KNIGHT, 0, 7)
    val blackRook2 = Piece(ColorType.BLACK, PieceType.ROOK, 0, 8)

    val blackPawn1 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 9)
    val blackPawn2 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 10)
    val blackPawn3 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 11)
    val blackPawn4 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 12)
    val blackPawn5 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 13)
    val blackPawn6 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 14)
    val blackPawn7 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 15)
    val blackPawn8 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 16)

    val whitePawn1 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 17)
    val whitePawn2 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 18)
    val whitePawn3 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 19)
    val whitePawn4 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 20)
    val whitePawn5 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 21)
    val whitePawn6 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 22)
    val whitePawn7 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 23)
    val whitePawn8 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 24)

    val whiteRook1 = Piece(ColorType.WHITE, PieceType.ROOK, 0, 25)
    val whiteKnight1 = Piece(ColorType.WHITE, PieceType.KNIGHT, 0, 26)
    val whiteBishop1 = Piece(ColorType.WHITE, PieceType.BISHOP, 0, 27)
    val whiteKing = Piece(ColorType.WHITE, PieceType.KING, 0, 28)
    val whiteQueen = Piece(ColorType.WHITE, PieceType.QUEEN, 0, 29)
    val whiteBishop2 = Piece(ColorType.WHITE, PieceType.BISHOP, 0, 30)
    val whiteKnight2 = Piece(ColorType.WHITE, PieceType.KNIGHT, 0, 31)
    val whiteRook2 = Piece(ColorType.WHITE, PieceType.ROOK, 0, 32)

    val classicPieces: Map<Square, Piece> = mapOf(
        Square(1, 1) to blackRook1,
        Square(2, 1) to blackKnight1,
        Square(3, 1) to blackBishop1,
        Square(4, 1) to blackQueen,
        Square(5, 1) to blackKing,
        Square(6, 1) to blackBishop2,
        Square(7, 1) to blackKnight2,
        Square(8, 1) to blackRook2,
        Square(1, 2) to blackPawn1,
        Square(2, 2) to blackPawn2,
        Square(3, 2) to blackPawn3,
        Square(4, 2) to blackPawn4,
        Square(5, 2) to blackPawn5,
        Square(6, 2) to blackPawn6,
        Square(7, 2) to blackPawn7,
        Square(8, 2) to blackPawn8,

        Square(1, 7) to whitePawn1,
        Square(2, 7) to whitePawn2,
        Square(3, 7) to whitePawn3,
        Square(4, 7) to whitePawn4,
        Square(5, 7) to whitePawn5,
        Square(6, 7) to whitePawn6,
        Square(7, 7) to whitePawn7,
        Square(8, 7) to whitePawn8,
        Square(1, 8) to whiteRook1,
        Square(2, 8) to whiteKnight1,
        Square(3, 8) to whiteBishop1,
        Square(4, 8) to whiteQueen,
        Square(5, 8) to whiteKing,
        Square(6, 8) to whiteBishop2,
        Square(7, 8) to whiteKnight2,
        Square(8, 8) to whiteRook2
    )

    fun createValidatorsMap(): Map<Piece, AndValidator> {
        var map = emptyMap<Piece, AndValidator>()
        val pieces = classicPieces.values.toList()
        for (piece in pieces) {
            when (piece.type) {

                PieceType.PAWN -> {
                    val or = OrValidator(
                        listOf(
                            AndValidator(
                                listOf(
                                    VerticalValidator(),
                                    VerticalObstacleValidator(),
                                    QMoveNSquaresValidator(0, 2, piece.type),
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
                    map = map + mapOf(piece to AndValidator(listOf(or)))
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
                    map = map + mapOf(piece to AndValidator(listOf(or)))
                }

                PieceType.KNIGHT -> {
                    map = map + mapOf(piece to AndValidator(listOf(LValidator())))
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
                    map = map + mapOf(piece to AndValidator(listOf(or)))
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
                    map = map + mapOf(piece to AndValidator(listOf(or)))
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
                    map = map + mapOf(piece to AndValidator(listOf(or)))
                }
            }
        }
        return map
    }

    private fun createClassicBoard(): Board {
        return Board(this.classicPieces, 8, 8)
    }

    fun createClassicGame(): Game {
        return Game(
            createClassicBoard(), ColorType.WHITE, globalValidations,
            createValidatorsMap(), listOf(CheckMateValidator()), movers
        )
    }
}