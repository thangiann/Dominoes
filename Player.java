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

    public boolean play(Stock stock, Board board){

        printHand();

        Scanner in = new Scanner(System.in);
        boolean input = true;

        while (input) {
            try {

                int index = in.nextInt();

                //when there are no possible moves
                if (index == -1) {
                    Tile draw = stock.draw();
                    this.hand.add(draw);

                    //drawing until the stock is empty or there is a possible move
                    while (!board.match(draw) && !stock.isEmpty()) {
                        draw = stock.draw();
                        this.hand.add(draw);
                    }
                    
                    //if there are not cards left to draw and no possible moves
                    if (!board.match(draw)) {
                        return false;
                    }

                    if (board.matchBoth(draw)) {
                        boolean ask = true;
                        
                        while (ask) {

                            System.out.println("Left(l) of right(r)");
                            String leftOrRight = in.nextLine().toLowerCase();
                            
                            if (leftOrRight == "l") {
                                
                                ask = false;
                                board.addLeft(draw);
                                return true;
                            }

                            else if (leftOrRight == "r"){

                                ask = false;
                                board.addRight(draw);
                                return true;
                            }
                        }
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

                //when there is a possible move
                else if (index >= 0 && index < hand.getHand().size()) {
                    
                    Tile played = hand.getTile(index);

                    if (board.matchBoth(played)) {
                        boolean ask = true;
                        
                        while (ask) {

                            System.out.println("Left(l) of right(r)");
                            String leftOrRight = in.nextLine().toLowerCase();
                            
                            if (leftOrRight == "l") {
                                
                                ask = false;
                                board.addLeft(played);
                                return true;
                            }

                            else if (leftOrRight == "r"){

                                ask = false;
                                board.addRight(played);
                                return true;
                            }
                            else{
                                System.out.println("invalid move");
                                continue;
                            }
                        }
                    }
                }
            }
                   
            catch (Exception e) {
                continue;
            }
        }
    
        in.close();
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
    
}
