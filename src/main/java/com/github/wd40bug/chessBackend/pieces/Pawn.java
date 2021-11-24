package com.github.wd40bug.chessBackend.pieces;

public class Pawn extends ChessPiece{
    Pawn() {
        super();
    }
    public Pawn(String color, int x, int y) {
        super(color, x, y);
        this.piece = "Pawn";
    }
}
