package chess;

import java.awt.Point;
import java.util.ArrayList;
import characters.Bishop;
import characters.King;
import characters.Knight;
import characters.Pawn;
import characters.Queen;
import characters.Rook;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Chessboard
 *
 * @author Mark
 * @version 2018
 */
public class ChessBoard extends Board {

    private ArrayList<Point> range;

    /**
     * 
     * Constructs an object of type Chessboard.
     */
    public ChessBoard() {
        list = new Square[BOARD][BOARD];
        for (int i = 0; i < BOARD; i++) {
            for (int j = 0; j < BOARD; j++) {
                Square s = new Square(i, j);

                list[i][j] = s;
                add(list[i][j], i * 100, j * 100);
            }
        }
        
    }

    public void listen() {
        for (int i = 0; i < BOARD; i++) {
            for (int j = 0; j < BOARD; j++) {
                Square s = list[i][j];
                s.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        move(s.getX(), s.getY());
                    }
                });
            }
        }
    }

    /**
     * places the pieces firstly.
     */
    public void startgame() {
        for (int i = 0; i < 8; i++) {
            list[i][1].setPiece(new Pawn(false));
            list[i][6].setPiece(new Pawn(true));
        }
        list[0][0].setPiece(new Rook(false));
        list[7][0].setPiece(new Rook(false));
        list[0][7].setPiece(new Rook(true));
        list[7][7].setPiece(new Rook(true));

        list[1][0].setPiece(new Knight(false));
        list[6][0].setPiece(new Knight(false));
        list[1][7].setPiece(new Knight(true));
        list[6][7].setPiece(new Knight(true));

        list[2][0].setPiece(new Bishop(false));
        list[5][0].setPiece(new Bishop(false));
        list[2][7].setPiece(new Bishop(true));
        list[5][7].setPiece(new Bishop(true));

        list[3][0].setPiece(new King(false));
        list[4][0].setPiece(new Queen(false));
        list[3][7].setPiece(new King(true));
        list[4][7].setPiece(new Queen(true));
    }

    /**
     * move method for pieces.
     * 
     * @param x
     * @param y
     */
    public void move(int x, int y) {
        if (!moving) {
            if (list[x][y].hasPiece()) {
                if (list[x][y].getPiece().isColor() == turn) {
                    px = x;
                    py = y;
                    moving = true;
                    if (list[px][py].getPiece().getP() == "P") {
                        range = Prange(px, py);

                    } else if (list[px][py].getPiece().getP() == "R") {
                        range = Rrange(px, py);

                    } else if (list[px][py].getPiece().getP() == "N") {
                        range = Nrange(px, py);

                    } else if (list[px][py].getPiece().getP() == "B") {
                        range = Brange(px, py);

                    } else if (list[px][py].getPiece().getP() == "K") {
                        range = Krange(px, py);

                    } else if (list[px][py].getPiece().getP() == "Q") {
                        range = Qrange(px, py);

                    }
                    paintmove();
                }

            }
        } else {
            if (x == px && y == py) {
                moving = false;
                paintstop();

            } else {
                for (int i = 0; i < range.size(); i++) {
                    if (x == range.get(i).getX() && y == range.get(i).getY()) {
                        list[px][py].getPiece().firstmove = false;
                        list[x][y].setPiece(list[px][py].getPiece());
                        list[px][py].setPiece(null);
                        moving = false;
                        turn = !turn;
                        paintstop();
                    }
                }
            }
        }
    }

    public void paintmove() {
        if (range != null) {
            for (int i = 0; i < range.size(); i++) {
                list[(int) range.get(i).getX()][(int) range.get(i).getY()].rm
                        .setFill(Color.YELLOW);
            }
        }

    }

    public void paintstop() {
        for (int i = 0; i < range.size(); i++) {
            list[(int) range.get(i).getX()][(int) range.get(i).getY()].rm
                    .setFill(Color.TRANSPARENT);
        }
    }

    public ArrayList<Point> Prange(int px, int py) {
        ArrayList<Point> range = new ArrayList<Point>();
        range.add(new Point(px, py));
        if (list[px][py].getPiece().firstmove) {
            if (list[px][py].getPiece().isColor()) {
                if (!list[px][py - 1].hasPiece()) {
                    range.add(new Point(px, py - 1));
                    if (!list[px][py - 2].hasPiece()) {
                        range.add(new Point(px, py - 2));
                    }
                }

            } else {
                if (!list[px][py + 1].hasPiece()) {
                    range.add(new Point(px, py + 1));
                    if (!list[px][py + 2].hasPiece()) {
                        range.add(new Point(px, py + 2));
                    }
                }
            }
        } else {
            if (list[px][py].getPiece().isColor()) {
                if (!list[px][py - 1].hasPiece()) {
                    range.add(new Point(px, py - 1));
                }

            } else {
                if (!list[px][py + 1].hasPiece()) {
                    range.add(new Point(px, py + 1));
                }

            }
        }
        return range;
    }

    public ArrayList<Point> Rrange(int px, int py) {
        ArrayList<Point> range = new ArrayList<Point>();
        range.add(new Point(px, py));
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px - i, py)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px, py - i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px + i, py)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px, py + i)) {
                break;
            }
        }
        return range;
    }

    public ArrayList<Point> Nrange(int px, int py) {
        ArrayList<Point> range = new ArrayList<Point>();
        range.add(new Point(px, py));
        addpoint(range, px - 1, py - 2);
        addpoint(range, px - 2, py - 1);
        addpoint(range, px - 2, py + 1);
        addpoint(range, px - 1, py + 2);
        addpoint(range, px + 1, py + 2);
        addpoint(range, px + 2, py + 1);
        addpoint(range, px + 2, py - 1);
        addpoint(range, px + 1, py - 2);
        return range;
    }

    public ArrayList<Point> Brange(int px, int py) {
        ArrayList<Point> range = new ArrayList<Point>();
        range.add(new Point(px, py));
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px - i, py - i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px + i, py - i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px + i, py + i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px - i, py + i)) {
                break;
            }
        }
        return range;
    }

    public ArrayList<Point> Krange(int px, int py) {
        ArrayList<Point> range = new ArrayList<Point>();
        range.add(new Point(px, py));
        addpoint(range, px - 1, py - 1);
        addpoint(range, px - 1, py);
        addpoint(range, px - 1, py + 1);
        addpoint(range, px, py + 1);
        addpoint(range, px + 1, py + 1);
        addpoint(range, px + 1, py);
        addpoint(range, px + 1, py - 1);
        addpoint(range, px, py - 1);
        return range;
    }

    public ArrayList<Point> Qrange(int px, int py) {
        ArrayList<Point> range = new ArrayList<Point>();
        range.add(new Point(px, py));
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px - i, py)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px, py - i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px + i, py)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px, py + i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px - i, py - i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px + i, py - i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px + i, py + i)) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (!addpoint(range, px - i, py + i)) {
                break;
            }
        }
        return range;
    }

    public boolean addpoint(ArrayList<Point> range, int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        if (!list[x][y].hasPiece()) {
            range.add(new Point(x, y));
            return true;
        } else if (list[x][y].getPiece().isColor() != turn) {
            range.add(new Point(x, y));
            return false;
        }
        return false;
    }

}
