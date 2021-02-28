package Pieces;

import Resources.Coordinate;

import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(Coordinate loc,int team){
        this.loc = loc;
        this.team = team;
    }
    @Override
    public ArrayList<Coordinate> findMoves() {
        int distance;
        ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
        for (int i = loc.yCoord + 1; i < boardXSize; i++) {
            distance = i - loc.yCoord;
            possibleMoves.add(new Coordinate(loc.xCoord + distance, loc.yCoord + distance));
            possibleMoves.add(new Coordinate(loc.xCoord - distance, loc.yCoord + distance));
        }
        for (int i = loc.yCoord - 1; i >= 0; i--) {
            distance = loc.yCoord - i;
            possibleMoves.add(new Coordinate(loc.xCoord + distance, loc.yCoord - distance));
            possibleMoves.add(new Coordinate(loc.xCoord - distance, loc.yCoord - distance));
        }
        return possibleMoves;
    }
}