package piecesMechanic;

import util.Move;

public class Pawn extends Piece{
    public Pawn(Color color) {
        super(color);
        this.type = Piece.Type.PAWN;
    }

    public boolean validateMove(Move move){
        Piece.Color color = move.getPiece().getColor();
        //pawns can only move at file difference of +-1
        //rank difference of +1
        //capture opposite color pieces
        switch (color){
            case WHITE:
                if(Math.abs(move.getDestinationFile() - move.getOriginFile()) == 1
                && move.getDestinationRank() - move.getOriginRank() == 1
                && move.getCapturedPiece() != null
                && Piece.Color.BLACK.equals(move.getCapturedPiece().getColor())){
                    return true;
                }
                break;
            case BLACK:
                if(Math.abs(move.getDestinationFile() - move.getOriginFile()) == 1
                && move.getDestinationRank() - move.getOriginRank() == -1
                && move.getCapturedPiece() != null
                && Piece.Color.WHITE.equals(move.getCapturedPiece().getColor())){
                    return true;
                }
                break;
        }

        //pawns can move 2 ranks when at their initial position
        switch (color) {
            case WHITE:
                if (move.getOriginRank() == 2
                        && move.getDestinationFile() == move.getOriginFile()
                        && move.getDestinationRank() - move.getOriginRank() <= 2
                        && move.getDestinationRank() - move.getOriginRank() >= 1
                        && move.getCapturedPiece() == null) {
                    // origin rank = 2
                    // no file difference
                    // 1 <= rank difference <= 2
                    // no captured piece
                    return true;
                }
                break;
            case BLACK:
                if (move.getOriginRank() == 7
                        && move.getDestinationFile() == move.getOriginFile()
                        && move.getDestinationRank() - move.getOriginRank() >= -2
                        && move.getDestinationRank() - move.getOriginRank() <= -1
                        && move.getCapturedPiece() == null) {
                    // origin rank = 7
                    // no file difference
                    // -2 <= rank difference <= -1
                    // no captured piece
                    return true;
                }
                break;
        }

        //normally move a pawn forward
        switch (color) {
            case WHITE:
                if (move.getDestinationFile() == move.getOriginFile()
                        && move.getDestinationRank() - move.getOriginRank() == 1
                        && move.getCapturedPiece() == null) {
                    // executeMove along file
                    // rank difference = 1
                    // no captured piece
                    return true;
                }
                break;
            case BLACK:
                if (move.getDestinationFile() == move.getOriginFile()
                        && move.getDestinationRank() - move.getOriginRank() == -1
                        && move.getCapturedPiece() == null) {
                    // executeMove along file
                    // rank difference = -1
                    // no captured piece
                    return true;
                }
                break;
        }

        // all other cases
        return false;
    }
}
