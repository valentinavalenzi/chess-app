package edu.austral.dissis.chess

import Board
import edu.austral.dissis.chess.validators.CompositeValidator
import Movement
import Piece
import Square
import edu.austral.dissis.chess.validators.AndValidator
import edu.austral.dissis.chess.validators.NotBackwardsValidator
import edu.austral.dissis.chess.validators.OrValidator
import kotlin.test.Test
import types.ColorType
import types.PieceType
import edu.austral.dissis.chess.validators.orientation.VerticalValidator
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.amounts.QMoveNSquaresValidator

class PawnMoveTest {
//    val sampleMap = mapOf(
//        Square(2, 2) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
//        Square(7, 7) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
//    )
//    val pawn = Piece(ColorType.WHITE, PieceType.PAWN, 0)
//
//    val verticalValidator = VerticalValidator()
//    val notMovingBackwardsValidator = NotBackwardsValidator(ColorType.WHITE)
//    val andValidators = AndValidator(listOf(verticalValidator, notMovingBackwardsValidator))
//
//    val amountValidator = AmountValidator(1)
//    val firstMoveValidator = QMoveNSquaresValidator(0, 2, pawn)
//    val orValidators = OrValidator(listOf(amountValidator, firstMoveValidator))
//
//    val pawnValidators = listOf(andValidators, orValidators)
//    val pawnRule = CompositeValidator(pawnValidators)
//    val board = Board(sampleMap, mapOf(pawn to pawnRule), 8, 8)
//    val game = Game(board, ColorType.WHITE)
//    @Test
//    fun movePawnForwardOnce(){
//        val resultMap = mapOf(
//            Square(2, 3) to Piece(ColorType.WHITE, PieceType.PAWN, 1),
//            Square(7, 7) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
//        )
//        val resultBoard = Board(resultMap, mapOf(pawn to pawnRule), 8, 8)
//        assert(game.move(pawn, Movement(Square(2, 2), Square(2, 3))) == Game(resultBoard, ColorType.BLACK))
//    }
//
//    @Test
//    fun movePawnForwardTwoSquares(){
//        val resultMap = mapOf(
//            Square(2, 4) to Piece(ColorType.WHITE, PieceType.PAWN, 1),
//            Square(7, 7) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
//        )
//        val resultBoard = Board(resultMap, mapOf(pawn to pawnRule), 8, 8)
//        assert(game.move(pawn, Movement(Square(2, 2), Square(2, 4))) == Game(resultBoard, ColorType.BLACK))
//    }
//
//    @Test
//    fun moveSecondPawn() {
//        val blackPawn = Piece(ColorType.BLACK, PieceType.PAWN, 0)
//        val secondMap = mapOf(
//            Square(2, 3) to Piece(ColorType.WHITE, PieceType.PAWN, 1),
//            Square(7, 6) to Piece(ColorType.BLACK, PieceType.PAWN, 1)
//        )
//        val secondPawnAndValidators = AndValidator(listOf(verticalValidator, NotBackwardsValidator(ColorType.BLACK)))
//        val secondPawnValidators = CompositeValidator(listOf(secondPawnAndValidators, orValidators))
//        val rulesMap = mapOf(pawn to pawnRule, blackPawn to secondPawnValidators)
//        val secondBoard = Board(secondMap, rulesMap, 8, 8)
//
//        val firstBoard = Board(sampleMap, mapOf(pawn to pawnRule, blackPawn to secondPawnValidators), 8, 8)
//        val firstGame = Game(firstBoard, ColorType.WHITE)
//        val newGame = firstGame.move(pawn, Movement(Square(2, 2), Square(2, 3)))
//
//        val resultGame = newGame.move(blackPawn, Movement(Square(7, 7), Square(7, 6)))
//
//        assert(resultGame == Game(secondBoard, ColorType.WHITE))
//        //debuggeandolo quedan iguales pero no sé que pasa
//    }
//
//    @Test
//    fun movePawnForwardThreeSquares(){
//        val resultMap = mapOf(
//            Square(2, 3) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
//            Square(7, 7) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
//        )
//        val resultBoard = Board(resultMap, mapOf(pawn to pawnRule), 8, 8)
//        assert(game.move(pawn, Movement(Square(2, 2), Square(2, 3))) !== Game(resultBoard, ColorType.BLACK))
//    }
//
//    @Test
//    fun movingPawnBackwards(){
//        val initialMap = mapOf(
//            Square(2, 2) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
//            Square(7, 7) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
//        )
//        val resultBoard = Board(initialMap, mapOf(pawn to pawnRule), 8, 8)
//        assert(game.move(pawn, Movement(Square(2, 2), Square(2, 1))) == Game(resultBoard, ColorType.BLACK))
//    }
//


}