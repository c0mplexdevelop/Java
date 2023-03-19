package game.checkers;

public class WhitePiece implements Piece {
    private boolean king = false;
    private int currentRow;
    private int currentCol;

    public WhitePiece(int row, int col) {
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

    public void move(int row, int col) {
        this.currentRow = row;
        this.currentCol = col;
        return;
    }

    public void moveDirection(String direction) throws InvalidPositionException {
        if(direction.equalsIgnoreCase("left")) {
            if(this.currentRow % 2 == 0 && this.currentCol == 0) {
                throw new InvalidPositionException("The piece cannot go left!");
            }

            move(this.currentRow + 1, this.currentCol - 1);
            return;
        }

        if(this.currentRow % 2 != 0 && this.currentCol == 3) {
            throw new InvalidPositionException("The piece cannot go right!");
        }

        move(this.currentRow + 1, this.currentCol);
        return;
    }

    public void moveDirectionAsKing(String direction, int spaces) throws InvalidPositionException {
        return;
    }

    public boolean eatEnemyPiece(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        if (king) {
            return "KB";
        }
        return "BP";
    }
}
