//AM 5672 Athanasios Giannopoulos
import java.util.Random;
import java.util.Scanner;

public class Player {
    
    private Hand hand;
    private int points = 0;
    private String name;

    private final Scanner in = new Scanner(System.in);

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
    
    public boolean play(Stock stock, Board board) {

        printHand();   
        
        boolean findMatchingTile = true;

        for (Tile tile: this.hand.getHand()){
            if(board.match(tile)){
                findMatchingTile = true;
                break;
            }
        }

        while (findMatchingTile) {
            int index = askPlayer();

            if (index == -1) {
                boolean played = unmatchedTile(board, stock);
                findMatchingTile = false;
                return played;
            }
            else{
                

                if (board.match(hand.getHand().get(index))) {
                    findMatchingTile = false;
                    matchedTile(board, hand.getTile(index));
                    return true;
                }
                else {System.out.println("Invalid move");}
            }
    }

    return true;
}

    public int ownPoints(){
        for (Tile tile:hand.getHand()){
            this.points += tile.points();
        }

        return this.points;
    }

    //helper methods 
    private void matchedTile(Board board, Tile tile){   //this methods checks if the tile selected can be played or not
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
                    System.out.println("Invalid input");
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
    
    }

    private boolean unmatchedTile(Board board, Stock stock) {
        Tile draw = stock.draw();
    
        while (draw != null && !board.match(draw)) {
            this.hand.add(draw);
            System.out.println("Tile drawn: " + draw);
            

            if (stock.isEmpty()) {
                break; // the  stock is empty but we check the current tile
            }
            
            draw = stock.draw();
        }

        if (draw != null && board.match(draw)) {
            System.out.println("Tile drawn: " + draw);
            matchedTile(board, draw);
            return true;
        } 
        else {
            if (draw == null) {
                System.out.println("Out of stock and no possible moves ");
                return false;
            }
            else { 
                System.out.println("No possible moves ");
                return false;
            }
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

    private boolean computerUnmatchedTile(Board board, Stock stock){
        
        Tile draw = stock.draw();

        //drawing until the stock is empty or there is a possible move
        while (draw != null && !board.match(draw)  ) {
            this.hand.add(draw);
            System.out.println("Tile drawn : " + draw.toString());

            
            if (stock.isEmpty()) {
                break;  // the  stock is empty but we check the current tile
            }

            draw = stock.draw();
        }

        //if there are not cards left to draw and no possible moves
        if (draw != null && board.match(draw)) {
            System.out.println("Tile drawn: " + draw.toString());
            computerMatchedTile(board, draw);
            return true;
        } 
        else {
            if (draw == null) {
                System.out.println("Out of stock and no possible moves ");
                return false;
            }
            else { 
                System.out.println("No possible moves ");
                return false;
            }
        }
    }

    private int askPlayer(){     //this method will get and return the players choice

        Scanner in = new Scanner(System.in);

        while (true) {
            try {

                System.out.print("Select a tile to play, or -1 if no tile: ");
                int index = in.nextInt();
    
                if (index == -1) {
                    
                    return -1;
                } 
                else if (index >= 0 && index < this.hand.getHand().size()) {
                    return index;
                } 
                
                else {
                    System.out.println("Invalid input, enter a number between 0 and " + (hand.getHand().size() - 1));
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please input a valid number");
                in.nextLine(); // flush the invalid input
            }
        }
    }

    

    public static void main(String[] args) {
        
        Stock stock = new Stock();
        Player player = new Player("Than");
        Board board = new Board();

        player.initializeHand(stock);
        
        board.initiliazeBoard(player.getHand().getTile(0));
        
        boolean playerMove = true;
        int round = 0;

        while (!player.emptyHand() && playerMove) {
            System.out.println(board.toString());
            playerMove = player.play(stock, board);

            System.out.println("!player.emptyHand() is " + !player.emptyHand());
            System.out.println("playerMove is " + playerMove);

            System.out.println("\nround is " + round);
            round++;
        }
        
        System.out.println("end");

    }
}
