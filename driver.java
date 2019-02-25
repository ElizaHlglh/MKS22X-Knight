public class driver{
  public static void main(String[] args){
    KnightBoard board = new KnightBoard(5,5); //create a 5X5 size board
    System.out.println(board);
    System.out.println("Testing solveH() in a new board");
    board.solve(2,2);
    System.out.println(board);



  }
}
