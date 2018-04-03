package chess;


import javafx.scene.layout.GridPane;

public abstract class Board extends GridPane {
    /**
     * Size of chess board.
     */
    public static final int BOARD = 8;
    /**
     * check if some piece is moving.
     */
    public boolean moving = false;
    /**
     * Record previous clicked position x.
     */
    protected int px;
    /**
     * Record previous clicked position y.
     */
    protected int py;
    /**
     * Record previous clicked position z.
     */
    protected int pz;
    /**
     * Set turn.
     */
    public boolean turn=true;
    /**
     * Set squares list for chessboard.
     */
    public Square[][] list;
    /**
     * Start the game, place the pieces.
     */
    public abstract void startgame();
    /**
     * Set mouse click listeners for chess board.
     */
    public abstract void listen();
    /**
     * paint the movable range for moving piece.
     */
    public abstract void paintmove();
    /**
     * Stop painting for movable range.
     */
    public abstract void paintstop();


}
