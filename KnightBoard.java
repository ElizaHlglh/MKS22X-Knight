public class KnightBoard{
  private int[][] board;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    try{
      int[][] board = new int[startingRows][startingCols];
      for (int row = 0; row < board.length; row++){
        for (int col = 0; col < board[row].length; col++){
          board[row][col] = 0;
        }
      }
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

  }

/*  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  public int countSolutions(int startingRow, int startingCol){

  }

  //Suggestion:
  private boolean solveH(int row ,int col, int level){
    if (level == board.length * board[row].length){ //check if reach the last value
      return true; //this is a solution
    }
    else{
      //EDIT!!! Instead of current, try if this location can add, the check 8 other next sites (list of solveH(+/- , +/-, +) etc)
      if ((row - 2 >= 0) && (col-1 >= 0 )) { //check up 2 left 1
        if (addKnight(row, col, level)){
          if (solveH(row - 2, col - 1, level + 1)){
            return true;
          }
        }
      }
    }
  }
  // level is the # of the knight

}
