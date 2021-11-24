package com.github.wd40bug.chessBackend;

import com.github.wd40bug.chessBackend.pieces.*;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

//https://reqbin.com/req/v0crmky0/rest-api-post-example

@RestController
public class controller {
    String name = "kalem";

    @CrossOrigin
    @GetMapping("/get_board")
    public String getBoard(){
        var gson = new Gson();
        return gson.toJson(getStart());
    }

    @CrossOrigin
    @PostMapping("/whoihate")
    public String customizeHate(@RequestBody String name){
        this.name = name;
        return "Successful";
    }

    public ChessPiece[] getStart(){
        var pieces = new ArrayList<>(Arrays.asList(defaultLine("white", 0)));
        pieces.addAll(Arrays.asList(pawnLine("white",1)));
        pieces.addAll(Arrays.asList(pawnLine("black",6)));
        pieces.addAll(Arrays.asList(defaultLine("black", 7)));
        ChessPiece[] storage = new ChessPiece[32];
        pieces.toArray(storage);
        return storage;
    }
    public ChessPiece[] defaultLine(String color, int y){
        var pieces = new ArrayList<ChessPiece>();
        pieces.add(new Rook(color,0,y));
        pieces.add(new Knight(color,1,y));
        pieces.add(new Bishop(color,2,y));
        pieces.add(new Queen(color,3,y));
        pieces.add(new King(color,4,y));
        pieces.add(new Bishop(color,5,y));
        pieces.add(new Knight(color,6,y));
        pieces.add(new Rook(color,7,y));
        ChessPiece[] storage = new ChessPiece[8];
        pieces.toArray(storage);
        return storage;
    }
    public ChessPiece[] pawnLine(String color, int y){
        var pieces = new ArrayList<ChessPiece>();
        for(int i = 0; i<8; i++){
            pieces.add(new Pawn(color, i, y));
        }
        ChessPiece[] storage = new ChessPiece[8];
        pieces.toArray(storage);
        return storage;
    }
}
