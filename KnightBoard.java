public class KnightBoard{
  private int[][] board;
  //private int[] direction;
  private int[] moveRow;
  private int[] moveCol;
  private int count;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    try{
      if (startingRows <= 0 || startingCols <= 0){
        throw new IllegalArgumentException("Invalid board size");
      }
      board = new int[startingRows][startingCols];
      for (int row = 0; row < board.length; row++){
        for (int col = 0; col < board[row].length; col++){
          board[row][col] = 0;
        }
      }
      moveRow = new int[]{2, 2, 1, -1, -2, -2, 1, -1};
      moveCol = new int[]{1, -1, 2, 2, 1, -1, -2, -2};
      //direction = new int[]{2, -1, 2, 1, 1, 2, -1, 2, -2, 1, -2, -1, -2, -1, -2, 1};
      count = 0;
    }
    catch(IllegalArgumentException e){
      System.out.println("Invalid board size");
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

  private boolean isClean(){
    for (int row = 0; row < board.length; row++){
      for (int col = 0; col < board[row].length; col++){
        if (board[row][col] != 0){
          return false;
        }
      }
    }
    return true;
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
    try{
      //exceptions:
      if (!isClean()){
        throw new IllegalStateException("Board isn't clean!!!");
      }
      if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length){
        throw new IllegalArgumentException("Invalid index!!!");
      }
      //actual code:
      if (addKnight(startingRow, startingCol, 1)){
        if (solveH(startingRow, startingCol, 1)){
          System.out.println(toString());
          clear();
          return true;
        }
      }
      clear();
      //System.out.println("No solution");
      return false;
    }
    catch(IllegalStateException e){
      return false;
    }
    catch(IllegalArgumentException e){
      return false;
    }
  }

/*  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  public int countSolutions(int startingRow, int startingCol){
    try{
      //exceptions:
      if (!isClean()){
        throw new IllegalStateException("Board isn't clean!!!");
      }
      if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length){
        throw new IllegalArgumentException("Invalid index!!!");
      }
      //actual code:
      count = 0;
      int ans = 0;
      addKnight(startingRow, startingCol, 1);
      solveAll(startingRow, startingCol, 1);
      clear();
      ans = count;
      count = 0;
      return ans;
    }
    catch(IllegalStateException e){
      return 0;
    }
    catch(IllegalArgumentException e){
      return 0;
    }
  }

  private boolean solveAll(int row ,int col, int level){
    if (level == board.length * board[row].length){ //check if reach the last value
      count++;
      return true; //this is a solution
    }
    else{
      for (int i = 0; i < 8; i++){
        if (addKnight(row + moveRow[i], col + moveCol[i], level+1)){
          solveAll(row + moveRow[i], col + moveCol[i], level+1);
          rmKnight(row + moveRow[i], col + moveCol[i]);
        }
      }
      return false;
    }
  }

  //Suggestion:
  private boolean solveH(int row ,int col, int level){
    if (level == board.length * board[row].length){ //check if reach the last value
      return true; //this is a solution
    }
    else{
      for (int i = 0; i < 8; i++){
        if (addKnight(row + moveRow[i], col + moveCol[i], level+1)){
          if (solveH(row + moveRow[i], col + moveCol[i], level+1)){
            return true;
          }
          else{
            rmKnight(row + moveRow[i], col + moveCol[i]);
          }
        }
      }
      return false;
    }
  }

/*Optimazation : a coordinate class that keep track of the number of possible moves each coordinate has.
  1. a coordinate class that store the number of possible moves that coordinate has.
  2. create an array that keeps the coordinates and arranged in a way that the one with the least possible move go first.
*/
}
