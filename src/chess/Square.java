package chess;

import characters.Piece;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Square
 *
 * @author Mark
 * @version 2018
 */
public class Square extends Pane {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * record piece.
     */
    private Piece piece;
    /**
     * show piece by label.
     */
    private Label label = new Label();
    Rectangle r = new Rectangle(100, 100);
    Rectangle rm = new Rectangle(100, 100);

    /**
     * get the piece on this square.
     * 
     * @return piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * set the piece on this square and show it.
     * 
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        show();
    }

    /**
     * check if this square has piece.
     * 
     * @return
     */
    public boolean hasPiece() {
        return piece != null;
    }

    /**
     * 
     * Constructs an object of type Square.
     * 
     * @param x
     * @param y
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        rm.setFill(Color.TRANSPARENT);
        r.setFill(Color.WHITE);
        if (((int) (x + y)) % 2 == 1) {
            r.setFill(Color.BLACK);
        }
        getChildren().add(r);
        getChildren().add(rm);
        getChildren().add(label);
    }

    /**
     * show piece by text.
     */
    private void show() {
        if (hasPiece()) {
            label.setText(piece.getP());
            label.setFont(new Font("Arial", 80));
            if (!piece.isColor()) {
                label.setTextFill(Color.BLUE);
            }
            if (piece.isColor()) {
                label.setTextFill(Color.RED);
            }

        } else {
            label.setText(null);
        }

    }

}
