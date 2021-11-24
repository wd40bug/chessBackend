package com.github.wd40bug.chessBackend.pieces;

public class Rook extends ChessPiece{
    Rook(){
        super();
    }
    public Rook(String color, int x, int y) {
        super(color, x, y);
        this.piece = "Rook";
    }
}
