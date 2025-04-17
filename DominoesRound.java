import java.util.Scanner;

public class DominoesRound {
    
    Player player;
    Player computer;
    Stock stock = new Stock();
    Board board;

    public DominoesRound(String name){
        player = new Player(name);
        computer = new Player("Computer");
        Board board = new Board();
    }

    public void playRound(Scanner in){

        player.initializeHand(stock);
        computer.initializeHand(stock);

        boolean playerMove = true;
        boolean computerMove = true;

        boolean first = true;
        int firstIndex = -1;

        while (first) {
            firstIndex = in.nextInt();

            if (firstIndex >= 0 && firstIndex < 7) {
                first = false;
            }
        }
        
        Tile firstTile = player.getHand().getHand().get(firstIndex);
         

        while (!player.emptyHand() && !computer.emptyHand() && (playerMove || computerMove)) {

            
            System.out.println(board.toString());

            playerMove = player.play(stock, board, in);

            System.out.println(board.toString());

            computerMove = computer.computerPlay(stock, board);

            System.out.println("!player.emptyHand() is " + !player.emptyHand());
            System.out.println("!computer.emptyHand() is " + !computer.emptyHand());
            System.out.println("playerMove is " + playerMove);
            System.out.println("computerMove is " + computerMove);
        }

        if (player.emptyHand()) {
            System.out.println("Human won! Emptied Hand");
            player.collectPoints(computer);
            printPoints();            
        }
        else if (computer.emptyHand()) {
            System.out.println("Computer won! Emptied Hand");
            computer.collectPoints(computer);
            printPoints();
        }
        else{
            if (computer.getPoints() > player.getPoints()) {
                System.out.println("Computer won! No possible moves");

            }
        }
    }

    //helper methods
    private void printPoints(){
        System.out.println(player.getName() + " points:" + player.getPoints());
        System.out.println("Computer" + " points:" + player.getPoints());
    }

    public static void main(String[] args) {
        DominoesRound dm = new DominoesRound("Thanasis");

        Scanner in = new Scanner(System.in);

        dm.playRound(in);
    }
}
