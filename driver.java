public class driver{
  public static void main(String[] args){
    KnightBoard board = new KnightBoard(5,5); //create a 5X5 size board
    System.out.println(board);
    System.out.println("Testing solveH() in a new board");
    System.out.println("" + board.solve(2,2));
    System.out.println(board);
    /*System.out.println("Testing addKnight in a new board");
    board.addKnight(2,3,1);
    System.out.println(board);*/
    System.out.println("Testing countSolutions in a new board");
    System.out.println(board.countSolutions(0,0));
    System.out.println(board);
    runTest(2);
    Coordinate coor = new Coordinate(5,5); //create a 5X5 size board
    System.out.println(coor);
    System.out.println("Testing removeMove() in a new board");
    coor.reduceMove(0,0);
    System.out.println(coor);
    System.out.println("Testing restoreMove() in a new board");
    coor.restoreMove(0,0);
    System.out.println(coor);

    KnightBoard board1 = new KnightBoard(10,10); //create a 5X5 size board
    System.out.println(board1);
    System.out.println("Testing solveOpti() in a new board");
    System.out.println("" + board1.opti(0,0));
    //System.out.println(board1);
  }

  //testcase must be a valid index of your input/output array
  public static void runTest(int i){

    KnightBoard b;
    int[]m =   {4,5,5,5,5};
    int[]n =   {4,5,4,5,5};
    int[]startx = {0,0,0,1,2};
    int[]starty = {0,0,0,1,2};
    int[]answers = {0,304,32,56,64};
    if(i >= 0 ){
      try{
        int correct = answers[i];
        b = new KnightBoard(m[i%m.length],n[i%m.length]);

        int ans  = b.countSolutions(startx[i],starty[i]);

        if(correct==ans){
          System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
        }else{
          System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
        }
      }catch(Exception e){
        System.out.println("FAIL Exception case: "+i);

      }
    }
  }
}
