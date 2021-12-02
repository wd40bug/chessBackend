package com.github.wd40bug.chessBackend.pieces;
import com.github.wd40bug.chessBackend.ChessCoordinate;

public class ChessPiece {
    String color;
    String piece;
    int x;
    int y;
    ChessCoordinate ccoord;
    ChessPiece(){}
    ChessPiece(String color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
