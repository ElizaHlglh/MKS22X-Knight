public class driver{
  public static void main(String[] args){
    KnightBoard board1 = new KnightBoard(5,5); //create a 5X5 size board
    System.out.println(board1);
    System.out.println("Testing solveH() in a new board");
    board1.solve(2,2);
    System.out.println(board1);



  }
}
