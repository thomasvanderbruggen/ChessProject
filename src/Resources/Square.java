package Resources;

import Pieces.Piece;

public class Square {
    private Coordinate loc;
    private Piece heldPiece;
    public Square() {
        heldPiece = null;
        loc = null;
    }
    public Square(Coordinate loc){
        this.loc = loc;
    }
    public Square(Coordinate loc, Piece piece) {
        this.loc = loc;
        heldPiece = piece;
    }
    public Piece removePiece() {
        Piece temp = heldPiece;
        heldPiece = null;
        return temp;
    }
    public Piece addPiece(Piece piece) {
        heldPiece = piece;
        return heldPiece;
    }

    public Coordinate getLoc() {
        return loc;
    }

    public void setLoc(Coordinate loc) {
        this.loc = loc;
    }

    public Piece getHeldPiece() {
        return heldPiece;
    }

    public void setHeldPiece(Piece heldPiece) {
        this.heldPiece = heldPiece;
    }
}