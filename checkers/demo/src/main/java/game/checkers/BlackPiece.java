package game.checkers;

public class BlackPiece implements Piece {
    private boolean king = false;
    private int currentRow;
    private int currentCol;

    public BlackPiece(int row, int col) {
        this.currentRow = row;
        this.currentCol = col;
    }

    public int getRow() {
        return this.currentRow;
    }

    public void setRow(int row) {
        this.currentRow = row;
    }

    public int getCol() {
        return this.currentCol;
    }

    public void setCol(int col) {
        this.currentCol = col;
    }

    public void setKing(boolean bool) {
        this.king = bool;
    }

    @Override
    public String toString() {
        if (king) {
            return "KW";
        }
        return "WP";
    }

    public boolean isEqual(Piece piece) {
        int pieceRow = piece.getRow();
        int pieceCol = piece.getCol();
        if(!(piece instanceof BlackPiece)) {
            return false;
        }

        return currentRow == pieceRow && currentCol == pieceCol;
    }
}
