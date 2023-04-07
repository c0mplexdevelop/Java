package game.checkers;

public interface Piece {
    public int getRow();

    public void setRow(int row);

    public int getCol();

    public void setCol(int col);

    public void setKing(boolean bool);

    public void move(int row, int col);

    public void moveDirection(String direction) throws InvalidPositionException;

    public void moveDirectionAsKing(String direction, int spaces) throws InvalidPositionException;

    public boolean eatEnemyPiece(int row, int col);

    public boolean isEqual(Piece piece);
}
