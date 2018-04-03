package characters;

/**
 * Piece
 *
 * @author Mark
 * @version 2018
 */
public abstract class Piece{

    /**
     * record color.
     */
    protected final boolean color;
    /**
     * record text to show.
     */
    protected String p;
    public boolean firstmove = true;
    /**
     * get color.
     * 
     * @return
     */
    public boolean isColor() {
        return color;
    }

    /**
     * get text to show.
     * 
     * @return
     */
    public String getP() {
        return p;
    }

    /**
     * Constructs an object of type Piece.
     * 
     * @param color
     */
    public Piece(boolean color) {
        super();
        this.color = color;
    }

}
