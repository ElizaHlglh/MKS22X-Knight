import java.util.*;
public class KnightBoard{
  private int[][] board;
  //private int[] direction;
  private int[] moveRow;
  private int[] moveCol;
  private int count;
  private Coordinate move; //the new board with number of moves each coordinate has


  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    try{
      if (startingRows <= 0 || startingCols <= 0){
        throw new IllegalArgumentException("Invalid board size");
      }
      move = new Coordinate(startingRows, startingCols);
      System.out.println(move);
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
  3. If moved to new place, update the PossiMove. Remove 1 from future coordi when reach a new coordi.
*/

  public boolean opti(int startingRow, int startingCol){
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
        move.reduceMove(startingRow, startingCol);
        if (solveOpti(startingRow, startingCol, 1)){
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

  private boolean solveOpti(int row ,int col, int level){
    if (level == board.length * board[row].length){ //check if reach the last value
      return true; //this is a solution
    }
    else{
      ArrayList<Integer> RowList = new ArrayList<Integer>();
      ArrayList<Integer> ColList = new ArrayList<Integer>();
      for (int i = 0; i < 8; i++){
        //loop through to create an array of reachable coordinates and sorted the array based on the moves.
        if (!(row + moveRow[i] < 0 || col + moveCol[i] < 0 || row + moveRow[i] >= board.length || col + moveCol[i] >= board[0].length)){
          RowList.add(row + moveRow[i]);//store the coordinates of possible future moves
          ColList.add(col + moveCol[i]);
        }
      }
      //arrange the future coordinates based on their moves:
      for (int k = 0; k < RowList.size(); k++){
        int minLoc = k;
        //loop to find the smallest first
        for (int j = k+1; j < RowList.size(); j++){
          if (move.getMove(RowList.get(j), ColList.get(j)) < move.getMove(RowList.get(minLoc), ColList.get(minLoc))){
            minLoc = j; // find the coordinate of one with the least move
          }
        }
        int frontValueR = RowList.get(k); //store the value of ary
        int frontValueC = ColList.get(k);
        //replace the value of ary with the smallest
        RowList.set(k, RowList.get(minLoc));
        ColList.set(k, ColList.get(minLoc));
        //switch place between the smallest and the frontValue
        RowList.set(minLoc, RowList.get(frontValueR));
        ColList.set(k, ColList.get(frontValueC));
      }
      //NOW, loop through the (already sorted) possible future coordinates
      for (int z = 0; z < RowList.size(); z++){
        if (addKnight(row + moveRow[z], col + moveCol[z], level+1)){
          move.reduceMove(row + moveRow[z], col + moveCol[z]);
          if (solveOpti(row + moveRow[z], col + moveCol[z], level+1)){
            return true;
          }
          else{
            rmKnight(row + moveRow[z], col + moveCol[z]);
            move.restoreMove(row + moveRow[z], col + moveCol[z]);
          }
        }
      }
      }
      return false;
  }
}
