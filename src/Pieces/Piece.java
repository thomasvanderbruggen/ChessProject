package Pieces;

import Resources.*;
import java.util.ArrayList;

public class Piece {
    protected Coordinate loc;
    protected ArrayList<Coordinate> possibleMoves;
    protected int team;
    protected int boardYSize = 8;
    protected int boardXSize = 8;
    public Piece(){

    }
    public Piece(Coordinate loc, int team){
        this.loc = loc;
        this.team = team;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getySize() {
        return boardYSize;
    }

    public void setySize(int ySize) {
        this.boardYSize = ySize;
    }

    public int getxSize() {
        return boardXSize;
    }

    public void setxSize(int xSize) {
        this.boardXSize = xSize;
    }

    public Piece(Coordinate loc){
        this.loc = loc;
    }
    public Coordinate getLoc() {
        return loc;
    }

    public void setLoc(Coordinate loc) {
        this.loc = loc;
    }

    public ArrayList<Coordinate> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<Coordinate> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
    public ArrayList<Coordinate> findMoves() {
        ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
        return possibleMoves;
    }
    public void move(Coordinate loc){
        this.loc = loc;
    }
}