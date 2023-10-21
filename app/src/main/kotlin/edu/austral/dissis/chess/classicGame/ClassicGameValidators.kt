package edu.austral.dissis.chess.classicGame

import edu.austral.dissis.chess.validators.*
import edu.austral.dissis.chess.validators.amounts.AmountValidator
import edu.austral.dissis.chess.validators.orientation.VerticalValidator
import types.PieceType

//class ClassicGameValidators : ValidatorFactory {
//    override fun create(type: PieceType): Validator {
//        return when (type) {
//            PieceType.PAWN -> classicPawnValidator()
//            PieceType.ROOK -> classicRookValidator()
//            PieceType.KNIGHT -> classicKnightValidator()
//            PieceType.BISHOP -> classicBishopValidator()
//            PieceType.QUEEN -> classicQueenValidator()
//            PieceType.KING -> classicKingValidator()
//        }
//    }
//
//    private fun classicPawnValidator() : Validator {
//        return OrValidator(listOf(
//            AndValidator(listOf(AmountValidator(1), VerticalValidator(), NotBackwardsValidator() )),
//        ))
//
//    }
//
//    private fun classicRookValidator() : Validator {}
//    private fun classicKnightValidator() : Validator {}
//
//    private fun classicBishopValidator() : Validator {}
//    private fun classicQueenValidator() : Validator {}
//    private fun classicKingValidator() : Validator {}
//
//
//
//}