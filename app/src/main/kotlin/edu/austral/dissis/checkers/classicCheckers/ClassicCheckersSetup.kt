package edu.austral.dissis.checkers.classicCheckers

import edu.austral.dissis.checkers.mover.CaptureMover
import edu.austral.dissis.checkers.mover.CommonCheckersMover
import edu.austral.dissis.common.mover.PromotionMover
import edu.austral.dissis.checkers.validators.IsEatingValidator
import edu.austral.dissis.checkers.validators.MustCaptureValidator
import edu.austral.dissis.common.validators.game.NoEnemyLeftValidator
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.common.Board
import edu.austral.dissis.common.Piece
import edu.austral.dissis.common.Square
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.types.ColorType
import edu.austral.dissis.common.validators.AndValidator
import edu.austral.dissis.common.validators.OrValidator
import edu.austral.dissis.common.validators.moves.NullDestinationValidator
import edu.austral.dissis.common.validators.game.IsInsideBoardValidator
import edu.austral.dissis.common.validators.orientation.NotBackwardsValidator
import edu.austral.dissis.common.validators.moves.NotEatingSameColor
import edu.austral.dissis.common.validators.orientation.DiagonalValidator
import types.PieceType

class ClassicCheckersSetup {

    val movers = listOf(CommonCheckersMover(), CaptureMover(), PromotionMover(PieceType.KING, AndValidator(listOf())))
    val globalValidators = listOf(
        NotEatingSameColor(), IsInsideBoardValidator(),
                                    DiagonalValidator(), MustCaptureValidator())

    val blackPawn1 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 1);
    val blackPawn2 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 2);
    val blackPawn3 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 3);
    val blackPawn4 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 4);
    val blackPawn5 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 5);
    val blackPawn6 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 6);
    val blackPawn7 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 7);
    val blackPawn8 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 8);
    val blackPawn9 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 9);
    val blackPawn10 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 10);
    val blackPawn11 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 11);
    val blackPawn12 = Piece(ColorType.BLACK, PieceType.PAWN, 0, 12);

    val whitePawn1 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 13);
    val whitePawn2 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 14);
    val whitePawn3 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 15);
    val whitePawn4 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 16);
    val whitePawn5 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 17);
    val whitePawn6 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 18);
    val whitePawn7 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 19);
    val whitePawn8 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 20);
    val whitePawn9 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 21);
    val whitePawn10 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 22);
    val whitePawn11 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 23);
    val whitePawn12 = Piece(ColorType.WHITE, PieceType.PAWN, 0, 24);

    val classicPieces: Map<Square, Piece> = mapOf(
        Square(2, 1) to blackPawn1,
        Square(4, 1) to blackPawn2,
        Square(6, 1) to blackPawn3,
        Square(8, 1) to blackPawn4,
        Square(1, 2) to blackPawn5,
        Square(3, 2) to blackPawn6,
        Square(5, 2) to blackPawn7,
        Square(7, 2) to blackPawn8,
        Square(2, 3) to blackPawn9,
        Square(4, 3) to blackPawn10,
        Square(6, 3) to blackPawn11,
        Square(8, 3) to blackPawn12,
        Square(1, 6) to whitePawn1,
        Square(3, 6) to whitePawn2,
        Square(5, 6) to whitePawn3,
        Square(7, 6) to whitePawn4,
        Square(2, 7) to whitePawn5,
        Square(4, 7) to whitePawn6,
        Square(6, 7) to whitePawn7,
        Square(8, 7) to whitePawn8,
        Square(1, 8) to whitePawn9,
        Square(3, 8) to whitePawn10,
        Square(5, 8) to whitePawn11,
        Square(7, 8) to whitePawn12

    )

    private fun createClassicBoard(): Board {
        return Board(classicPieces, 8, 8)
    }

    private fun createValidatorsMap(): Map<Piece, AndValidator> {
        var map = emptyMap<Piece, AndValidator>()
        val pieces = classicPieces.values.toList()
        for (piece in pieces) {
            when (piece.type) {
                PieceType.PAWN -> map = map.plus(
                    piece to AndValidator(
                        listOf(
                            OrValidator(
                                listOf(
                                    AndValidator(listOf(NotBackwardsValidator(), NullDestinationValidator(), AmountValidator(1))),
                                    AndValidator(listOf(NotBackwardsValidator(), IsEatingValidator(), AmountValidator(2)))
                                )
                            )
                        )
                    )
                )

                PieceType.KING -> map = map.plus(
                    piece to AndValidator(listOf())
                )

                else -> {}
            }
        }
        return map
    }

    fun createClassicCheckers(): Game {
        return Game(
            createClassicBoard(),
            ColorType.WHITE,
            globalValidators,
            createValidatorsMap(),
            listOf(NoEnemyLeftValidator()),
            movers
        )
    }

}