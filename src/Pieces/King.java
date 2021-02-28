package Pieces;

import Resources.Coordinate;

import java.util.ArrayList;

public class King extends Piece {
    public King (Coordinate loc, int team) {
        super(loc, team);
    }
    @Override
    public ArrayList<Coordinate> findMoves(){
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinate(loc.xCoord + 1, loc.yCoord));
        possibleMoves.add(new Coordinate(loc.xCoord - 1, loc.yCoord));
        possibleMoves.add(new Coordinate(loc.xCoord, loc.yCoord + 1));
        possibleMoves.add(new Coordinate(loc.xCoord, loc.yCoord -1));
        return possibleMoves;
    }
}