package edu.austral.dissis.chess.pieces


/* might not run correctly because NotMovingBackwardsValidator was changed due to the ui */

class PawnMoveTest {
//    val sampleMap = mapOf(
//        Square(0, 1) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
//    )
//    val pawn = Piece(ColorType.WHITE, PieceType.PAWN, 0)
//
//    val verticalValidator = VerticalValidator()
//    val notMovingBackwardsValidator = NotBackwardsValidator(ColorType.WHITE)
//    val isNotEatingEnemyValidator = IsNotEatingEnemyValidator()
//    val amountValidator = AmountValidator(1)
//    val diagonalValidator = DiagonalValidator()
//    val isEatingEnemy = IsEatingEnemyValidator()
//
//    val firstMoveValidator = AndValidator(
//        listOf(
//            verticalValidator,
//            notMovingBackwardsValidator,
//            isNotEatingEnemyValidator,
//            QMoveNSquaresValidator(0, 2, pawn)
//        )
//    )
//
//    val secondMoveValidator = AndValidator(
//        listOf(
//            verticalValidator,
//            notMovingBackwardsValidator,
//            amountValidator,
//            isNotEatingEnemyValidator
//        )
//    )
//    val thirdMoveValidator = AndValidator(
//        listOf(
//            diagonalValidator,
//            notMovingBackwardsValidator,
//            amountValidator,
//            isEatingEnemy
//        )
//    )
//
//    val generalValidations = AndValidator(listOf(IsInsideBoardValidator(), NotEatingSameColor()))
//    val orValidators = OrValidator(listOf(firstMoveValidator, secondMoveValidator, thirdMoveValidator))
//
//    val pawnRules = CompositeValidator(listOf(generalValidations, orValidators))
//    val board = Board(sampleMap, mapOf(pawn to pawnRules), 8, 8)
//    val game = Game(board, ColorType.WHITE)
//
//    @Test
//    fun movePawnForwardOneSquare() {
//        val resultMap = mapOf(
//            Square(0, 2) to Piece(ColorType.WHITE, PieceType.PAWN, 1),
//        )
//        val resultBoard = Board(resultMap, mapOf(Piece(ColorType.WHITE, PieceType.PAWN, 1) to pawnRules), 8, 8)
//        val resultGame = game.move(pawn, edu.austral.dissis.chess.Movement(Square(0, 1), Square(0, 2), board))
//        assert(resultGame == Game(resultBoard, ColorType.BLACK))
//    }
//
//    @Test
//    fun movePawnForwardTwoSquares() {
//        val firstMap = mapOf(
//            Square(0, 6) to Piece(ColorType.BLACK, PieceType.PAWN, 0),
//        )
//        val resultMap = mapOf(
//            Square(0, 4) to Piece(ColorType.BLACK, PieceType.PAWN, 1),
//        )
//
//        val bPawn = Piece(ColorType.BLACK, PieceType.PAWN, 0)
//
//        val verticalValidator = VerticalValidator()
//        val notMovingBackwardsValidator = NotBackwardsValidator(ColorType.BLACK)
//        val isNotEatingEnemyValidator = IsNotEatingEnemyValidator()
//        val amountValidator = AmountValidator(1)
//        val diagonalValidator = DiagonalValidator()
//        val isEatingEnemy = IsEatingEnemyValidator()
//
//        val firstMoveValidator = AndValidator(
//            listOf(
//                verticalValidator,
//                notMovingBackwardsValidator,
//                isNotEatingEnemyValidator,
//                QMoveNSquaresValidator(0, 2, bPawn)
//            )
//        )
//
//        val secondMoveValidator = AndValidator(
//            listOf(
//                verticalValidator,
//                notMovingBackwardsValidator,
//                amountValidator,
//                isNotEatingEnemyValidator
//            )
//        )
//        val thirdMoveValidator = AndValidator(
//            listOf(
//                diagonalValidator,
//                notMovingBackwardsValidator,
//                amountValidator,
//                isEatingEnemy
//            )
//        )
//
//        val orValidators = OrValidator(listOf(firstMoveValidator, secondMoveValidator, thirdMoveValidator))
//
//        val bPawnRules = CompositeValidator(listOf(orValidators))
//        val firstBoard = Board(firstMap, mapOf(bPawn to bPawnRules), 8, 8)
//        val game = Game(firstBoard, ColorType.BLACK)
//
//        val resultBoard = Board(resultMap, mapOf(Piece(ColorType.BLACK, PieceType.PAWN, 1) to bPawnRules), 8, 8)
//        val resGame = game.move(bPawn, edu.austral.dissis.chess.Movement(Square(0, 6), Square(0, 4), firstBoard))
//        assert(resGame == Game(resultBoard, ColorType.WHITE))
//    }
//
////    @Test
////    fun moveSecondPawn() {
////        val blackPawn = Piece(ColorType.BLACK, PieceType.PAWN, 0)
////        val secondMap = mapOf(
////            Square(2, 3) to Piece(ColorType.WHITE, PieceType.PAWN, 1),
////            Square(7, 6) to Piece(ColorType.BLACK, PieceType.PAWN, 1)
////        )
////        val secondPawnAndValidators = AndValidator(listOf(verticalValidator, NotBackwardsValidator(ColorType.BLACK)))
////        val secondPawnValidators = CompositeValidator(listOf(secondPawnAndValidators, orValidators))
////        val rulesMap = mapOf(pawn to pawnRule, blackPawn to secondPawnValidators)
////        val secondBoard = edu.austral.dissis.chess.Board(secondMap, rulesMap, 8, 8)
////
////        val firstBoard = edu.austral.dissis.chess.Board(sampleMap, mapOf(pawn to pawnRule, blackPawn to secondPawnValidators), 8, 8)
////        val firstGame = Game(firstBoard, ColorType.WHITE)
////        val newGame = firstGame.move(pawn, edu.austral.dissis.chess.Movement(Square(2, 2), Square(2, 3)))
////
////        val resultGame = newGame.move(blackPawn, edu.austral.dissis.chess.Movement(Square(7, 7), Square(7, 6)))
////
////        assert(resultGame == Game(secondBoard, ColorType.WHITE))
////        //debuggeandolo quedan iguales pero no sé que pasa
////    }
//
//    @Test
//    fun movePawnForwardThreeSquares(){
//        val resultMap = mapOf(
//            Square(2, 1) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
//        )
//        val resultBoard = Board(resultMap, mapOf(pawn to pawnRules), 8, 8)
//        assert(game.move(pawn, edu.austral.dissis.chess.Movement(Square(2, 1), Square(2, 4), board)) !== Game(resultBoard, ColorType.BLACK))
//    }
//
////    @Test
////    fun movingPawnBackwards(){
////        val initialMap = mapOf(
////            Square(2, 1) to Piece(ColorType.WHITE, PieceType.PAWN, 0),
////        )
////        val resultBoard = Board(initialMap, mapOf(Piece(ColorType.WHITE, PieceType.PAWN, 0) to pawnRules), 8, 8)
////        val tryMovingBackwards = game.move(pawn, edu.austral.dissis.chess.Movement(Square(2, 1), Square(2, 0), board))
////        assert(tryMovingBackwards == Game(resultBoard, ColorType.BLACK))
////    }
//
//    @Test
//    fun eatingBlackPawn() {
//        val pawn = Piece(ColorType.WHITE, PieceType.PAWN, 0)
//        val blackPawn = Piece(ColorType.BLACK, PieceType.PAWN, 0)
//        val firstMap = mapOf(
//            Square(2, 1) to pawn,
//            Square(3, 2) to blackPawn
//        )
//        val resultBoard = Board(mapOf(Square(3, 2) to Piece(ColorType.WHITE, PieceType.PAWN, 1)),
//            mapOf(Piece(ColorType.WHITE, PieceType.PAWN, 1) to pawnRules), 8, 8)
//        val board = Board(firstMap, mapOf(pawn to pawnRules), 8, 8)
//        val game = Game(board, ColorType.WHITE)
//
//        val tryEating = game.move(pawn, edu.austral.dissis.chess.Movement(Square(2, 1), Square(3, 2), board))
//        assert(tryEating == Game(resultBoard, ColorType.BLACK))
//    }

}