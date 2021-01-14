import java.util.*;

public class AIAgent {
  Random rand;

  public AIAgent() {
    rand = new Random();
  }

  /*
   * The method randomMove takes as input a stack of potential moves that the AI
   * agent can make. The agent uses a rondom number generator to randomly select a
   * move from the inputted Stack and returns this to the calling agent.
   */

  public Move randomMove(Stack possibilities) {

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : " + moveID);
    for (int i = 1; i < (possibilities.size() - (moveID)); i++) {
      possibilities.pop();
    }
    Move selectedMove = (Move) possibilities.pop();
    return selectedMove;
  }

  /////////////////////////////////////////////// Next Best Move /////////////////////////////////////////

  public Move nextBestMove(Stack whitePossibilities, Stack blackPossibilities) {
    Stack white = (Stack) whitePossibilities.clone();
    Stack blackStack = (Stack) blackPossibilities.clone();
    Move whiteMove, normalMove, bestMove;
    Square positionBlack;
    double points = 0.0;
    double selectedPiece = 0.0;
    bestMove = null;

    while (!whitePossibilities.empty()) { 
      whiteMove = (Move) whitePossibilities.pop(); 
      normalMove = whiteMove; 

      // Center squares most valuable on the board so make first move
      if ((normalMove.getStart().getYC() < normalMove.getLanding().getYC()) 
          && (normalMove.getLanding().getXC() == 3) && (normalMove.getLanding().getYC() == 3)
          || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 3)
          || (normalMove.getLanding().getXC() == 3) && (normalMove.getLanding().getYC() == 4)
          || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 4)) {
        points = 0.5;

        // Choosing best move
        if (points > selectedPiece) {// If points to be gained are more valuable than piece
          selectedPiece = points;
          bestMove = normalMove; // Best move is now normal move
        }
      }

      while (!blackStack.isEmpty()) { 
        points = 0;// Points start at 0
        positionBlack = (Square) blackStack.pop();
        if ((normalMove.getLanding().getXC() == positionBlack.getXC())
          && (normalMove.getLanding().getYC() == positionBlack.getYC())) {

          // Giving value to pieces
          if (positionBlack.getName().equals("BlackPawn")) {
            points = 1;
          } else if (positionBlack.getName().equals("BlackBishop") || positionBlack.getName().equals("BlackKnight")) {
            points = 3;
          } else if (positionBlack.getName().equals("BlackRook")) {
            points = 5;
          } else if (positionBlack.getName().equals("BlackQueen")) {
            points = 9;
          } else if (positionBlack.getName().equals("BlackKing")) {
            points = 10;
            System.out.println("you have won");
          }
        }

        //Choosing next best move 
        if (points > selectedPiece) {
          selectedPiece = points;
          bestMove = normalMove;
        }
      }
      //Reset list of moves
      blackStack = (Stack) blackPossibilities.clone();
    }

    // Select best move when available or use random if no best available
    if (selectedPiece > 0) {
      System.out.println("Best move: " + selectedPiece);
      return bestMove;
    }
    System.out.println("random move");
    return randomMove(white);

  }

  ////////////////////////////// Two Levels Deep///////////////////////////

  public Move twoLevelsDeep(Stack possibilities) {
    Move selectedMove = new Move();
    return selectedMove;
  }
}