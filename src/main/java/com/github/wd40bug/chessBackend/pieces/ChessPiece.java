package com.github.wd40bug.chessBackend.pieces;
import com.github.wd40bug.chessBackend.ChessCoordinate;

public class ChessPiece {
    String color;
    String piece;
    int x;
    int y;
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

    @Override
    public String toString() {
        return "ChessPiece{" +
                "color='" + color + '\'' +
                ", piece='" + piece + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
