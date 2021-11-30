package com.github.wd40bug.chessBackend;

import com.github.wd40bug.chessBackend.pieces.*;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//https://reqbin.com/req/v0crmky0/rest-api-post-example

@RestController
public class controller {
    String name = "kalem";
    ArrayList<String> IDList= new ArrayList<>();
    HashMap<String,ChessPiece[]> gameMap = new HashMap<>();
    @CrossOrigin
    @GetMapping("/get_board")
    public String getBoard(@RequestParam String ID){
        ChessPiece[] board;
        if(!IDList.contains(ID)){
            board = getStart();
            gameMap.put(ID,board);
            IDList.add(ID);
        }else{
            board = gameMap.get(ID);
        }
        var gson = new Gson();
        return gson.toJson(board);
    }
    public boolean addPiece(@RequestBody ChessPiece piece, String ID){
        if(checkMove(piece)){
            var board = gameMap.get(ID);
            ArrayList<ChessPiece> arrayBoard = (ArrayList<ChessPiece>) Arrays.asList(board);
            arrayBoard.add(piece);
            gameMap.put(ID,arrayBoard.toArray(ChessPiece[]::new));
            return true;
        }else{
            return false;
        }
    }
    public void removePiece(@RequestBody ChessPiece piece, String ID){
        var board = gameMap.get(ID);
        ArrayList<ChessPiece> arrayBoard = (ArrayList<ChessPiece>) Arrays.asList(board);
        arrayBoard.remove(piece);
        gameMap.put(ID, arrayBoard.toArray(ChessPiece[]::new));
    }
    public boolean checkMove(ChessPiece piece){
        //todo add something reasonable here
        return true;
    }
    @CrossOrigin
    @PostMapping("/whoihate")
    public String customizeHate(@RequestBody String who){
        this.name = who;
        return "Successful";
    }

    public ChessPiece[] getStart(){
        var pieces = new ArrayList<>(Arrays.asList(defaultLine("White", 0)));
        pieces.addAll(Arrays.asList(pawnLine("White",1)));
        pieces.addAll(Arrays.asList(pawnLine("Black",6)));
        pieces.addAll(Arrays.asList(defaultLine("Black", 7)));
        ChessPiece[] storage = new ChessPiece[pieces.size()];
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
