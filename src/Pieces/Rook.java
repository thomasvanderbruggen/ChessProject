package Pieces;

import Resources.Coordinate;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(Coordinate loc, int team) {
        super(loc, team);
    }
    @Override
    public ArrayList<Coordinate> findMoves() {
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        for (int i = loc.yCoord + 1; i < boardYSize; i++) {
            possibleMoves.add(new Coordinate(loc.xCoord, i));
        }
        for (int i = loc.yCoord - 1; i >= 0; i--) {
            possibleMoves.add(new Coordinate(loc.xCoord, i));
        }
        for (int i = loc.xCoord + 1; i < boardXSize; i++) {
            possibleMoves.add(new Coordinate(i, loc.yCoord));
        }
        for (int i = loc.xCoord - 1; i >= 0; i--) {
            possibleMoves.add(new Coordinate(i, loc.yCoord));
        }
        return possibleMoves;
    }
}