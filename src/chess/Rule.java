package chess;

import java.awt.Point;
import java.util.ArrayList;

public class Rule {

    Square[][] list;
    boolean turn;

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setList(Square[][] list) {
        this.list = list;
    }

    public boolean Move(int x, int y, ArrayList<Point> range) {
        for (int i = 0; i < range.size(); i++) {
            if (range.get(i).getX() == x) {

            }
        }
        return false;

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
        if (list[px][py].getPiece().isColor()) {
            if (px != 0 && py != 0 && list[px - 1][py - 1].hasPiece()) {
                if (list[px - 1][py - 1].getPiece().isColor() != list[px][py]
                        .getPiece().isColor()) {
                    range.add(new Point(px - 1, py - 1));
                }
            }
            if (px != 7 && py != 0 && list[px + 1][py - 1].hasPiece()) {
                if (list[px + 1][py - 1].getPiece().isColor() != list[px][py]
                        .getPiece().isColor()) {
                    range.add(new Point(px + 1, py - 1));
                }
            }
        } else {
            if (px != 0 && py != 7 && list[px - 1][py + 1].hasPiece()) {
                if (list[px - 1][py + 1].getPiece().isColor() != list[px][py]
                        .getPiece().isColor()) {
                    range.add(new Point(px - 1, py + 1));
                }
            }
            if (px != 7 && py != 7 && list[px + 1][py + 1].hasPiece()) {
                if (list[px + 1][py + 1].getPiece().isColor() != list[px][py]
                        .getPiece().isColor()) {
                    range.add(new Point(px + 1, py + 1));
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

    private boolean addpoint(ArrayList<Point> range, int x, int y) {
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
