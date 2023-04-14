package game.checkers;

public interface Piece {
    int getRow();

    void setRow(int row);

    int getCol();

    void setCol(int col);

    void setKing(boolean bool);

    boolean isEqual(Piece piece);
}
