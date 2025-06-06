//AM 5672 Athanasios Giannopoulos
import java.util.Random;

public class Board {
    
    private Chain chain;

    //setters and getters
    public Chain getChain(){
        return this.chain;
    }

    public void setChain(Chain chain){
        this.chain = chain;
    }

    //methods 
    public boolean isEmpty(){
        return this.chain.isEmpty();
    }

    @Override
    public String toString(){
        return "\nBoard\n" + this.chain.toString() + "\n";
    }

    public boolean matchLeft(Tile tile){
        return this.chain.getLeft() == tile.getLeft() || this.chain.getLeft() == tile.getRight();
    }

    public boolean matchRight(Tile tile){
        return this.chain.getRight() == tile.getLeft() || this.chain.getRight() == tile.getRight();
    }

    public boolean match(Tile tile){
        return matchLeft(tile) || matchRight(tile);
    }

    public boolean matchBoth(Tile tile){
        return matchLeft(tile) && matchRight(tile);
    }

    public void addLeft(Tile tile){
        if (this.chain.getLeft() == tile.getLeft()) {
            tile.rotate();
            chain.insertLeft(tile);
            return;
        }
        
        chain.insertLeft(tile);
        return;
    }

    public void addRight(Tile tile){
        if (this.chain.getRight() == tile.getRight()) {
            tile.rotate();
            chain.insertRight(tile);
            return;
        }
        
        chain.insertRight(tile);;
        return;
    }

    public void initiliazeBoard(Tile tile){   //puts the first tile on the board
        ChainElement ce = new ChainElement(tile);
        this.chain = new Chain(ce);
    }
    
    public static void main(String[] args) {
        
        Stock stock = new Stock();

        Random rand = new Random();

        Tile draw = stock.draw();
        System.out.println("first is " + draw.toString());

        Board board = new Board();
        board.initiliazeBoard(draw);

        while (!stock.isEmpty()){

            draw = stock.draw();
            System.out.println(draw.toString());

            if (board.match(draw)) {
                
                if (board.matchBoth(draw)) {

                    int random = rand.nextInt(2);

                    if (random == 1) {
                        board.addLeft(draw);
                    }

                    else{board.addRight(draw);}
                }

                else if (board.matchLeft(draw)) {
                    board.addLeft(draw);
                
                }

                else{ board.addRight(draw);}
            }
            else{
                System.out.println("doenst match");
            }
        }

        System.out.println(board.toString());
    }
}
