//AM 5672 Athanasios Giannopoulos
import java.util.Scanner;

public class DominoesRound {
    
    Player player;
    Player computer;
    Stock stock;
    Board board;

    public DominoesRound(String name){
        player = new Player(name);
        computer = new Player("Computer");
    }

    //setters and getters
    public Player getPlayer(){
        return this.player;
    }

    public Player getComputer(){
        return this.computer;
    }

    public Stock geStock(){
        return this.stock;
    }

    public Board getBoard(){
        return this.board;
    }

    //methods
    public void playRound(){

        Scanner in = new Scanner(System.in);

        this.board = new Board();
        this.stock = new Stock();
        player.initializeHand(stock);
        computer.initializeHand(stock);

        boolean playerMove = true;
        boolean computerMove = true;

        initializeDominoesRound(in);         

        while (!player.emptyHand() && !computer.emptyHand() && (playerMove || computerMove)) {

            
            System.out.println(board.toString());

            computerMove = computer.computerPlay(stock, board);

            if (!computer.emptyHand() && playerMove) {
                System.out.println(board.toString());

                playerMove = player.play(stock, board);
            }
        }

        if (player.emptyHand()) {
            System.out.println("Human won! Emptied Hand");
            player.collectPoints(computer);
            printPoints();            
        }
        else if (computer.emptyHand()) {
            System.out.println("Computer won! Emptied Hand");
            computer.collectPoints(player);
            printPoints();
        }
        else{
            if (computer.getPoints() > player.getPoints()) {
                System.out.println("Computer won! No possible moves");
                computer.collectPoints(player);
                printPoints();
            }
            else if (computer.getPoints() < player.getPoints()) {
                System.out.println("Player won! No possible moves");
                player.collectPoints(computer);
                printPoints();
            }
            else { //this is according to the official Dominoes rules
                System.out.println("Draw! no possible moves");
                System.out.println("No points are awared");
            }
        }
    }

    //helper methods
    private void printPoints(){
        System.out.println(player.getName() + " points:" + player.getPoints());
        System.out.println("Computer" + " points:" + computer.getPoints());
    }

    
    private void initializeDominoesRound(Scanner in){   //ask the player to put the first tile on the board
        System.out.println("\nBoard\n"); // the "\n" are used to make the output more readable
        player.printHand();

        boolean first = true;
        int firstIndex = -1;

        while (first) {
            System.out.print("Select a tile to play: ");
            firstIndex = in.nextInt();

            if (firstIndex >= 0 && firstIndex < 7) {
                first = false;
            }
        }

        Tile firstTile = player.getHand().getHand().remove(firstIndex);
        this.board.initiliazeBoard(firstTile);
    }

}
