package com.github.wd40bug.chessBackend.pieces;

public class Knight extends ChessPiece{
    Knight(){
        super();
    }
    public Knight(String color, int x, int y) {
        super(color, x, y);
        this.piece = "Knight";
    }
}
