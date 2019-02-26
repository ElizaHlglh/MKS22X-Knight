public class Coordinate{
  private int[][] move;
  private int[] moveRow;
  private int[] moveCol;

  public Coordinate(int Row, int Col){
    move = new int[Row][Col];
    moveRow = new int[]{2, 2, 1, -1, -2, -2, 1, -1};
    moveCol = new int[]{1, -1, 2, 2, 1, -1, -2, -2};

    for (int row = 0; row < move.length; row++){
      for (int col = 0; col < move[row].length; col++){
        //for every point, find their possible moves:
        int num = 0;
        for (int i = 0; i < 8; i++){
          if (!(row + moveRow[i] < 0 || col + moveCol[i] < 0 || row + moveRow[i] >= move.length || col + moveCol[i] >= move[0].length)){
            num++; //collect the number of possible moves
          }
        }
        move[row][col] = num;
        num = 0;
      }
    }
  }

  public String toString(){
    String ans = "";
    for (int row = 0; row < move.length; row++){
      for (int col = 0; col < move[row].length; col++){
        ans += move[row][col] + " ";
      }
      ans += "\n";
    }
    return ans;
  }

  public int getMove(int row, int col){
    return move[row][col];
  }

  public void reduceMove(int row, int col){
    for (int i = 0; i < 8; i++){
      if (!(row + moveRow[i] < 0 || col + moveCol[i] < 0 || row + moveRow[i] >= move.length || col + moveCol[i] >= move[0].length)){
        move[row + moveRow[i]][col + moveCol[i]] -= 1;
      }
    }
    move[row][col] = -1;
  }

  public void restoreMove(int row, int col){
    for (int i = 0; i < 8; i++){
      if (!(row + moveRow[i] < 0 || col + moveCol[i] < 0 || row + moveRow[i] >= move.length || col + moveCol[i] >= move[0].length)){
        if (move[row + moveRow[i]][col + moveCol[i]] >= 0){
          move[row + moveRow[i]][col + moveCol[i]] += 1;
        }
      }
    }
    int num = 0;
    for (int i = 0; i < 8; i++){
      if (!(row + moveRow[i] < 0 || col + moveCol[i] < 0 || row + moveRow[i] >= move.length || col + moveCol[i] >= move[0].length)){
        num++; //collect the number of possible moves
      }
    }
    move[row][col] = num;
  }

  public void reset(){
    for (int row = 0; row < move.length; row++){
      for (int col = 0; col < move[row].length; col++){
        //for every point, find their possible moves:
        int num = 0;
        for (int i = 0; i < 8; i++){
          if (!(row + moveRow[i] < 0 || col + moveCol[i] < 0 || row + moveRow[i] >= move.length || col + moveCol[i] >= move[0].length)){
            num++; //collect the number of possible moves
          }
        }
        move[row][col] = num;
        num = 0;
      }
    }
  }
}
