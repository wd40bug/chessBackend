package com.github.wd40bug.chessBackend;

import com.github.wd40bug.chessBackend.pieces.*;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//https://reqbin.com/req/v0crmky0/rest-api-post-example

@RestController
public class controller {
    private static final Logger logger = LogManager.getLogger(controller.class);
    String name = "kalem";
    ArrayList<String> IDList= new ArrayList<>();
    HashMap<String,ChessPiece[]> gameMap = new HashMap<>();
    @CrossOrigin
    @GetMapping("/get_board")
    public String getBoard(@RequestParam String ID){
        ChessPiece[] board;
        if(!IDList.contains(ID)){
            IDList.add(ID);
            logger.info("adding "+ID+" to ID List");
            board = getStart();
            gameMap.put(ID,board);
        }else{
            logger.info("We already know "+ID);
            board = gameMap.get(ID);
        }
        var gson = new Gson();
        return gson.toJson(board);
    }
    @CrossOrigin
    @PostMapping("/testJSONsending")
    public void testJSON(@RequestBody String pieceString, @RequestParam String ID){
        logger.info("GOT: "+new Gson().fromJson(pieceString,ChessPiece.class)+" "+ID);
    }
    @CrossOrigin
    @PostMapping("/addPiece")
    public boolean addPiece(@RequestBody String pieceString,@RequestParam String ID){
        var piece = new Gson().fromJson(pieceString,ChessPiece.class);
        logger.info(piece.toString());
        if(checkMove(piece)){
            var board = gameMap.get(ID);
            var arrayBoard = new ArrayList<>(Arrays.asList(board));
            arrayBoard.stream().filter(piece1 -> (piece1.getX() == piece.getX() && piece1.getY() ==
                    piece.getY())).findFirst().ifPresent(arrayBoard::remove);
            arrayBoard.add(piece);
            gameMap.put(ID,arrayBoard.toArray(ChessPiece[]::new));

            return true;
        }else{
            return false;
        }
    }
    @CrossOrigin
    @PostMapping("/removePiece")
    public void removePiece(@RequestBody String pieceString,@RequestParam String ID){
        logger.info("removed "+pieceString);
        var piece = new Gson().fromJson(pieceString,ChessPiece.class);
        var board = gameMap.get(ID);
        var arrayBoard = new ArrayList<>(Arrays.asList(board));
        logger.info(arrayBoard);
        arrayBoard.remove(piece);
        gameMap.put(ID, arrayBoard.toArray(ChessPiece[]::new));
    }
    public boolean checkMove(ChessPiece piece){
        //todo add something reasonable here
        return true;
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
