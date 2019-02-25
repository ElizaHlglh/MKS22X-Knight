public class KnightBoard{
  private int[][] board;
  private int[] direction;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    try{
      board = new int[startingRows][startingCols];
      for (int row = 0; row < board.length; row++){
        for (int col = 0; col < board[row].length; col++){
          board[row][col] = 0;
        }
      }
      direction = new int[]{2, -1, 2, 1, 1, 2, -1, 2, -2, 1, -2, -1, -2, -1, -2, 1};
    }
    catch(IllegalArgumentException e){
      System.out.println("CAN'T HAVE NEGATIVE BOARD SIZE");
    }
  }

      //Initialize the board to the correct size and make them all 0's

  public boolean addKnight(int row, int col, int lvl){
    try{
      if (board[row][col] != 0){
        return false;
      }
      else {
        board[row][col] = lvl;
        return true;
      }
    }
    catch(IndexOutOfBoundsException e){
      return false;
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

  public void clear(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        board[r][c] = 0;
      }
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
    for (int row = 0; row < board.length; row++){
      for (int col = 0; col < board[row].length; col++){
        addKnight(row,col,1);
        if (solveH(row, col, 1)){
          System.out.println(toString() + "one solution");
          return true;
        }
        clear();
      }
    }
    System.out.println("No solution");
    return false;
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
      System.out.println(toString() + "debug");
      return true; //this is a solution
    }
    else{
      //assumed the current one is added: try add the next one and see if it will
      for (int i = 0; i < direction.length; i= i+2){
        if (level > 23){
          System.out.println("" + level);
        }
        if (addKnight(row + direction[i], col + direction[i+1], level+1)){
          if (solveH(row + direction[i], col + direction[i+1], level+1)){
            return true;
          }
          else{
            rmKnight(row + direction[i], col + direction[i+1]);
          }
        }
      }
      /*
      if (addKnight(row, col, level)){ //if addable
        for (int i = 0; i < direction.length; i= i+2){
          if (row + direction[i] >= board.length || row + direction[i] < 0 || col + direction[i+1] >= board[row].length || col + direction[i+1] < 0){
            if (solveH(row + direction[i], col + direction[i+1], level+1)){
              System.out.println(toString());
              System.out.println("debug");
              return true;
            }
            else{
              System.out.println("debug2");
              rmKnight(row,col);
            }
          }
        }
        System.out.println("debug3");
        rmKnight(row,col);
      }*/
      return false;
    }
  }

}
