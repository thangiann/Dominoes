//AM 5672 Athanasios Giannopoulos
import java.util.Random;

public class Stock {

    private Tile[] stack = new Tile[28];

    Random random = new Random();

    //constructor
    public Stock(){
        int pos = 0;
        for (int i = 0; i < 7; i++){

            int j = Integer.valueOf(i);

            while(j < 7){

                Tile tile = new Tile(i, j);
                stack[pos] = tile;
                pos++;
                j++;
            }
        }

        shuffle();
    }

    //setters and getters
    public Tile[] getStack(){
        return this.stack;
    }
    
    //helper method
    private void shuffle(){     //this method starts from the last tile and swaps with another one that is in front of it  
        for (int i = stack.length - 1; i > 0; i--){
            int j = random.nextInt(i);

            Tile tmp = stack[j];
            stack[j] = stack[i];
            stack[i] = tmp; 
        }
    }

    //methods
    public boolean isEmpty(){
        //check if every item in list is null(if it has been removed or "drew")
        for (Tile tile:stack){
            if (tile != null) {
                return false;
            }
        }
        return true;
    }

    public Tile draw(){
        for (int i = 0; i < stack.length; i++){

            if (stack[i] != null) {

                //save the tile, remove it and then return it
                Tile tmp = stack[i];
                stack[i] = null;
                return tmp;
            }
        }

        return null;
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < stack.length; i++){
            if (stack[i] != null) {
                sb.append(stack[i].toString() + " ");   
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        
        Stock stock = new Stock();

        while (!stock.isEmpty()) {
            
            Tile next = stock.draw();
            System.out.println("next tile is " + next.toString());

            System.out.println(stock.toString());
        }
    }
}
