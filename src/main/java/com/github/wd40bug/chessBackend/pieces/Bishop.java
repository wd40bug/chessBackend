package com.github.wd40bug.chessBackend.pieces;

public class Bishop extends ChessPiece{
    Bishop(){
        super();
    }
    public Bishop(String color, int x, int y) {
        super(color, x, y);
        this.piece = "Bishop";
    }
}
