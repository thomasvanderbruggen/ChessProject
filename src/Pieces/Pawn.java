package Pieces;

import Resources.Coordinate;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean firstMove;
    public Pawn(Coordinate loc, int team) {
        super(loc, team);
        firstMove = true;
    }
    @Override
    public ArrayList<Coordinate> findMoves(){
        ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
        if (firstMove == true) {
            possibleMoves.add(new Coordinate(loc.xCoord, loc.yCoord+1));
            possibleMoves.add(new Coordinate(loc.xCoord, loc.yCoord+2));
        }else {
            possibleMoves.add(new Coordinate(loc.xCoord, loc.yCoord));
        }
        return possibleMoves;
    }
    public boolean getFirstMove() {
        return firstMove;
    }
}