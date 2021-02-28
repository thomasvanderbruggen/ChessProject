package Pieces;

import Resources.Coordinate;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(Coordinate loc, int team) {
        super(loc, team);
    }
    @Override
    public ArrayList<Coordinate> findMoves(){
        ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
        for (int i = loc.yCoord + 1; i < boardYSize; i++) {
            possibleMoves.add(new Coordinate(loc.xCoord, i));
            int distance = i - loc.yCoord;
            possibleMoves.add(new Coordinate(loc.xCoord + distance,loc.yCoord + distance));
            possibleMoves.add(new Coordinate(loc.xCoord - distance, loc.yCoord + distance));
        }
        for (int i = loc.yCoord - 1; i >= 0; i--) {
            possibleMoves.add(new Coordinate(loc.xCoord, i));
            int distance = loc.yCoord - i;
            possibleMoves.add(new Coordinate(loc.xCoord + distance, loc.yCoord - distance));
            possibleMoves.add(new Coordinate(loc.xCoord - distance, loc.yCoord - distance));
        }
        for (int i = loc.xCoord + 1; i < boardXSize; i++) {
            possibleMoves.add(new Coordinate(i, loc.yCoord));
        }
        for (int i = loc.xCoord - 1; i >= 0; i++) {
            possibleMoves.add(new Coordinate(i, loc.yCoord));
        }
        return possibleMoves;
    }
}