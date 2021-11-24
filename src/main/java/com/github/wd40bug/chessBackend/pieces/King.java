package com.github.wd40bug.chessBackend.pieces;

public class King extends ChessPiece{
    King(){
        super();
    }
    public King(String color, int x, int y) {
        super(color, x, y);
        this.piece = "King";
    }
}
