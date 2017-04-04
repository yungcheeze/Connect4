package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.ColumnFullException;
import Connect_Four.Exceptions.InvalidPieceException;

/**
 * Class used to generate ASCII visual representation of the board
 */
public class BoardPrinter {

    public BoardPrinter(){
    }


    public void printBoard(Board board){
        System.out.println(getBoardString(board));
    }

    public String getBoardString(Board board){
        int sizeV = board.getSizeV();
        int sizeH = board.getSizeH();
        int[][] boardArray = board.getboardArray();
        String[] rows = new String[sizeV + 2];

        for (int i = 0; i < 2; i++) {
            if(i == 0) {
                String row = " ";
                for (int h = 0; h < sizeH; h++) {
                    String piece = String.format(" %d ", h);
                    row = row.concat(piece);
                }
                row = row.concat(" ");
                rows[rows.length - 1] = row;
            } else {
                String row = "|";
                for (int h = 0; h < sizeH; h++) {
                    String piece = "---";
                    row = row.concat(piece);
                }
                row = row.concat("|");
                rows[rows.length - 2] = row;
            }
        }

        for (int v = 0; v < sizeV; v++) {
            String row = "|";
            for (int h = 0; h < sizeH; h++) {
                String piece = String.format(" %d ", boardArray[h][v]);
                row = row.concat(piece);
            }
            row = row.concat("|");
            row = row.replace("0", " ");
            rows[Math.abs(v - sizeV + 1)] = row;
        }
        return String.join("\n", rows);
    }


}
