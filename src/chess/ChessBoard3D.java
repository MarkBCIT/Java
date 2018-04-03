package chess;

import java.awt.Point;
import java.util.ArrayList;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class ChessBoard3D extends Board {

    public Board[] board = new ChessBoard[3];
    private ArrayList<Point3D> range;

    @Override
    public void startgame() {
        board[0].startgame();
    }

    @Override
    public void listen() {
        for (int i = 0; i < BOARD; i++) {
            for (int j = 0; j < BOARD; j++) {
                Square s = board[0].list[i][j];
                s.setOnMouseClicked(e -> {
                    move(s.getX(), s.getY(), 0);
                });
                Square s1 = board[1].list[i][j];
                s1.setOnMouseClicked(e -> {
                    move(s.getX(), s1.getY(), 1);
                });
                Square s2 = board[2].list[i][j];
                s2.setOnMouseClicked(e -> {
                    move(s.getX(), s2.getY(), 2);
                });

            }
        }
    }

    public ChessBoard3D() {
        board[0] = new ChessBoard();
        board[0].setStyle("-fx-border-color: green;" + "-fx-border-width: 10;");
        board[1] = new ChessBoard();
        board[1].setStyle("-fx-border-color: red;" + "-fx-border-width: 10;");
        board[2] = new ChessBoard();
        board[2].setStyle(
                "-fx-border-color: yellow;" + "-fx-border-width: 10;");
    }

    public void move(int x, int y, int z) {
        if (!moving) {
            if (board[z].list[x][y].hasPiece()) {
                if (board[z].list[x][y].getPiece().isColor() == turn) {
                    pz = z;
                    px = x;
                    py = y;
                    moving = true;

                    if (board[z].list[px][py].getPiece().getP() == "P") {
                        range = Prange(px, py, z);

                    } else if (board[z].list[px][py].getPiece().getP() == "R") {
                        range = Rrange(px, py, z);

                    } else if (board[z].list[px][py].getPiece().getP() == "N") {
                        range = Nrange(px, py, z);

                    } else if (board[z].list[px][py].getPiece().getP() == "B") {
                        range = Brange(px, py, z);

                    } else if (board[z].list[px][py].getPiece().getP() == "K") {
                        range = Krange(px, py, z);

                    } else if (board[z].list[px][py].getPiece().getP() == "Q") {
                        range = Qrange(px, py, z);

                    }
                    paintmove();
                }

            }
        } else {
            if (x == px && y == py && z == pz) {
                moving = false;
                paintstop();
            } else {
                for (int i = 0; i < range.size(); i++) {
                    if (x == range.get(i).getX() && y == range.get(i).getY()
                            && z == range.get(i).getZ()) {
                        board[pz].list[px][py].getPiece().firstmove = false;
                        board[z].list[x][y]
                                .setPiece(board[pz].list[px][py].getPiece());
                        board[pz].list[px][py].setPiece(null);
                        moving = false;
                        turn = !turn;
                        paintstop();
                    }
                }
            }
        }
    }

    @Override
    public void paintmove() {
        // TODO Auto-generated method stub
        for (int i = 0; i < range.size(); i++) {
            board[(int) range.get(i).getZ()].list[(int) range.get(i)
                    .getX()][(int) range.get(i).getY()].rm
                            .setFill(Color.YELLOW);
        }

    }

    @Override
    public void paintstop() {
        for (int i = 0; i < range.size(); i++) {
            board[(int) range.get(i).getZ()].list[(int) range.get(i)
                    .getX()][(int) range.get(i).getY()].rm
                            .setFill(Color.TRANSPARENT);
        }

    }

    public ArrayList<Point3D> Rrange(int px, int py, int pz) {
        ArrayList<Point3D> range = new ArrayList<Point3D>();
        ArrayList<Point> zrange = new ArrayList<Point>();
        range.add(new Point3D(px, py, pz));
        board[pz].turn = this.turn;
        zrange = ((ChessBoard) board[pz]).Rrange(px, py);
        for (int i = 0; i < zrange.size(); i++) {
            range.add(new Point3D(zrange.get(i).getX(), zrange.get(i).getY(),
                    pz));
        }
        if (pz - 1 >= 0) {
            addpoint(range, px, py + 1, pz - 1);
            addpoint(range, px, py - 1, pz - 1);
            addpoint(range, px + 1, py, pz - 1);
            addpoint(range, px - 1, py, pz - 1);
            if (pz - 2 >= 0) {
                addpoint(range, px, py + 2, pz - 2);
                addpoint(range, px, py - 2, pz - 2);
                addpoint(range, px + 2, py, pz - 2);
                addpoint(range, px - 2, py, pz - 2);
            }
        }
        if (pz + 1 <= 2) {
            addpoint(range, px, py + 1, pz + 1);
            addpoint(range, px, py - 1, pz + 1);
            addpoint(range, px + 1, py, pz + 1);
            addpoint(range, px - 1, py, pz + 1);
            if (pz + 2 <= 2) {
                addpoint(range, px, py + 2, pz + 2);
                addpoint(range, px, py - 2, pz + 2);
                addpoint(range, px + 2, py, pz + 2);
                addpoint(range, px - 2, py, pz + 2);
            }
        }

        return range;
    }

    public ArrayList<Point3D> Nrange(int px, int py, int pz) {
        ArrayList<Point3D> range = new ArrayList<Point3D>();
        range.add(new Point3D(px, py, pz));
        ArrayList<Point> zrange = new ArrayList<Point>();
        range.add(new Point3D(px, py, pz));
        board[pz].turn = this.turn;
        zrange = ((ChessBoard) board[pz]).Nrange(px, py);
        for (int i = 0; i < zrange.size(); i++) {
            range.add(new Point3D(zrange.get(i).getX(), zrange.get(i).getY(),
                    pz));
        }
        if (pz - 2 >= 0) {
            addpoint(range, px - 1, py - 2, pz - 2);
            addpoint(range, px - 2, py - 1, pz - 2);
            addpoint(range, px - 2, py + 1, pz - 2);
            addpoint(range, px - 1, py + 2, pz - 2);
            addpoint(range, px + 1, py + 2, pz - 2);
            addpoint(range, px + 2, py + 1, pz - 2);
            addpoint(range, px + 2, py - 1, pz - 2);
            addpoint(range, px + 1, py - 2, pz - 2);
        }
        if (pz + 2 <= 2) {
            addpoint(range, px - 1, py - 2, pz + 2);
            addpoint(range, px - 2, py - 1, pz + 2);
            addpoint(range, px - 2, py + 1, pz + 2);
            addpoint(range, px - 1, py + 2, pz + 2);
            addpoint(range, px + 1, py + 2, pz + 2);
            addpoint(range, px + 2, py + 1, pz + 2);
            addpoint(range, px + 2, py - 1, pz + 2);
            addpoint(range, px + 1, py - 2, pz + 2);
        }
        return range;
    }

    public ArrayList<Point3D> Brange(int px, int py, int pz) {
        ArrayList<Point3D> range = new ArrayList<Point3D>();
        range.add(new Point3D(px, py, pz));
        ArrayList<Point> zrange = new ArrayList<Point>();
        range.add(new Point3D(px, py, pz));
        board[pz].turn = this.turn;
        zrange = ((ChessBoard) board[pz]).Brange(px, py);
        for (int i = 0; i < zrange.size(); i++) {
            range.add(new Point3D(zrange.get(i).getX(), zrange.get(i).getY(),
                    pz));
        }
        if (pz - 1 >= 0) {
            addpoint(range, px + 1, py + 1, pz - 1);
            addpoint(range, px + 1, py - 1, pz - 1);
            addpoint(range, px - 1, py + 1, pz - 1);
            addpoint(range, px - 1, py - 1, pz - 1);
            if (pz - 2 >= 0) {
                addpoint(range, px + 2, py + 2, pz - 2);
                addpoint(range, px + 2, py - 2, pz - 2);
                addpoint(range, px - 2, py + 2, pz - 2);
                addpoint(range, px - 2, py - 2, pz - 2);
            }
        }
        if (pz + 1 <= 2) {
            addpoint(range, px + 1, py + 1, pz + 1);
            addpoint(range, px + 1, py - 1, pz + 1);
            addpoint(range, px - 1, py + 1, pz + 1);
            addpoint(range, px - 1, py - 1, pz + 1);
            if (pz + 2 <= 2) {
                addpoint(range, px + 2, py + 2, pz + 2);
                addpoint(range, px + 2, py - 2, pz + 2);
                addpoint(range, px - 2, py + 2, pz + 2);
                addpoint(range, px - 2, py - 2, pz + 2);
            }
        }
        return range;
    }

    public ArrayList<Point3D> Krange(int px, int py, int pz) {
        ArrayList<Point3D> range = new ArrayList<Point3D>();
        range.add(new Point3D(px, py, pz));
        ArrayList<Point> zrange = new ArrayList<Point>();
        range.add(new Point3D(px, py, pz));
        board[pz].turn = this.turn;
        zrange = ((ChessBoard) board[pz]).Krange(px, py);
        for (int i = 0; i < zrange.size(); i++) {
            range.add(new Point3D(zrange.get(i).getX(), zrange.get(i).getY(),
                    pz));
        }
        if (pz - 1 >= 0) {
            addpoint(range, px + 1, py - 1, pz - 1);
            addpoint(range, px + 1, py, pz - 1);
            addpoint(range, px + 1, py + 1, pz - 1);
            addpoint(range, px, py + 1, pz - 1);
            addpoint(range, px - 1, py + 1, pz - 1);
            addpoint(range, px - 1, py, pz - 1);
            addpoint(range, px - 1, py - 1, pz - 1);
            addpoint(range, px, py - 1, pz - 1);
        }
        if (pz + 1 <= 2) {
            addpoint(range, px + 1, py - 1, pz + 1);
            addpoint(range, px + 1, py, pz + 1);
            addpoint(range, px + 1, py + 1, pz + 1);
            addpoint(range, px, py + 1, pz + 1);
            addpoint(range, px - 1, py + 1, pz + 1);
            addpoint(range, px - 1, py, pz + 1);
            addpoint(range, px - 1, py - 1, pz + 1);
            addpoint(range, px, py - 1, pz + 1);
        }
        return range;
    }

    public ArrayList<Point3D> Qrange(int px, int py, int pz) {
        ArrayList<Point3D> range = new ArrayList<Point3D>();
        range.add(new Point3D(px, py, pz));
        ArrayList<Point> zrange = new ArrayList<Point>();
        range.add(new Point3D(px, py, pz));
        board[pz].turn = this.turn;
        zrange = ((ChessBoard) board[pz]).Qrange(px, py);
        for (int i = 0; i < zrange.size(); i++) {
            range.add(new Point3D(zrange.get(i).getX(), zrange.get(i).getY(),
                    pz));
        }
        if (pz - 1 >= 0) {
            addpoint(range, px + 1, py - 1, pz - 1);
            addpoint(range, px + 1, py, pz - 1);
            addpoint(range, px + 1, py + 1, pz - 1);
            addpoint(range, px, py + 1, pz - 1);
            addpoint(range, px - 1, py + 1, pz - 1);
            addpoint(range, px - 1, py, pz - 1);
            addpoint(range, px - 1, py - 1, pz - 1);
            addpoint(range, px, py - 1, pz - 1);
            if (pz - 2 >= 0) {
                addpoint(range, px + 2, py - 2, pz - 2);
                addpoint(range, px + 2, py, pz - 2);
                addpoint(range, px + 2, py + 2, pz - 2);
                addpoint(range, px, py + 2, pz - 2);
                addpoint(range, px - 2, py + 2, pz - 2);
                addpoint(range, px - 2, py, pz - 2);
                addpoint(range, px - 2, py - 2, pz - 2);
                addpoint(range, px, py - 2, pz - 2);
            }
        }
        if (pz + 1 <= 2) {
            addpoint(range, px + 1, py - 1, pz + 1);
            addpoint(range, px + 1, py, pz + 1);
            addpoint(range, px + 1, py + 1, pz + 1);
            addpoint(range, px, py + 1, pz + 1);
            addpoint(range, px - 1, py + 1, pz + 1);
            addpoint(range, px - 1, py, pz + 1);
            addpoint(range, px - 1, py - 1, pz + 1);
            addpoint(range, px, py - 1, pz + 1);
            if (pz + 2 <= 2) {
                addpoint(range, px + 2, py - 2, pz + 2);
                addpoint(range, px + 2, py, pz + 2);
                addpoint(range, px + 2, py + 2, pz + 2);
                addpoint(range, px, py + 2, pz + 2);
                addpoint(range, px - 2, py + 2, pz + 2);
                addpoint(range, px - 2, py, pz + 2);
                addpoint(range, px - 2, py - 2, pz + 2);
                addpoint(range, px, py - 2, pz + 2);
            }
        }
        return range;
    }

    public ArrayList<Point3D> Prange(int px, int py, int pz) {
        ArrayList<Point3D> range = new ArrayList<Point3D>();
        range.add(new Point3D(px, py, pz));
        if (turn) {
            if (board[pz].list[px][py].getPiece().firstmove) {
                if (!board[pz].list[px][py - 1].hasPiece()) {
                    range.add(new Point3D(px, py - 1, pz));
                    if (!board[pz].list[px][py - 2].hasPiece()) {
                        range.add(new Point3D(px, py - 2, pz));
                    }
                }
                if (!board[pz + 1].list[px][py - 1].hasPiece()) {
                    range.add(new Point3D(px, py - 1, pz + 1));
                }
                if (!board[pz + 2].list[px][py - 2].hasPiece()) {
                    range.add(new Point3D(px, py - 2, pz + 2));
                }
            } else {
                if (!board[pz].list[px][py - 1].hasPiece()) {
                    range.add(new Point3D(px, py - 1, pz));
                }
                if (pz - 1 >= 0 && !board[pz - 1].list[px][py - 1].hasPiece()) {
                    range.add(new Point3D(px, py - 1, pz - 1));
                }
                if (pz + 1 <= 2 && !board[pz + 1].list[px][py - 1].hasPiece()) {
                    range.add(new Point3D(px, py - 1, pz + 1));
                }
            }

        } else {
            if (board[pz].list[px][py].getPiece().firstmove) {
                if (!board[pz].list[px][py + 1].hasPiece()) {
                    range.add(new Point3D(px, py + 1, pz));
                    if (!board[pz].list[px][py + 2].hasPiece()) {
                        range.add(new Point3D(px, py + 2, pz));
                    }
                }
                if (!board[pz + 1].list[px][py + 1].hasPiece()) {
                    range.add(new Point3D(px, py + 1, pz + 1));
                }
                if (!board[pz + 2].list[px][py + 2].hasPiece()) {
                    range.add(new Point3D(px, py + 2, pz + 2));
                }
            } else {
                if (!board[pz].list[px][py + 1].hasPiece()) {
                    range.add(new Point3D(px, py + 1, pz));
                }
                if (pz - 1 >= 0 && !board[pz - 1].list[px][py + 1].hasPiece()) {
                    range.add(new Point3D(px, py + 1, pz - 1));
                }
                if (pz + 1 <= 2 && !board[pz + 1].list[px][py + 1].hasPiece()) {
                    range.add(new Point3D(px, py + 1, pz + 1));
                }
            }
        }
        return range;
    }

    public boolean addpoint(ArrayList<Point3D> range, int x, int y, int z) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        if (!board[z].list[x][y].hasPiece()) {
            range.add(new Point3D(x, y, z));
            return true;
        } else if (board[z].list[x][y].getPiece().isColor() != turn) {
            range.add(new Point3D(x, y, z));
            return false;
        }
        return false;
    }
}
