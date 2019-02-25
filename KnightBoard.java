public class KnightBoard{
  private int[][] board;
  private int[] direction;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    try{
      int[][] board = new int[startingRows][startingCols];
      for (int row = 0; row < board.length; row++){
        for (int col = 0; col < board[row].length; col++){
          board[row][col] = 0;
        }
      }
      int[] direction = {2, -1, 2, 1, 1, 2, -1, 2, -2, 1, -2, -1, -2, -1, -2, 1};
    }
    catch(IllegalArgumentException e){
      System.out.println("CAN'T HAVE NEGATIVE BOARD SIZE");
    }
  }

      //Initialize the board to the correct size and make them all 0's

  public boolean addKnight(int row, int col, int lvl){
    if ((row < 0 && row >= board.length) || (col < 0 && col >= board[row].length)) {
      return false;
    }
    if (board[row][col] != 0){
      return false;
    }
    else {
      board[row][col] = lvl;
      return true;
    }
  }

  public boolean rmKnight(int row, int col){
    if (board[row][col] == 0){
      return false;
    }
    else {
      board[row][col] = 0;
      return true;
    }
  }

  public String toString(){
    String ans = "";
    if (board.length * board[0].length >= 10){
      for (int row = 0; row < board.length; row++){
        for (int col = 0; col < board[row].length; col++){
          if (board[row][col] < 10){
            ans += " " + board[row][col] + " ";
          }
          else{
            ans += board[row][col] + " ";
          }
        }
        ans += "\n";
      }
    }
    else{
      for (int row = 0; row < board.length; row++){
        for (int col = 0; col < board[row].length; col++){
          ans += board[row][col] + " ";
        }
        ans += "\n";
      }
    }
    return ans;
  }

/*  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  public boolean solve(int startingRow, int startingCol){
    return solveH(startingRow, startingCol, 0);
  }

/*  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  public int countSolutions(int startingRow, int startingCol){
    return 0;
  }

  //Suggestion:
  private boolean solveH(int row ,int col, int level){
    if (level == board.length * board[row].length){ //check if reach the last value
      return true; //this is a solution
    }
    else{
      //EDIT!!! Instead of current, try if this location can add, the check 8 other next sites (list of solveH(+/- , +/-, +) etc)
      if (addKnight(row, col, level)){ //if addable
        for (int i = 0; i < direction.length; i= i+2){
          if (solveH(row + direction[i], col + direction[i+1], level+1)){
            return true;
          }
          else{
            rmKnight(row,col);
          }
        }
      }
      return false;
    }
  }

}
