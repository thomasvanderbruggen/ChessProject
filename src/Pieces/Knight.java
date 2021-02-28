package Pieces;

import Resources.Coordinate;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Coordinate loc, int team){
        super(loc, team);
    }
    @Override
    public ArrayList<Coordinate> findMoves() {
        ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
        if (loc.xCoord + 2 <= boardXSize) {
            if (loc.yCoord + 1 <= boardYSize) {
                possibleMoves.add(new Coordinate(loc.xCoord + 2, loc.yCoord + 1));
            }
            if (loc.yCoord - 1 >= 0) {
                possibleMoves.add(new Coordinate(loc.xCoord + 2, loc.yCoord - 1));
            }
        }
        if (loc.xCoord - 2 >= 0) {
            if (loc.yCoord + 1 <= boardYSize) {
                possibleMoves.add(new Coordinate(loc.xCoord - 2, loc.yCoord + 1));
            }
            if (loc.yCoord - 1 >= 0) {
                possibleMoves.add(new Coordinate(loc.xCoord - 2, loc.yCoord + 1));
            }
        }
        if (loc.yCoord + 2 <= boardYSize) {
            if (loc.xCoord + 1 <= boardXSize) {
                possibleMoves.add(new Coordinate(loc.xCoord + 1, loc.yCoord + 2));
            }
            if (loc.xCoord - 1 >= 0) {
                possibleMoves.add(new Coordinate(loc.xCoord - 1, loc.yCoord + 2));
            }
        }
        if (loc.yCoord - 2 >= 0) {
            if (loc.xCoord + 1 <= boardXSize) {
                possibleMoves.add(new Coordinate(loc.xCoord + 1, loc.yCoord - 2));
            }
            if (loc.xCoord - 1 <= boardXSize) {
                possibleMoves.add(new Coordinate(loc.xCoord - 1, loc.yCoord - 2));
            }
        }
        return possibleMoves;
    }
}