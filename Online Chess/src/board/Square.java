package board;

import piecesMechanic.*;

public class Square {
    private int xCoordinate;
    private int yCoordinate;

    private char file;
    private int rank;

    private Piece currentPiece;

    public Square(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.currentPiece = null;
    }

    public Square(int xCoordinate, int yCoordinate, Piece initialPiece){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.currentPiece = initialPiece;
    }

    public Square(char file, int rank){
        this.file = file;
        this.rank = rank;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }
}
