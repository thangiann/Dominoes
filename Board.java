import java.util.Random;

public class Board {
    
    private Chain chain;

    //methods 
    public boolean isEmpty(){
        return this.chain.isEmpty();
    }

    @Override
    public String toString(){
        return this.chain.toString();
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

    public static void main(String[] args) {
        
        Stock stock = new Stock();

        Board board = new Board();

        Random rand = new Random();

        Tile draw;

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
