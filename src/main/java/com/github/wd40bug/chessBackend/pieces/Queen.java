package com.github.wd40bug.chessBackend.pieces;

public class Queen extends ChessPiece{
    Queen(){
        super();
    }
    public Queen(String color, int x, int y) {
        super(color, x, y);
        this.piece = "Queen";
    }
}
