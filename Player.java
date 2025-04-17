import java.util.Random;
import java.util.Scanner;

public class Player {
    
    private Hand hand;
    private int points = 0;
    private String name;

    //constructor
    public Player(String name){
        this.name = name;
    }

    // Setters and Getters
    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //methods
    public void printHand(){
        System.out.println(this.name + ":\n" + this.hand.toString());
    }

    @Override
    public String toString(){
        return this.name;
    }

    public void initializeHand(Stock stock){
        hand = new Hand();
        
        for (int i = 0; i < 7; i++){
            hand.add(stock.draw());
        }
    }

    public boolean emptyHand(){
        return this.hand.isEmpty();
    }

    public int handPoints(){
        return this.hand.points();
    }

    public void collectPoints(Player other){
        this.points += other.handPoints();
        other.setPoints(0);
    } 
 
    public boolean computerPlay(Stock stock, Board board){

        printHand();

        Tile bestTile = hand.getBestTile(board);

        if (bestTile != null) {
            
            computerMatchedTile(board, bestTile);
            return true;
        }
        else{
            boolean computerMove = computerUnmatchedTile(board, stock);
            return computerMove;
        }
    }
    
    public boolean play(Stock stock, Board board, Scanner in) {

    printHand();   
    boolean input = true;

    while (input) {
        System.out.print("Select a tile to play, or -1 if no tile: ");
        
        // Check if the input is an integer
        if (in.hasNextInt()) {
            int index = in.nextInt();

            // when there are no possible moves
            if (index == -1) {
                //boolean returnValue = unmatchedTile(board, stock);
                System.out.println("-1");
                input = false;
                return false; //returnValue;
            }

            // when there is a possible move
            else if (index >= 0 && index < hand.getHand().size()) {
                Tile played = hand.getTile(index);

                if (board.match(played)) {
                    System.out.println("match");
                    input = false;
                    return true;
                }
                else{
                    System.out.println("cant play tile, it doesnt match");
                }
                

            } else {
                System.out.println( "invalid between please select an integer between 0 and " + hand.getHand().size());
            }
        } else {
            // Flush the invalid input (if non-integer input)
            in.next(); // Consume the invalid input (i.e., the non-integer)
            System.out.println("invalid input, please input an integer");
        }
    }

    return false;
}

    //helper methods 
    private void matchedTile(Board board, Tile tile){
        Scanner in = new Scanner(System.in);

        if (board.matchBoth(tile)) {
            boolean ask = true;
                        
            while (ask) {

                System.out.println("Left(l) of right(r)");
                String leftOrRight = in.nextLine(); //.toLowerCase();
                
                if (leftOrRight.equals("l") ) {
                    
                    ask = false;
                    System.out.println("To be added" + tile.toString());
                    board.addLeft(tile);
                }

                else if (leftOrRight.equals("r")){

                    ask = false;
                    System.out.println("To be added" + tile.toString());
                    board.addRight(tile);

                }
                else{
                    System.out.println("invalid move");
                    continue;
                }
            }
        }

        else if (board.matchLeft(tile)){
            System.out.println("To be added" + tile.toString());
            board.addLeft(tile);
        }

        else {
            System.out.println("To be added" + tile.toString());
            board.addRight(tile);
        }
        in.close();
    }

    private boolean unmatchedTile(Board board, Stock stock){

        Tile draw = stock.draw();
        System.out.println("Tile drawn : " + draw.toString());

        //drawing until the stock is empty or there is a possible move
        while (!board.match(draw) && !stock.isEmpty()) {
            draw = stock.draw();
            System.out.println("Tile drawn : " + draw.toString());
        }

        //if there are not cards left to draw and no possible moves
        if (!board.match(draw)) {
            System.out.println("No possible moves");
            this.hand.add(draw);
            return false;
        }
        else if (board.match(draw)) {
            matchedTile(board, draw);
            return true;
        }
        else{
            System.out.println("out of stock");
            return false;
        }
    }

    private void computerMatchedTile(Board board, Tile tile){

        Random rand = new Random();

        if (board.matchBoth(tile)) {
            if (board.matchBoth(tile)) {

                int random = rand.nextInt(2);

                if (random == 1) {
                    System.out.println("Tile to be added: " + tile.toString());
                    board.addLeft(tile);
                }

                else{
                    System.out.println("Tile to be added: " + tile.toString());
                    board.addRight(tile);
                }
            }
            
        }
        else if (board.matchLeft(tile)) {
            System.out.println("Tile to be added: " + tile.toString());
            board.addLeft(tile);
        }
        else{
            System.out.println("Tile to be added: " + tile.toString());
            board.addRight(tile);
        }
    }

    public boolean computerUnmatchedTile(Board board, Stock stock){
        
        Tile draw = stock.draw();
        System.out.println("Tile drawn : " + draw.toString());
        

        //drawing until the stock is empty or there is a possible move
        while (!board.match(draw) && !stock.isEmpty()) {
            draw = stock.draw();
            System.out.println("Tile drawn : " + draw.toString());
        }

        //if there are not cards left to draw and no possible moves
        if (!board.match(draw)) {
            System.out.println("No possible moves");
            this.hand.add(draw);
            return false;
        }
        else if (board.match(draw)) {
            computerMatchedTile(board, draw);
            return true;
        }
        else{
            System.out.println("out of stock");
            return false;
        }
    }

    public static void main(String[] args) {
        
        Stock stock = new Stock();
        Player player = new Player("computer");
        Board board = new Board();

        Tile first = stock.draw();

        board.initiliazeBoard(new Tile(7, 7));
        player.initializeHand(stock);
        
        System.out.println(board.toString());

        boolean computerMove = true;
        int round = 1;

        while (!player.emptyHand() && computerMove) {

            computerMove = player.computerPlay(stock, board);

            System.out.println("!player.emptyHand() " + !player.emptyHand());
            System.out.println("computerMove " + computerMove);

            System.out.println(board.toString());

            System.out.println("round " + round + "\n");
            round++;
        } 

        System.out.println("empty");

        int rem = 0;
        for (int i = 0; i < 28; i++){
            if (stock.getStack()[i] != null) {
                rem++;
            }
        }

        System.out.println("remaining tiles are " + rem);

    } 
}
