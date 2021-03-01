package Board;
// TODO: Finish adding in team validation to the rest of the valid move checkers (Done: All but King gl), The rest of the board shit
import Errors.SquareNotAvailableException;
import Pieces.*;
import Resources.Coordinate;
import Resources.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ChessBoard {
    ArrayList<Piece> whiteLostPieces;
    ArrayList<Piece> blackLostPieces;
    ArrayList<Piece> whiteAvailPieces;
    ArrayList<Piece> blackAvailPieces;
    Square[][] squares;

    public ChessBoard() {
        resetBoard();
    }

    public void resetBoard() {
        squares = new Square[8][8];
        whiteAvailPieces = new ArrayList<>();
        blackAvailPieces = new ArrayList<>();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Coordinate temp = new Coordinate(y, x);
                squares[y][x] = new Square(temp);
            }
        }
        resetWhite();
        resetBlack();
    }

    public void resetWhite() {
        for (int x = 0; x < 8; x++) {
            squares[1][x].addPiece(new Pawn(new Coordinate(x, 2), 0));
        }
        squares[0][0].addPiece(new Rook(new Coordinate(0, 0), 0));
        squares[0][7].addPiece(new Rook(new Coordinate(7, 0), 0));
        squares[0][1].addPiece(new Knight(new Coordinate(1, 0), 0));
        squares[0][6].addPiece(new Knight(new Coordinate(6, 0), 0));
        squares[0][2].addPiece(new Bishop(new Coordinate(2, 0), 0));
        squares[0][5].addPiece(new Bishop(new Coordinate(5, 0), 0));
        squares[0][3].addPiece(new Queen(new Coordinate(3, 0), 0));
        squares[0][4].addPiece(new King(new Coordinate(4, 0), 0));
    }

    public void resetBlack() {
        for (int x = 0; x < 8; x++) {
            squares[6][x].addPiece(new Pawn(new Coordinate(x, 2), 1));
        }
        squares[7][0].addPiece(new Rook(new Coordinate(0, 0), 1));
        squares[7][7].addPiece(new Rook(new Coordinate(7, 0), 1));
        squares[7][1].addPiece(new Knight(new Coordinate(1, 0), 1));
        squares[7][6].addPiece(new Knight(new Coordinate(6, 0), 1));
        squares[7][2].addPiece(new Bishop(new Coordinate(2, 0), 1));
        squares[7][5].addPiece(new Bishop(new Coordinate(5, 0), 1));
        squares[7][3].addPiece(new Queen(new Coordinate(3, 0), 1));
        squares[7][4].addPiece(new King(new Coordinate(4, 0), 1));
    }

    public void movePiece(Coordinate currentLoc, Coordinate newLoc) throws SquareNotAvailableException {
        Piece tempPiece = squares[currentLoc.xCoord][currentLoc.yCoord].removePiece();
        if (squares[newLoc.xCoord][newLoc.yCoord].getHeldPiece() == null) {
            squares[newLoc.xCoord][newLoc.yCoord].addPiece(tempPiece);
            tempPiece.setLoc(newLoc);
        } else if (squares[newLoc.xCoord][newLoc.yCoord].getHeldPiece().getTeam() != tempPiece.getTeam()) {
            if (tempPiece.getTeam() == 0) {
                blackLostPieces.add(squares[newLoc.xCoord][newLoc.yCoord].removePiece());
                squares[newLoc.xCoord][newLoc.yCoord].addPiece(tempPiece);
                tempPiece.setLoc(newLoc);
            } else {
                whiteLostPieces.add(squares[newLoc.xCoord][newLoc.yCoord].removePiece());
                squares[newLoc.xCoord][newLoc.yCoord].addPiece(tempPiece);
                tempPiece.setLoc(newLoc);
            }
        } else {
            throw new SquareNotAvailableException("Square occupied by own team");
        }
    }

    public ArrayList<Coordinate> getAllowedMoves(Pawn piece) {
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        if (piece.getFirstMove()) {
            if (piece.getTeam() == 0) {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord + 1));
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord + 2));
            } else {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord - 1));
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord - 2));
            }
        } else {
            if (piece.getTeam() == 0) {
                if (squares[piece.getLoc().xCoord][piece.getLoc().yCoord + 1].getHeldPiece() == null) {
                     possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord + 1));
                }
                if (squares[piece.getLoc().xCoord + 1][piece.getLoc().yCoord + 1].getHeldPiece() != null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 1, piece.getLoc().yCoord + 1));
                }
                if (squares[piece.getLoc().xCoord - 1][piece.getLoc().yCoord + 1].getHeldPiece() != null){
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 1, piece.getLoc().yCoord + 1));
                }
            }else {
                if (squares[piece.getLoc().xCoord][piece.getLoc().yCoord - 1].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord - 1));
                }
                if (squares[piece.getLoc().xCoord + 1][piece.getLoc().yCoord - 1].getHeldPiece() != null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 1, piece.getLoc().yCoord - 1));
                }
                if (squares[piece.getLoc().xCoord - 1][piece.getLoc().yCoord - 1].getHeldPiece() != null){
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 1, piece.getLoc().yCoord - 1));
                }
                if (squares[piece.getLoc().xCoord][piece.getLoc().yCoord - 1].getHeldPiece() != null) {
                    if (squares[piece.getLoc().xCoord][piece.getLoc().yCoord - 1].getHeldPiece().getTeam() != 1) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord - 1));
                    }
                } else {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord - 1));
                }
            }
        }
        return possibleMoves;
    }

    public ArrayList<Coordinate> getAllowedMoves(Bishop piece) {
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        int distance;
        boolean upRight = true, upLeft = true, downLeft = true, downRight = true; // Holds true if the path is unimpeded, switched to false if path is blocked by a differing piece
        for (int i = piece.getLoc().yCoord + 1; i < piece.getySize(); i++) { // find all available moves above the bishop
            distance = i - piece.getLoc().yCoord; // Find the amount of squares we are vertically above the bishop, used to find available moves as bishop move function is f(x) = x
            if (upRight && piece.getLoc().xCoord + distance <= 7) {
                if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord + distance].getHeldPiece() != null) {
                    if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord + distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord + distance));
                    }
                    upRight = false;
                } else {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord + distance));
                }
            }
            if (upLeft && piece.getLoc().xCoord - distance >= 0) {
                if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord + distance].getHeldPiece() != null) {
                    if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord + distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord + distance));
                    }
                    upLeft = false;
                } else {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord + distance));
                }
            }
        }
        for (int i = piece.getLoc().yCoord - 1; i >= 0; i++) {
            distance = piece.getLoc().yCoord - i;
            if (downRight && piece.getLoc().xCoord + distance <= 7) {
                if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord - distance].getHeldPiece() != null) {
                    if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord - distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord - distance));
                    }
                    downRight = false;
                } else {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord - distance));
                }
            }
            if (downLeft && piece.getLoc().xCoord - distance >= 0) {
                if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord - distance].getHeldPiece() != null) {
                    if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord - distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord - distance));
                    }
                    downLeft = false;
                } else {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord - distance));
                }
            }
        }
        return possibleMoves;
    }

    public ArrayList<Coordinate> getAllowedMoves(Rook piece) {
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        for (int i = piece.getLoc().yCoord + 1; i < piece.getySize(); i++) {
            if (squares[piece.getLoc().xCoord][i].getHeldPiece() == null) {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
            } else {
                if (squares[piece.getLoc().xCoord][i].getHeldPiece().getTeam() != piece.getTeam()) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
                }
                break;
            }
        }
        for (int i = piece.getLoc().yCoord - 1; i >= 0; i--) {
            if (squares[piece.getLoc().xCoord][i].getHeldPiece() == null) {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
            } else {
                if (squares[piece.getLoc().xCoord][i].getHeldPiece().getTeam() != piece.getTeam()) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
                }
                break;
            }
        }
        for (int i = piece.getLoc().xCoord + 1; i < piece.getxSize(); i++) {
            if (squares[i][piece.getLoc().yCoord].getHeldPiece() == null) {
                possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
            } else {
                if (squares[piece.getLoc().xCoord][i].getHeldPiece().getTeam() != piece.getTeam()) {
                    possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
                }
                break;
            }
        }
        for (int i = piece.getLoc().xCoord - 1; i >= 0; i--) {
            if (squares[i][piece.getLoc().yCoord].getHeldPiece() == null) {
                possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
            } else {
                if (squares[i][piece.getLoc().yCoord].getHeldPiece().getTeam() != piece.getTeam()) {
                    possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
                }
                break;
            }
        }
        return possibleMoves;
    }

    public ArrayList<Coordinate> getAllowedMoves(Knight piece) {
        ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
        if (piece.getLoc().xCoord + 2 <= piece.getxSize()) {
            if (piece.getLoc().yCoord + 1 <= piece.getySize()) {
                if (squares[piece.getLoc().xCoord + 2][piece.getLoc().yCoord + 1].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 2, piece.getLoc().yCoord + 1));
                } else {
                    if (squares[piece.getLoc().xCoord + 2][piece.getLoc().yCoord + 1].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 2, piece.getLoc().yCoord + 1));
                    }
                }
            }
            if (piece.getLoc().yCoord - 1 >= 0) {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 2, piece.getLoc().yCoord - 1));
                if (squares[piece.getLoc().xCoord + 2][piece.getLoc().yCoord - 1].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 2, piece.getLoc().yCoord - 1));
                } else {
                    if (squares[piece.getLoc().xCoord + 2][piece.getLoc().yCoord - 1].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 2, piece.getLoc().yCoord - 1));
                    }
                }
            }
        }
        if (piece.getLoc().xCoord - 2 >= 0) {
            if (piece.getLoc().yCoord + 1 <= piece.getySize()) {
                if (squares[piece.getLoc().xCoord - 2][piece.getLoc().yCoord + 1].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 2, piece.getLoc().yCoord + 1));
                } else {
                    if (squares[piece.getLoc().xCoord - 2][piece.getLoc().yCoord + 1].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 2, piece.getLoc().yCoord + 1));
                    }
                }
            }
            if (piece.getLoc().yCoord - 1 >= 0) {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 2, piece.getLoc().yCoord + 1));
                if (squares[piece.getLoc().xCoord - 2][piece.getLoc().yCoord - 1].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 2, piece.getLoc().yCoord - 1));
                } else {
                    if (squares[piece.getLoc().xCoord - 2][piece.getLoc().yCoord - 1].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 2, piece.getLoc().yCoord - 1));
                    }
                }
            }
        }
        if (piece.getLoc().yCoord + 2 <= piece.getySize()) {
            if (piece.getLoc().xCoord + 1 <= piece.getxSize()) {
                if (squares[piece.getLoc().xCoord + 1][piece.getLoc().yCoord + 2].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 1, piece.getLoc().yCoord + 2));
                } else {
                    if (squares[piece.getLoc().xCoord + 1][piece.getLoc().yCoord + 2].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 1, piece.getLoc().yCoord + 2));
                    }
                }
            }
            if (piece.getLoc().xCoord - 1 >= 0) {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 1, piece.getLoc().yCoord + 2));
            }
        }
        if (piece.getLoc().yCoord - 2 >= 0) {
            if (piece.getLoc().xCoord + 1 <= piece.getxSize()) {
                if (squares[piece.getLoc().xCoord + 1][piece.getLoc().yCoord - 2].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 1, piece.getLoc().yCoord - 2));
                } else {
                    if (squares[piece.getLoc().xCoord + 1][piece.getLoc().yCoord - 2].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 1, piece.getLoc().yCoord - 2));
                    }
                }
            }
            if (piece.getLoc().xCoord - 1 <= piece.getxSize()) {
                possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 1, piece.getLoc().yCoord - 2));
            }
        }
        return possibleMoves;
    }

    public ArrayList<Coordinate> getAllowedMoves(Queen piece) {
        boolean up = true, upLeft = true, upRight = true, down = true, downLeft = true, downRight = true, left = true, right = true;
        ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
        for (int i = piece.getLoc().yCoord + 1; i < piece.getySize(); i++) {
            if (up) {
                if (squares[piece.getLoc().xCoord][i].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
                } else {
                    if (squares[piece.getLoc().xCoord][i].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
                    }
                    up = false;
                }
            }
            int distance = i - piece.getLoc().yCoord;
            if (upRight) {
                if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord + distance].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord + distance));
                } else {
                    if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord + distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord + distance));
                    }
                    upRight = false;
                }
            }
            if (upLeft) {
                if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord + distance].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord + distance));
                } else {
                    if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord + distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord + distance));
                    }
                    upLeft = false;
                }
            }
        }
        for (int i = piece.getLoc().yCoord - 1; i >= 0; i--) {
            if (down) {
                if (squares[piece.getLoc().xCoord][i].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
                } else {
                    if (squares[piece.getLoc().xCoord][i].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord, i));
                    }
                    down = false;
                }
            }
            int distance = piece.getLoc().yCoord - i;
            if (downRight) {
                if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord - distance].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord - distance));
                } else {
                    if (squares[piece.getLoc().xCoord + distance][piece.getLoc().yCoord - distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + distance, piece.getLoc().yCoord - distance));
                    }
                    downRight = false;
                }
            }
            if (downLeft) {
                if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord - distance].getHeldPiece() == null) {
                    possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord - distance));
                } else {
                    if (squares[piece.getLoc().xCoord - distance][piece.getLoc().yCoord - distance].getHeldPiece().getTeam() != piece.getTeam()) {
                        possibleMoves.add(new Coordinate(piece.getLoc().xCoord - distance, piece.getLoc().yCoord - distance));
                    }
                    downLeft = false;
                }
            }
        }
        for (int i = piece.getLoc().xCoord + 1; i < piece.getxSize(); i++) {
            if (squares[i][piece.getLoc().yCoord].getHeldPiece() == null) {
                possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
            } else {
                if (squares[i][piece.getLoc().yCoord].getHeldPiece().getTeam() != piece.getTeam()) {
                    possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
                }
                break;
            }
        }
        for (int i = piece.getLoc().xCoord - 1; i >= 0; i++) {
            if (squares[i][piece.getLoc().yCoord].getHeldPiece() == null) {
                possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
            } else {
                if (squares[i][piece.getLoc().yCoord].getHeldPiece().getTeam() != piece.getTeam()) {
                    possibleMoves.add(new Coordinate(i, piece.getLoc().yCoord));
                }
                break;
            }
        }
        return possibleMoves;
    }

    public ArrayList<Coordinate> getAllowedMoves(King piece) {
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinate(piece.getLoc().xCoord + 1, piece.getLoc().yCoord));
        possibleMoves.add(new Coordinate(piece.getLoc().xCoord - 1, piece.getLoc().yCoord));
        possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord + 1));
        possibleMoves.add(new Coordinate(piece.getLoc().xCoord, piece.getLoc().yCoord - 1));
        return possibleMoves;
    }

    public ArrayList<Coordinate> getAllowedMoves(Piece piece) {
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        return possibleMoves;
    }

    public ArrayList<Piece> getWhiteLostPieces() {
        return whiteLostPieces;
    }

    public void setWhiteLostPieces(ArrayList<Piece> whiteLostPieces) {
        this.whiteLostPieces = whiteLostPieces;
    }

    public ArrayList<Piece> getBlackLostPieces() {
        return blackLostPieces;
    }

    public void setBlackLostPieces(ArrayList<Piece> blackLostPieces) {
        this.blackLostPieces = blackLostPieces;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public boolean checkForMate(int attackingTeam, Coordinate defendingKingLoc) {
        ArrayList<Coordinate> attackingPiecesMoves = new ArrayList<>();
        Set<Coordinate> possibleKingLocations = new HashSet<>();
        possibleKingLocations.add(defendingKingLoc);
        possibleKingLocations.addAll(getAllowedMoves(squares[defendingKingLoc.xCoord][defendingKingLoc.yCoord].getHeldPiece()));
        int amtOfPossibleMoves = possibleKingLocations.size();
        for (Square[] col : squares) {
            for (Square row : col) {
                if (row.getHeldPiece() != null) {
                    if (row.getHeldPiece().getTeam() == attackingTeam) {
                        for (Coordinate loc : getAllowedMoves(row.getHeldPiece())) {
                            for (Coordinate kingLoc: possibleKingLocations) {
                                if (loc.xCoord == kingLoc.xCoord && loc.yCoord == kingLoc.xCoord) {
                                    amtOfPossibleMoves--;
                                    possibleKingLocations.remove(kingLoc);
                                }
                            }
                        }

                    }
                }
                if (amtOfPossibleMoves == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkForCheck(int attackingTeam, Coordinate defendingKingLoc) {
        for (Square[] col: squares) {
            for (Square row: col) {
                if (row.getHeldPiece() != null) {
                    if (row.getHeldPiece().getTeam() == attackingTeam){
                        if (getAllowedMoves(row.getHeldPiece()).contains(defendingKingLoc)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }
}