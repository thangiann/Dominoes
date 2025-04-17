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
    }

    public boolean playold(Stock stock, Board board){

        printHand();

        Scanner in = new Scanner(System.in);
        boolean input = true;

        while (input) {
            try {

                System.out.print("Select a tile to play, or -1 if no tile: ");
                int index = in.nextInt();
                in.nextLine();

                System.out.println("index is " + index);

                //when there are no possible moves
                if (index == -1) {
                    boolean returnvalue = unmatchedTile(board, stock);
                    input = false;
                    return returnvalue;
                }

                //when there is a possible move
                else if (index >= 0 && index < hand.getHand().size()) {
                    
                    Tile played = hand.getTile(index);
                    
                    if (board.match(played)) {
                        matchedTile(board, played);
                        input = false;
                        return true;
                    }
                    else{
                        System.out.println("invalid move");
                    }
                }
                
                else{System.out.println("invalid input");}

            }
                   
            catch (Exception e) {
                if (in.hasNextLine()) {
                    in.nextLine(); // clear invalid input safely
                }
                System.out.println("invalid input");
            }
        }
        
        System.out.println();

        //in.close();
        return false;
    }

    public boolean playold2(Stock stock, Board board){

        printHand();

        Scanner in = new Scanner(System.in);
        boolean input = true;

        while (input) {
            System.out.print("Select a tile to play, or -1 if no tile: ");
            
            if (in.hasNextInt()) {
                int index = in.nextInt();
                
                if (in.hasNextLine()) {
                    in.nextLine();
                }

                //when there are no possible moves
                if (index == -1) {
                    boolean returnvalue = unmatchedTile(board, stock);
                    input = false;
                    return returnvalue;
                }

                //when there is a possible move
                else if (index >= 0 && index < hand.getHand().size()) {
                    
                    Tile played = hand.getTile(index);
                    
                    if (board.match(played)) {
                        matchedTile(board, played);
                        input = false;
                        return true;
                    }
                    else{
                        System.out.println("invalid move");
                    }
                }
                
                else{System.out.println("invalid input");}


            }
            else {
                if (in.hasNextLine()) {
                    in.nextLine();
                }
                System.out.println("invalid input");
            }        
        }

        return false;
    }

    public boolean computerPlay(Stock stock, Board board){

        printHand();
        Random rand = new Random();

        Tile bestTile = hand.getBestTile(board);

        if (bestTile != null) {
            if (board.matchBoth(bestTile)) {

                int random = rand.nextInt(2);

                if (random == 1) {
                    board.addLeft(bestTile);
                    return true;
                }

                else{board.addRight(bestTile);}
                return true;
            }
            else if (board.matchLeft(bestTile)) {
                board.addLeft(bestTile);
                return true;
            }
        }
        else{
            Tile draw = stock.draw();
            this.hand.add(draw);

            //drawing until the stock is empty or there is a possible move
            while (!board.match(draw) && !stock.isEmpty()) {
                    draw = stock.draw();
                    this.hand.add(draw);
            }

            if (!board.match(draw)) {
                System.out.println("no possible moves");
                return false;
            }

            if (board.matchBoth(draw)) {

                int random = rand.nextInt(2);

                if (random == 1) {
                    board.addLeft(draw);
                    return true;
                }

                else{board.addRight(draw);}
                return true;
            }
            else if (board.matchLeft(draw)) {
                board.addLeft(draw);
                return true;
            }
            
            else{
                board.addRight(draw);
                return true;
            }
        }

        return false;
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
                boolean returnValue = unmatchedTile(board, stock);
                System.out.println("-1");
                input = false;
                return returnValue;
            }

            // when there is a possible move
            else if (index >= 0 && index < hand.getHand().size()) {
                Tile played = hand.getTile(index);

                if (board.match(played)) {
                    matchedTile(board, played);
                    input = false;
                    return true;
                } else {
                    System.out.println("Invalid move");
                }
            } else {
                System.out.println("Invalid input");
            }
        } else {
            // Flush the invalid input (if non-integer input)
            in.next(); // Consume the invalid input (i.e., the non-integer)
            System.out.println("Invalid input. Please enter a valid integer.");
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
        this.hand.add(draw);

        //drawing until the stock is empty or there is a possible move
        while (!board.match(draw) && !stock.isEmpty()) {
            draw = stock.draw();
            this.hand.add(draw);
        }

        //if there are not cards left to draw and no possible moves
        if (!board.match(draw)) {
            System.out.println("No possible moves");
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
}
