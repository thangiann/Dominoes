public class DominoesRound {
    
    Player player;
    Player computer;
    Stock stock = new Stock();
    Board board;

    public DominoesRound(String name){
        player = new Player(name);
        computer = new Player("Computer");
        Tile first = stock.draw();
        board = new Board(first);
    }

    public void playRound(){

        player.initializeHand(stock);
        computer.initializeHand(stock);

        boolean playerMove = true;
        boolean computerMove = true;

        while (!player.emptyHand() && !computer.emptyHand() && (playerMove || computerMove)) {
            System.out.println(board.toString());

            playerMove = player.play(stock, board);

            System.out.println(board.toString());

            computerMove = computer.computerPlay(stock, board);
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
    }

    //helper methods
    private void printPoints(){
        System.out.println(player.getName() + " points:" + player.getPoints());
        System.out.println("Computer" + " points:" + player.getPoints());
    }

    public static void main(String[] args) {
        DominoesRound dm = new DominoesRound("Thanasis");

        dm.playRound();
    }
}
