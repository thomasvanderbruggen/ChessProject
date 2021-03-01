import Board.ChessBoard;
import Pieces.*;
import Resources.Coordinate;
import Resources.Square;

import java.sql.SQLOutput;

public class test {
    public static void main (String[] args) {
        ChessBoard cb = new ChessBoard();
        Square[][] squares = cb.getSquares();
        String output = "";
        for (Square[] col: squares) {
            for (Square row: col) {
                if (row.getHeldPiece() == null) {
                    output += "x";
                }else {
                    if (row.getHeldPiece().getClass() == King.class) {
                        output += "K";
                    }else if (row.getHeldPiece().getClass() == Queen.class) {
                        output += "Q";
                    } else if (row.getHeldPiece().getClass() == Rook.class) {
                        output += "R";
                    } else if (row.getHeldPiece().getClass() == Bishop.class) {
                        output += "B";
                    } else if (row.getHeldPiece().getClass() == Knight.class) {
                        output += "N";
                    } else if (row.getHeldPiece().getClass() == Pawn.class) {
                        output += "P";
                    }
                }
            output += " ";
            }
            output += "\n";
        }
        System.out.println(output);
        Square square = squares[0][0];
        output = "";
        if (square.getHeldPiece().getClass() == King.class) {
            output += "K";
        }else if (square.getHeldPiece().getClass() == Queen.class) {
            output += "Q";
        } else if (square.getHeldPiece().getClass() == Rook.class) {
            output += "R";
        } else if (square.getHeldPiece().getClass() == Bishop.class) {
            output += "B";
        } else if (square.getHeldPiece().getClass() == Knight.class) {
            output += "N";
        } else if (square.getHeldPiece().getClass() == Pawn.class) {
            output += "P";
        }
        System.out.println(output);
        System.out.println(square.getHeldPiece().getTeam());
        try {
            cb.movePiece(new Coordinate(1, 0), new Coordinate(2, 0));
        }catch (Exception e) {

        }
        output = "";
        for (Square[] col: squares) {
            for (Square row: col) {
                if (row.getHeldPiece() == null) {
                    output += "x";
                }else {
                    if (row.getHeldPiece().getClass() == King.class) {
                        output += "K";
                    }else if (row.getHeldPiece().getClass() == Queen.class) {
                        output += "Q";
                    } else if (row.getHeldPiece().getClass() == Rook.class) {
                        output += "R";
                    } else if (row.getHeldPiece().getClass() == Bishop.class) {
                        output += "B";
                    } else if (row.getHeldPiece().getClass() == Knight.class) {
                        output += "N";
                    } else if (row.getHeldPiece().getClass() == Pawn.class) {
                        output += "P";
                    }
                }
                output += " ";
            }
            output += "\n";
        }
        System.out.println(output);
        if (squares[1][0].getHeldPiece() != null) {
            System.out.println("Fucked up :(");
        }
        try {
            cb.movePiece(new Coordinate(0,0), new Coordinate(1, 0));
        }catch (Exception e) {

        }
        output = "";
        for (Square[] col: squares) {
            for (Square row: col) {
                if (row.getHeldPiece() == null) {
                    output += "x";
                }else {
                    if (row.getHeldPiece().getClass() == King.class) {
                        output += "K";
                    }else if (row.getHeldPiece().getClass() == Queen.class) {
                        output += "Q";
                    } else if (row.getHeldPiece().getClass() == Rook.class) {
                        output += "R";
                    } else if (row.getHeldPiece().getClass() == Bishop.class) {
                        output += "B";
                    } else if (row.getHeldPiece().getClass() == Knight.class) {
                        output += "N";
                    } else if (row.getHeldPiece().getClass() == Pawn.class) {
                        output += "P";
                    }
                }
                output += " ";
            }
            output += "\n";
        }
        System.out.println(output);
    }
}
